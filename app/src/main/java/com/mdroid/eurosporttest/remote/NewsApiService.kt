package com.mdroid.eurosporttest.remote

import com.mdroid.eurosporttest.remote.data.NewsRemote
import retrofit2.http.GET


interface NewsApiService {

    @GET("json-storage/bin/edfefba")
    suspend fun getNews(): NewsRemote

}