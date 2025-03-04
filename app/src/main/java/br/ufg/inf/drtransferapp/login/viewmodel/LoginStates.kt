package br.ufg.inf.drtransferapp.login.viewmodel

import br.ufg.inf.drtransferapp.login.model.LoginResponseModel

sealed class LoginStates {
    data class OnSuccessLogin(val loginResponseModel: LoginResponseModel) : LoginStates()
    data class OnError(val error: Throwable) : LoginStates()
}