package br.ufg.inf.drtransferapp.login.repository

import br.ufg.inf.drtransferapp.login.model.LoginRequestModel
import br.ufg.inf.drtransferapp.login.model.LoginResponseModel
import br.ufg.inf.drtransferapp.network.service.LoginApiServices
import br.ufg.inf.drtransferapp.patient.commons.utils.extension.orElse

class LoginRepositoryImpl(private val apiService: LoginApiServices) : LoginRepository {
    override suspend fun login(loginRequest: LoginRequestModel): Result<LoginResponseModel> {
        val result: Result<LoginResponseModel>
        return try {
            val response = apiService.login(loginRequest.email, loginRequest.password)
            result = if (response.code() != STATUS_CODE_200) {
                Result.failure(Throwable(response.message()))
            } else {
                response.body()?.let {
                    Result.success(it)
                }.orElse {
                    Result.failure(Throwable("Erro ao fazer login"))
                }
            }
            result
        } catch (e: Exception) {
            Result.failure(Throwable(e))
        }
    }

    companion object {
        private const val STATUS_CODE_200 = 200
    }
}