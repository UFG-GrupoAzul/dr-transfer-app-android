package br.ufg.inf.drtransferapp.home.usecase

import br.ufg.inf.drtransferapp.home.model.PatientRequestModel
import br.ufg.inf.drtransferapp.home.repository.PatientRepository

class PatientUseCaseImpl(private val repository: PatientRepository): PatientUseCase {
    override suspend fun listAllPatients(): PatientStates {
        val result = repository.callAllPatients()

        return if (result.isSuccess) {
            PatientStates.OnSuccessListPatients(
                result.getOrNull() ?: run {
                    emptyList()
                }
            )
        } else {
            PatientStates.OnError(result.exceptionOrNull() ?: Throwable("Erro desconhecido"))
        }
    }

    override suspend fun createPatient(patient: PatientRequestModel): PatientStates {
        TODO("Not yet implemented")
    }

    override suspend fun updatePatient(
        idPatient: String,
        patient: PatientRequestModel
    ): PatientStates {
        TODO("Not yet implemented")
    }

    override suspend fun deletePatient(idPatient: String): PatientStates {
        TODO("Not yet implemented")
    }

}