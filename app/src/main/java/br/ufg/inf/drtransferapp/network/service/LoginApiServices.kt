package br.ufg.inf.drtransferapp.network.service

import br.ufg.inf.drtransferapp.login.model.LoginResponseModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApiServices {

    @FormUrlEncoded
    @Headers("Content-Type: application/json")
    @POST("auth")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): retrofit2.Response<LoginResponseModel>
}