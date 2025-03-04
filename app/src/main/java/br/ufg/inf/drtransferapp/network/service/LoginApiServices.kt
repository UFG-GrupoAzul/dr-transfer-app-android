package br.ufg.inf.drtransferapp.network.service

import br.ufg.inf.drtransferapp.login.model.LoginRequestModel
import br.ufg.inf.drtransferapp.login.model.LoginResponseModel
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApiServices {

    @POST("auth")
    suspend fun login(
        @Body loginRequest: LoginRequestModel
    ): retrofit2.Response<LoginResponseModel>
}