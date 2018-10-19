package com.eungpang.android.infinitescrollsample.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    private val retrofit: Retrofit
    init {
        retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun getClient(): Retrofit {
        return retrofit
    }
}