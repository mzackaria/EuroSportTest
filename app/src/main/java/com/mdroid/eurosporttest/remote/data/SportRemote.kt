package com.mdroid.eurosporttest.remote.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SportRemote(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?
)