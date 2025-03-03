package br.ufg.inf.drtransferapp.patient.registerNewPatient.usecase

import br.ufg.inf.drtransferapp.patient.commons.model.PatientRequestModel
import br.ufg.inf.drtransferapp.patient.listPatients.viewmodel.PatientStates
import br.ufg.inf.drtransferapp.patient.registerNewPatient.viewmodel.RegisterNewPatientStates

interface RegisterNewPatientUseCase {
    suspend fun createPatient(patient: PatientRequestModel) : RegisterNewPatientStates
}