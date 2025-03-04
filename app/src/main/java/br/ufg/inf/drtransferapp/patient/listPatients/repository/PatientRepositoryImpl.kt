package br.ufg.inf.drtransferapp.patient.listPatients.repository

import br.ufg.inf.drtransferapp.network.service.PatientApiServices
import br.ufg.inf.drtransferapp.patient.commons.model.PatientResponseModel

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
            result = if (response.code() != STATUS_CODE_200 && response.code() != STATUS_CODE_201 && response.code() != STATUS_CODE_204) {
                Result.failure(Throwable(response.message()))
            } else if (response.code() == STATUS_CODE_200 || response.code() == STATUS_CODE_201 || response.code() == STATUS_CODE_204){
                Result.success(true)
            } else {
                Result.failure(Throwable(response.message()))
            }
            result
        } catch (exception: Throwable) {
            Result.failure(Throwable(exception))
        }
    }

    companion object {
        private const val STATUS_CODE_200 = 200
        private const val STATUS_CODE_201 = 201
        private const val STATUS_CODE_204 = 204
    }
}