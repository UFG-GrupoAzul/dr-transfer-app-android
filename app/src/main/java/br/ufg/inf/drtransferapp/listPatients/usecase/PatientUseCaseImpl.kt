package br.ufg.inf.drtransferapp.listPatients.usecase

import br.ufg.inf.drtransferapp.listPatients.model.PatientRequestModel
import br.ufg.inf.drtransferapp.listPatients.repository.PatientRepository
import br.ufg.inf.drtransferapp.utils.extension.orElse
import br.ufg.inf.drtransferapp.listPatients.viewmodel.PatientStates

class PatientUseCaseImpl(private val repository: PatientRepository): PatientUseCase {
    override suspend fun listAllPatients(): PatientStates {
        val result = repository.callAllPatients()

        return if (result.isSuccess) {
            result.getOrNull()?.let {
                PatientStates.OnSuccessListPatients(it)
            }.orElse {
                PatientStates.OnError(result.exceptionOrNull() ?: Throwable("Erro desconhecido"))
            }
        } else {
            PatientStates.OnError(result.exceptionOrNull() ?: Throwable("Erro desconhecido"))
        }
    }

    override suspend fun createPatient(patient: PatientRequestModel): PatientStates {
        val result = repository.callCreatePatient(patient)

        return if (result.isSuccess) {
            result.getOrNull()?.let {
                PatientStates.OnSuccessPatient(it)
            }.orElse {
                PatientStates.OnError(result.exceptionOrNull() ?: Throwable("Erro desconhecido"))
            }
        } else {
            PatientStates.OnError(result.exceptionOrNull() ?: Throwable("Erro desconhecido"))
        }
    }

    override suspend fun updatePatient(
        idPatient: String,
        patient: PatientRequestModel
    ): PatientStates {
        val result = repository.callUpdatePatient(idPatient, patient)

        return if (result.isSuccess) {
            result.getOrNull()?.let {
                PatientStates.OnSuccessPatient(it)
            }.orElse {
                PatientStates.OnError(result.exceptionOrNull() ?: Throwable("Erro desconhecido"))
            }
        } else {
            PatientStates.OnError(result.exceptionOrNull() ?: Throwable("Erro desconhecido"))
        }
    }

    override suspend fun deletePatient(idPatient: String): PatientStates {
        val result = repository.callDeletePatient(idPatient)

        return if (result.isSuccess) {
            result.getOrNull()?.let {
                PatientStates.OnSuccessDeletePatient(it)
            }.orElse {
                PatientStates.OnError(result.exceptionOrNull() ?: Throwable("Erro desconhecido"))
            }
        } else {
            PatientStates.OnError(result.exceptionOrNull() ?: Throwable("Erro desconhecido"))
        }
    }
}