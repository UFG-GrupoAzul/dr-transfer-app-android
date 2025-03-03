package br.ufg.inf.drtransferapp.home.usecase

import br.ufg.inf.drtransferapp.home.model.PatientRequestModel
import br.ufg.inf.drtransferapp.home.viewmodel.PatientStates

interface PatientUseCase {
    suspend fun listAllPatients() : PatientStates

    suspend fun createPatient(patient: PatientRequestModel) : PatientStates

    suspend fun updatePatient(idPatient: String, patient: PatientRequestModel) : PatientStates

    suspend fun deletePatient(idPatient: String) : PatientStates
}

