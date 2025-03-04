package br.ufg.inf.drtransferapp.login.viewmodel

import br.ufg.inf.drtransferapp.login.model.LoginRequestModel

sealed class LoginInterpreter {
    class CallLogin(val loginRequest: LoginRequestModel) : LoginInterpreter()
}