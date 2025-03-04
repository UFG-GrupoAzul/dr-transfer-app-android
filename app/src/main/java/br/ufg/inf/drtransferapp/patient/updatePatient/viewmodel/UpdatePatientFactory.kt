package br.ufg.inf.drtransferapp.patient.updatePatient.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.ufg.inf.drtransferapp.network.client.RetrofitClient
import br.ufg.inf.drtransferapp.network.service.PatientApiServices
import br.ufg.inf.drtransferapp.patient.updatePatient.repository.UpdatePatientRepositoryImpl
import br.ufg.inf.drtransferapp.patient.updatePatient.usecase.UpdatePatientUseCaseImpl

class UpdatePatientFactory : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>) : T {
        val service = RetrofitClient().createService(PatientApiServices::class.java)
        val repository = UpdatePatientRepositoryImpl(service)
        val useCase = UpdatePatientUseCaseImpl(repository)
        return UpdatePatientVM(useCase) as T
    }
}