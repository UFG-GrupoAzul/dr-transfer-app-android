package br.ufg.inf.drtransferapp.patient.updatePatient.usecase

import br.ufg.inf.drtransferapp.patient.commons.model.PatientRequestModel
import br.ufg.inf.drtransferapp.patient.updatePatient.viewmodel.UpdatePatientStates

interface UpdatePatientUseCase {
    suspend fun updatePatient(idPatient: String, patient: PatientRequestModel) : UpdatePatientStates
}