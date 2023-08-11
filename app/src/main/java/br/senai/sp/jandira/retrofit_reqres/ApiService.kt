package br.senai.sp.jandira.retrofit_reqres

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/api/users/{id}")
    //async
    suspend fun getUserById(@Path("id") id: String) : Response<BaseResponse<UserResponse>>
}