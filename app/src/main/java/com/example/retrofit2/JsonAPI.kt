package com.example.retrofit2

import retrofit2.http.GET
import retrofit2.http.Path

interface JsonAPI {
    @GET("/product/{id}")
    suspend fun getProductByID(@Path("id") id : Int) : Product
}