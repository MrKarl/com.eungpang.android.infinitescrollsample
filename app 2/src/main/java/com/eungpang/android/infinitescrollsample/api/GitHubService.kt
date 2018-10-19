package com.eungpang.android.infinitescrollsample.api

import com.eungpang.android.infinitescrollsample.vo.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface GitHubService {
    @GET("users/list?sort=desc")
    fun users(): Call<List<User>>

    @GET("users")
    fun users(@Query("since") since: Int): Call<List<User>>
}