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
import ru.aston.news.di.api.headLine.ApiBusinessService
import ru.aston.news.dto.Filters
import ru.aston.news.dto.Post
import ru.aston.news.dto.Response
import ru.aston.news.entity.headline.GeneralEntity
import ru.aston.news.entity.headline.toDto
import ru.aston.news.entity.headline.toGeneralEntity
import ru.aston.news.entity.saved.SavedEntity
import ru.aston.news.entity.search.SearchEntity
import ru.aston.news.entity.search.toSearchEntity
import ru.aston.news.entity.source.CheckSourceEntity
import ru.aston.news.entity.source.toCheckSourceEntity
import ru.aston.news.entity.toDto
import ru.aston.news.entity.toEntity
import javax.inject.Inject


class PostRepositoryImpl @Inject constructor(
    private val apiService: ApiBusinessService,
    private val dao: PostDao,
    private val checkSourceDao: CheckSourceDao,
    private val generalDao: GeneralDao,
    private val savedDao: SavedDao,
    private val searchDao: SearchDao,
) : PostRepository {

    private var filters: Filters = Filters()


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
                    generalDao.insertAll(posts.toGeneralEntity())
                }
                generalDao.getAll().map { it.toDto() }
            }

    override val singleGeneralPost: List<Post> = generalDao.getPost().map { it.toDto() }

    override fun addGeneral(post: Post) {
        generalDao.insert(GeneralEntity.fromDto(post))
    }
    override  fun removeGeneral(post: Post) {
        generalDao.removePost(post.idSaved)
    }

    override suspend fun search(text: String) {

        val result = apiService.getSearchAll(text)
        if (result.isSuccessful) {
            searchDao.clear()
            val body = result.body()?.posts
            searchDao.insertAll(body!!.toSearchEntity())
        } else {
            searchDao.insertAll(filteredDataFromDBBy(text)!!.toSearchEntity())
        }
    }

    override suspend fun clearSearchDao() {
        searchDao.clear()
    }

    override val searchPosts =
        searchDao.getSearchListAll().map { it.map(SearchEntity::toDto) }.flowOn(Dispatchers.Default)

    private fun filteredDataFromDBBy(text: String): List<Post>? {
        return (singleBusinessPost + singleGeneralPost + singleSavedPost).toSet().filter {
            it.title == text || it.content == text || it.source.name == text || it.author == text
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


    override fun saveRelevant(relevant: String?) {
        filters = filters.copy(relevant = relevant)
    }

    override fun saveLanguage(language: String?) {
        filters = filters.copy(language = language)
    }

    override fun saveData(startData: String?, toData: String?) {
        filters = filters.copy(dateFrom = startData, dateTo = toData)
    }

    override fun getFilters(): Filters = filters


    //Saved


    override val dataSavedPost: Flow<List<Post>> =
        savedDao.getSavedAll().map { it.map(SavedEntity::toDto) }.flowOn(Dispatchers.Default)

    override val singleSavedPost: List<Post> = savedDao.getSinglePost().map { it.toDto() }

    override suspend fun remove(post: Post) {
        savedDao.clear(post.idSaved)
    }

}