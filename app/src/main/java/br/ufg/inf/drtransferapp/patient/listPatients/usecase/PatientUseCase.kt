package br.ufg.inf.drtransferapp.patient.listPatients.usecase

import br.ufg.inf.drtransferapp.patient.commons.model.PatientRequestModel
import br.ufg.inf.drtransferapp.patient.listPatients.viewmodel.PatientStates

interface PatientUseCase {
    suspend fun listAllPatients() : PatientStates

    suspend fun deletePatient(idPatient: String) : PatientStates
}

