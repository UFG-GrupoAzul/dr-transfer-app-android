package br.ufg.inf.drtransferapp.patient.listPatients.usecase

import br.ufg.inf.drtransferapp.patient.listPatients.model.PatientRequestModel
import br.ufg.inf.drtransferapp.patient.listPatients.viewmodel.PatientStates

interface PatientUseCase {
    suspend fun listAllPatients() : PatientStates

    suspend fun createPatient(patient: PatientRequestModel) : PatientStates

    suspend fun updatePatient(idPatient: String, patient: PatientRequestModel) : PatientStates

    suspend fun deletePatient(idPatient: String) : PatientStates
}

