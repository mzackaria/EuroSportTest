package com.mdroid.eurosporttest.remote

import com.mdroid.eurosporttest.remote.data.NewsRemote

class RemoteNewsRepository(
    val api: NewsApi
) : NewsApiService {

    override suspend fun getNews(): NewsRemote {
        return api.retrofitService.getNews()
    }
}