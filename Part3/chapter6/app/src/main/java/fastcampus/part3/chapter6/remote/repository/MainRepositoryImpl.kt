package fastcampus.part3.chapter6.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import fastcampus.part3.chapter6.remote.MainPagingSource
import fastcampus.part3.chapter6.remote.MainService
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainService: MainService
) : MainRepository {

    override fun loadList() = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            MainPagingSource(mainService)
        }
    ).flow
}