package br.ufg.inf.drtransferapp.listPatients.repository

import br.ufg.inf.drtransferapp.listPatients.api.PatientApiServices
import br.ufg.inf.drtransferapp.listPatients.model.PatientRequestModel
import br.ufg.inf.drtransferapp.listPatients.model.PatientResponseModel
import br.ufg.inf.drtransferapp.utils.extension.orElse

class PatientRepositoryImpl(private val apiServices: PatientApiServices) : PatientRepository {
    override suspend fun callAllPatients(): Result<List<PatientResponseModel>> {
        return try {
            val response = apiServices.getAllPatients()
            Result.success(response.body() ?: emptyList())
        } catch (exception: Throwable) {
            Result.failure(exception)
        }
    }

    override suspend fun callCreatePatient(patient: PatientRequestModel): Result<PatientResponseModel> {
        return try {
            val response = apiServices.createPatient(patient)
            response.body()?.let {
                Result.success(it)
            }.orElse {
                Result.failure(Throwable("Falha ao criar paciente"))
            }
        } catch (exception: Throwable) {
            Result.failure(exception)
        }
    }

    override suspend fun callUpdatePatient(
        idPatient: String,
        patient: PatientRequestModel
    ): Result<PatientResponseModel> {
        return try {
            val response = apiServices.updatePatient(idPatient, patient)
            response.body()?.let {
                Result.success(it)
            }.orElse {
                Result.failure(Throwable("Falha ao atualizar paciente"))
            }
        } catch (exception: Throwable) {
            Result.failure(exception)
        }
    }

    override suspend fun callDeletePatient(idPatient: String): Result<Boolean> {
        return try {
            apiServices.deletePatient(idPatient)
            Result.success(true)
        } catch (exception: Throwable) {
            Result.failure(exception)
        }
    }
}