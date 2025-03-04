package br.ufg.inf.drtransferapp.patient.registerNewPatient.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.ufg.inf.drtransferapp.network.client.RetrofitClient
import br.ufg.inf.drtransferapp.network.service.PatientApiServices
import br.ufg.inf.drtransferapp.patient.registerNewPatient.repository.RegisterNewPatientRepositoryImpl
import br.ufg.inf.drtransferapp.patient.registerNewPatient.usecase.RegisterNewPatientUseCaseImpl

class RegisterNewPatientFactory: ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>) : T {
        val service = RetrofitClient().createService(PatientApiServices::class.java)
        val repository = RegisterNewPatientRepositoryImpl(service)
        val useCase = RegisterNewPatientUseCaseImpl(repository)
        return RegisterNewPatientVM(useCase) as T
    }
}