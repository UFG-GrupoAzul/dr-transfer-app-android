package br.ufg.inf.drtransferapp.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.ufg.inf.drtransferapp.login.repository.LoginRepositoryImpl
import br.ufg.inf.drtransferapp.login.usecase.LoginUseCaseImpl
import br.ufg.inf.drtransferapp.network.client.RetrofitClient
import br.ufg.inf.drtransferapp.network.service.LoginApiServices

@Suppress("UNCHECKED_CAST")
class LoginFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val service = RetrofitClient().createService(LoginApiServices::class.java)
        val repository = LoginRepositoryImpl(service)
        val useCase = LoginUseCaseImpl(repository)
        return LoginVM(useCase) as T
    }
}