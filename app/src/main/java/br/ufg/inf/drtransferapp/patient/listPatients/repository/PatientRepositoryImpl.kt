package br.ufg.inf.drtransferapp.patient.listPatients.repository

import br.ufg.inf.drtransferapp.network.service.PatientApiServices
import br.ufg.inf.drtransferapp.patient.commons.model.PatientRequestModel
import br.ufg.inf.drtransferapp.patient.commons.model.PatientResponseModel
import br.ufg.inf.drtransferapp.patient.commons.utils.extension.orElse

class PatientRepositoryImpl(private val apiServices: PatientApiServices) : PatientRepository {
    override suspend fun callAllPatients(): Result<List<PatientResponseModel>> {
        return try {
            val response = apiServices.getAllPatients()
            Result.success(response.body() ?: emptyList())
        } catch (exception: Throwable) {
            Result.failure(exception)
        }
    }

    override suspend fun callDeletePatient(idPatient: String): Result<Boolean> {
        return try {
            val result: Result<Boolean>
            val response = apiServices.deletePatient(idPatient)
            result = if (response.code() != 200 && response.code() != 201) {
                Result.success(false)
            } else if (response.code() == 200 || response.code() == 201){
                Result.success(true)
            } else {
                Result.success(false)
            }
            result
        } catch (exception: Throwable) {
            Result.failure(exception)
        }
    }
}