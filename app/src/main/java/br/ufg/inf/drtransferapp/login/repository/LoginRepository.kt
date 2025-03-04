package br.ufg.inf.drtransferapp.login.repository

import br.ufg.inf.drtransferapp.login.model.LoginRequestModel
import br.ufg.inf.drtransferapp.login.model.LoginResponseModel

interface LoginRepository {
    suspend fun login(loginRequest: LoginRequestModel): Result<LoginResponseModel>
}