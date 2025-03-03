package br.ufg.inf.drtransferapp.patient.updatePatient.repository

import br.ufg.inf.drtransferapp.patient.commons.model.PatientRequestModel
import br.ufg.inf.drtransferapp.patient.commons.model.PatientResponseModel

interface UpdatePatientRepository {
    suspend fun callUpdatePatient(
        idPatient: String,
        patient: PatientRequestModel
    ): Result<PatientResponseModel>
}