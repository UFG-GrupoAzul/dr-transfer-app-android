package br.ufg.inf.drtransferapp.patient.registerNewPatient.repository

import br.ufg.inf.drtransferapp.patient.commons.model.PatientRequestModel
import br.ufg.inf.drtransferapp.patient.commons.model.PatientResponseModel

interface RegisterNewPatientRepository {
    suspend fun callCreatePatient(patient: PatientRequestModel): Result<PatientResponseModel>
}