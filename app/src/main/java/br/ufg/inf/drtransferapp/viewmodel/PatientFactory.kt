package br.ufg.inf.drtransferapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.ufg.inf.drtransferapp.api.RetrofitClient
import br.ufg.inf.drtransferapp.repository.PatientRepositoryImpl
import br.ufg.inf.drtransferapp.usecase.PatientUseCaseImpl

class PatientFactory : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>) : T {
        val service = RetrofitClient().createService()
        val repository = PatientRepositoryImpl(service)
        val useCase = PatientUseCaseImpl(repository)
        return PatientVM(useCase) as T
    }
 }