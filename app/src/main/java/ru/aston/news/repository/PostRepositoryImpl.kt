package ru.aston.news.repository

import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.aston.news.ApiError
import ru.aston.news.dao.PostDao
import ru.aston.news.dao.checkSource.CheckSourceDao
import ru.aston.news.dao.general.GeneralDao
import ru.aston.news.dao.savedSource.SavedDao
import ru.aston.news.dao.search.SearchDao
import ru.aston.news.dao.travel.TravelDao
import ru.aston.news.di.api.headLine.ApiBusinessService
import ru.aston.news.dto.Post
import ru.aston.news.dto.Response
import ru.aston.news.entity.headline.GeneralEntity
import ru.aston.news.entity.headline.toDto
import ru.aston.news.entity.headline.toGeneralEntity
import ru.aston.news.entity.saved.SavedEntity
import ru.aston.news.entity.saved.toSavedEntity
import ru.aston.news.entity.search.SearchEntity
import ru.aston.news.entity.search.toSearchEntity
import ru.aston.news.entity.source.CheckSourceEntity
import ru.aston.news.entity.source.toCheckSourceEntity
import ru.aston.news.entity.toDto
import ru.aston.news.entity.toEntity
import ru.aston.news.entity.travel.toDto
import ru.aston.news.entity.travel.toTravelEntity
import javax.inject.Inject


class PostRepositoryImpl @Inject constructor(
    private val apiService: ApiBusinessService,
    private val dao: PostDao,
    private val checkSourceDao: CheckSourceDao,
    private val generalDao: GeneralDao,
    private val savedDao: SavedDao,
    private val searchDao: SearchDao,
    private val travelDao: TravelDao,
) : PostRepository {


    override fun getTravelPosts(
        language: String?,
        sortBy: String?,
        from: String?,
        to: String?
    ): Single<List<Post>> =
        apiService.getAllTravel(language, sortBy, from, to)
            .onErrorResumeNext { Single.just(Response()) }
            .map { it.posts ?: emptyList() }
            .flatMap { posts ->
                travelDao.clear()
                if (posts.isNotEmpty()) {
                    // travelDao.clear()
                    travelDao.insertAll(posts.toTravelEntity())
                }
                travelDao.getAll().map { it.toDto() }
            }

    override val singleTravelPost: List<Post> = travelDao.getPost().map { it.toDto() }


    //Business
    override fun getBusinessPosts(
        language: String?,
        sortBy: String?,
        from: String?,
        to: String?
    ): Single<List<Post>> =
        apiService.getAllBusiness(language, sortBy, from, to)
            .onErrorResumeNext { Single.just(Response()) }
            .map { it.posts ?: emptyList() }
            .flatMap { posts ->
                if (posts.isNotEmpty()) {
                    dao.clear()
                    dao.insertAll(posts.toEntity())
                }
                dao.getAll().map { it.toDto() }
            }

    override val singleBusinessPost: List<Post> = dao.getPost().map { it.toDto() }


    //General

    override fun getGeneralPosts(
        language: String?,
        sortBy: String?,
        from: String?,
        to: String?
    ): Single<List<Post>> =
        apiService.getAllGeneral(language, sortBy, from, to)
            .onErrorResumeNext { Single.just(Response()) }
            .map { it.posts ?: emptyList() }
            .flatMap { posts ->
                if (posts.isNotEmpty()) {
                    generalDao.clear()
                    generalDao.insertAll(posts.toGeneralEntity())
                }
                generalDao.getAll().map { it.toDto() }
            }

    override val singleGeneralPost: List<Post> = generalDao.getPost().map { it.toDto() }

    override fun addGeneral(post: Post) {
        generalDao.insert(GeneralEntity.fromDto(post))
    }

    override fun removeGeneral(post: Post) {
        generalDao.removePost(post.idSaved)
    }

    override suspend fun search(text: String) {
        searchDao.clear()
        searchDao.insertAll(filteredDataFromDBBy(text)!!.toSearchEntity())
        val result = apiService.getSearchAll(text)
        if (result.isSuccessful) {
            searchDao.clear()
            val body = result.body()?.posts ?: filteredDataFromDBBy(text)
            searchDao.insertAll(body!!.toSearchEntity())
        } else {
            searchDao.insertAll(filteredDataFromDBBy(text)!!.toSearchEntity())
        }
    }

    override suspend fun searchBD(text: String) {
        searchDao.insertAll(filteredDataFromDBBy(text)!!.toSearchEntity())
    }


    override val singleSearchPost: List<Post> = searchDao.getPost().map { it.toDto() }

    override suspend fun clearSearchDao() {
        searchDao.clear()
    }

    override val searchPosts =
        searchDao.getSearchListAll().map { it.map(SearchEntity::toDto) }.flowOn(Dispatchers.Default)

    private fun filteredDataFromDBBy(text: String): List<Post>? {
        return (singleBusinessPost + singleGeneralPost + singleSavedPost + singleTravelPost).filter {
            it.title.contains(text, ignoreCase = true) || it.content!!.contains(
                text,
                ignoreCase = true
            ) || it.source.name.contains(text, ignoreCase = true) || it.author!!.contains(
                text,
                ignoreCase = true
            ) || it.description!!.contains(text, ignoreCase = true)
        }
    }


    //CheckSource
    override val dataCheckPost: Flow<List<Post>> =
        checkSourceDao.getAll().map { it.map(CheckSourceEntity::toDto) }.flowOn(Dispatchers.Default)

    override suspend fun getSourcePost(source: String) {
        checkSourceDao.clear()
        val sourcePost = apiService.getAll(source)
        if (!sourcePost.isSuccessful) {
            throw ApiError(sourcePost.code(), sourcePost.message())
        }
        val body =
            sourcePost.body()?.posts ?: throw ApiError(sourcePost.code(), sourcePost.message())
        checkSourceDao.insertAll(body.toCheckSourceEntity())
    }

    override val dataPost: List<Post> = checkSourceDao.getPost().map { it.toDto() }

    override suspend fun add(post: Post) {
        savedDao.insert(SavedEntity.fromDto(post))
    }


    //Saved


    override val dataSavedPost: Flow<List<Post>> =
        savedDao.getSavedAll().map { it.map(SavedEntity::toDto) }.flowOn(Dispatchers.Default)

    override val singleSavedPost: List<Post> = savedDao.getSinglePost().map { it.toDto() }

    override suspend fun remove(post: Post) {
        savedDao.clear(post.idSaved)
    }

    override fun deleteOld(time: Long) {
        val list = singleSavedPost.filter { it.time > time }
        savedDao.clearAll()
        savedDao.insertAll(list.toSavedEntity())
    }

}