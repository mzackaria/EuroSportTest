package com.mdroid.eurosporttest.remote

import com.mdroid.eurosporttest.Constants.BASE_URL
import com.mdroid.eurosporttest.remote.data.NewsRemote
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface NewsApiService {

    @GET("json-storage/bin/edfefba")
    suspend fun getNews(): NewsRemote

}

object NewsApi {
    val retrofitService : NewsApiService by lazy { retrofit.create(NewsApiService::class.java) }
}
