package ru.aston.news.repository

import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.aston.news.ApiError
import ru.aston.news.dao.PostDao
import ru.aston.news.dao.checkSource.CheckSourceDao
import ru.aston.news.dao.general.GeneralDao
import ru.aston.news.di.api.headLine.ApiBusinessService
import ru.aston.news.dto.Filters
import ru.aston.news.dto.Post
import ru.aston.news.dto.Response
import ru.aston.news.entity.headline.toDto
import ru.aston.news.entity.headline.toGeneralEntity
import ru.aston.news.entity.source.CheckSourceEntity
import ru.aston.news.entity.source.toCheckSourceEntity
import ru.aston.news.entity.toDto
import ru.aston.news.entity.toEntity
import javax.inject.Inject


class PostRepositoryImpl @Inject constructor(
    private val apiService: ApiBusinessService,
    private val dao: PostDao,
    private val checkSourceDao: CheckSourceDao,
    private val generalDao: GeneralDao
) : PostRepository {

    //liveData для запросов
    //override val filterState: MutableLiveData<Filters> = MutableLiveData(Filters())



    //Business
    override fun getBusinessPosts(): Single<List<Post>> =
        apiService.getAllBusiness()
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

    override fun getGeneralPosts(language: String?, sortBy: String?): Single<List<Post>> =
        apiService.getAllGeneral(language, sortBy)
            .onErrorResumeNext { Single.just(Response()) }
            .map { it.posts ?: emptyList() }
            .flatMap { posts ->
                if (posts.isNotEmpty()) {
                    generalDao.insertAll(posts.toGeneralEntity())
                }
                generalDao.getAll().map { it.toDto() }
            }

    override val singleGeneralPost: List<Post> = generalDao.getPost().map { it.toDto() }


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
        //TODO savedDao.insert(SavedEntity.fromDto(post))
    }

    override fun saveRelevant(relevant: String?) {
        filtersState = filtersState.copy(relevant = relevant)

        /*Filters(
        relevant = relevant,
        language = null)*/
    }

    override fun saveLanguage(language: String?) {
        filtersState = Filters(
            relevant = filtersState.relevant,
            language = language
        )
    }

    override var filtersState = Filters(null, "ru")
}