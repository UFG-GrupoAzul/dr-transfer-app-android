package br.ufg.inf.drtransferapp.patient.registerNewPatient.usecase

import br.ufg.inf.drtransferapp.patient.commons.model.PatientRequestModel
import br.ufg.inf.drtransferapp.patient.commons.utils.extension.orElse
import br.ufg.inf.drtransferapp.patient.registerNewPatient.repository.RegisterNewPatientRepository
import br.ufg.inf.drtransferapp.patient.registerNewPatient.viewmodel.RegisterNewPatientStates

class RegisterNewPatientUseCaseImpl(private val repository: RegisterNewPatientRepository) : RegisterNewPatientUseCase {
    override suspend fun createPatient(patient: PatientRequestModel): RegisterNewPatientStates {
        val result = repository.callCreatePatient(patient)

        return if (result.isSuccess) {
            result.getOrNull()?.let {
                RegisterNewPatientStates.OnSuccess(it)
            }.orElse {
                RegisterNewPatientStates.OnError(result.exceptionOrNull() ?: Throwable("Erro desconhecido"))
            }
        } else {
            RegisterNewPatientStates.OnError(result.exceptionOrNull() ?: Throwable("Erro desconhecido"))
        }
    }
}