package br.ufg.inf.drtransferapp.home.repository

import androidx.lifecycle.LiveData
import br.ufg.inf.drtransferapp.home.model.PatientRequestModel
import br.ufg.inf.drtransferapp.home.model.PatientResponseModel
import okhttp3.Response

interface PatientRepository {
    /*Aqui estão todos os métoddos que serão implementados na classe PatientRepositoryImpl.
    * São os métoddos que vão chamar cada um dos endpoints da nossa API.
    */
    suspend fun callAllPatients(): Result<List<PatientResponseModel>>

    suspend fun callCreatePatient(patient: PatientRequestModel): Result<PatientResponseModel>

    suspend fun callUpdatePatient(
        idPatient: String,
        patient: PatientRequestModel
    ): Result<PatientResponseModel>

    suspend fun callDeletePatient(idPatient: String): Result<Any>
}