package com.vezeeta.vezeetatask.data.source.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.vezeeta.vezeetatask.data.source.remote.RemoteDataSource
import com.vezeeta.vezeetatask.domain.model.Offer
import com.vezeeta.vezeetatask.domain.repository.OffersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Created by Ahmed Zain on 6/28/2020.
 */
class OffersDataSource(
    coroutineContext: CoroutineContext,
    val remoteDataSource: RemoteDataSource
) :
    PageKeyedDataSource<Int, Offer>() {
    private val job = Job()
    private val scope = CoroutineScope(coroutineContext + job)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Offer>
    ) {
        scope.launch {
            try {
                val response = remoteDataSource.getOffers(params.requestedLoadSize)
                when {
                    response.isSuccessful -> {
                        val offers = response.body()?.offersList
                        callback.onResult(offers ?: listOf(), null, 1)
                    }
                }
            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Offer>) {
        scope.launch {
            try {
                val response = remoteDataSource.getOffers(params.key)
                when {
                    response.isSuccessful -> {
                        val offers = response.body()?.offersList
                        callback.onResult(offers ?: listOf(), params.key + 1)
                    }
                }
            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }

    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Offer>
    ) {
        /*scope.launch {
            try {
                val response =
                    apiService.fetchPosts(loadSize = params.requestedLoadSize, before = params.key)
                when {
                    response.isSuccessful -> {
                        val listing = response.body()?.data
                        val items = listing?.children?.map { it.data }
                        callback.onResult(items ?: listOf(), listing?.after)
                    }
                }

            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }*/

    }

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }

}