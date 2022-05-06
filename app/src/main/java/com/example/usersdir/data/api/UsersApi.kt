package com.example.usersdir.data.api

import com.example.usersdir.BuildConfig
import com.example.usersdir.data.api.request.UserRequest
import com.example.usersdir.data.api.schema.UserSchema
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface UsersApi {

    @GET("public/v2/users")
    suspend fun getUsers(@Header("Authorization") authorization: String = "Bearer " + BuildConfig.apiKey): List<UserSchema>

    @POST("public/v2/users")
    suspend fun createRequest(
        @Body request: UserRequest,
        @Header("Authorization") authorization: String = "Bearer " + BuildConfig.apiKey
    ): UserSchema

    @DELETE("public/v2/users/{id}")
    suspend fun deleteUser(
        @Path("id") id: Int,
        @Header("Authorization") authorization: String = "Bearer " + BuildConfig.apiKey
    ): Response<Unit>
}
