package br.ufg.inf.drtransferapp.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.ufg.inf.drtransferapp.home.api.RetrofitClient
import br.ufg.inf.drtransferapp.home.repository.PatientRepositoryImpl
import br.ufg.inf.drtransferapp.home.usecase.PatientUseCaseImpl

class PatientFactory : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>) : T {
        val service = RetrofitClient().createService()
        val repository = PatientRepositoryImpl(service)
        val useCase = PatientUseCaseImpl(repository)
        return PatientVM(useCase) as T
    }
 }