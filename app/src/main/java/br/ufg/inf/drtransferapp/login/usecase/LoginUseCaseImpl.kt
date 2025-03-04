package br.ufg.inf.drtransferapp.login.usecase

import br.ufg.inf.drtransferapp.login.model.LoginRequestModel
import br.ufg.inf.drtransferapp.login.repository.LoginRepository
import br.ufg.inf.drtransferapp.login.viewmodel.LoginStates
import br.ufg.inf.drtransferapp.patient.commons.utils.extension.orElse

class LoginUseCaseImpl(private val repository: LoginRepository): LoginUseCase {
    override suspend fun login(loginRequest: LoginRequestModel): LoginStates {
        val result = repository.login(loginRequest)

        return if (result.isSuccess) {
            result.getOrNull()?.let {
                LoginStates.OnSuccessLogin(it)
            }.orElse {
                LoginStates.OnError(result.exceptionOrNull() ?: Throwable("Erro desconhecido"))
            }
        } else {
            LoginStates.OnError(result.exceptionOrNull() ?: Throwable("Erro desconhecido"))
        }
    }
}