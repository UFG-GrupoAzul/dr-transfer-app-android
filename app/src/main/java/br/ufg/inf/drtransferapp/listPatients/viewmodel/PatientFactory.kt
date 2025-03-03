package br.ufg.inf.drtransferapp.listPatients.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.ufg.inf.drtransferapp.listPatients.api.RetrofitClient
import br.ufg.inf.drtransferapp.listPatients.repository.PatientRepositoryImpl
import br.ufg.inf.drtransferapp.listPatients.usecase.PatientUseCaseImpl

class PatientFactory : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>) : T {
        val service = RetrofitClient().createService()
        val repository = PatientRepositoryImpl(service)
        val useCase = PatientUseCaseImpl(repository)
        return PatientVM(useCase) as T
    }
 }