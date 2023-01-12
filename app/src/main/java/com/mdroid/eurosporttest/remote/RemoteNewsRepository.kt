package com.mdroid.eurosporttest.remote

import com.mdroid.eurosporttest.remote.data.NewsRemote

class RemoteNewsRepository(
    private val api: NewsApiService
) : NewsApiService {

    override suspend fun getNews(): NewsRemote {
        return api.getNews()
    }
}