package br.ufg.inf.drtransferapp.patient.updatePatient.usecase

import br.ufg.inf.drtransferapp.patient.commons.model.PatientRequestModel
import br.ufg.inf.drtransferapp.patient.commons.utils.extension.orElse
import br.ufg.inf.drtransferapp.patient.listPatients.viewmodel.PatientStates
import br.ufg.inf.drtransferapp.patient.updatePatient.repository.UpdatePatientRepository
import br.ufg.inf.drtransferapp.patient.updatePatient.viewmodel.UpdatePatientStates

class UpdatePatientUseCaseImpl(private val repository: UpdatePatientRepository):UpdatePatientUseCase {
    override suspend fun updatePatient(
        idPatient: String,
        patient: PatientRequestModel
    ): UpdatePatientStates {
        val result = repository.callUpdatePatient(idPatient, patient)

        return if (result.isSuccess) {
            result.getOrNull()?.let {
                UpdatePatientStates.OnSuccess(it)
            }.orElse {
                UpdatePatientStates.OnError(result.exceptionOrNull() ?: Throwable("Erro desconhecido"))
            }
        } else {
            UpdatePatientStates.OnError(result.exceptionOrNull() ?: Throwable("Erro desconhecido"))
        }
    }
}