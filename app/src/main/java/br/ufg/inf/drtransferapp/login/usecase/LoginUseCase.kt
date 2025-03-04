package br.ufg.inf.drtransferapp.login.usecase

import br.ufg.inf.drtransferapp.login.model.LoginRequestModel
import br.ufg.inf.drtransferapp.login.viewmodel.LoginStates

interface LoginUseCase {

    suspend fun login(loginRequest: LoginRequestModel): LoginStates
}