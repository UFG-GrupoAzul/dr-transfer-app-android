package br.ufg.inf.drtransferapp.repository

import androidx.lifecycle.LiveData
import br.ufg.inf.drtransferapp.api.ApiListener
import br.ufg.inf.drtransferapp.model.PatientRequestModel
import br.ufg.inf.drtransferapp.model.PatientResponseModel
import okhttp3.Response

interface PatientRepository {
    /*Aqui estão todos os métoddos que serão implementados na classe PatientRepositoryImpl.
    * São os métoddos que vão chamar cada um dos endpoints da nossa API.
    */
    fun callAllPatients(): LiveData<List<PatientResponseModel>>
    fun callCreatePatient(patient: PatientRequestModel)
    fun callUpdatePatient(idPatient: String, patient: PatientRequestModel)
    fun callDeletePatient(idPatient: String)
}