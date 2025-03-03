package br.ufg.inf.drtransferapp.patient.listPatients.repository

import br.ufg.inf.drtransferapp.patient.commons.model.PatientRequestModel
import br.ufg.inf.drtransferapp.patient.commons.model.PatientResponseModel

interface PatientRepository {
    /*Aqui estão todos os métoddos que serão implementados na classe PatientRepositoryImpl.
    * São os métoddos que vão chamar cada um dos endpoints da nossa API.
    */
    suspend fun callAllPatients(): Result<List<PatientResponseModel>>

    suspend fun callUpdatePatient(
        idPatient: String,
        patient: PatientRequestModel
    ): Result<PatientResponseModel>

    suspend fun callDeletePatient(idPatient: String): Result<Boolean>
}