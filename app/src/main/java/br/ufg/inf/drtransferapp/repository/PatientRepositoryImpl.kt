package br.ufg.inf.drtransferapp.repository

import br.ufg.inf.drtransferapp.api.PatientApiServices
import br.ufg.inf.drtransferapp.model.PatientRequestModel
import br.ufg.inf.drtransferapp.model.PatientResponseModel
import br.ufg.inf.drtransferapp.utils.extension.orElse

class PatientRepositoryImpl(private val apiServices: PatientApiServices) : PatientRepository {
    override suspend fun callAllPatients(): Result<List<PatientResponseModel>> {
        val response = apiServices.getAllPatients()

        return if (response.isSuccessful) {
            Result.success(response.body() ?: emptyList())
        } else {
            Result.failure(Throwable("Erro na requisição"))
        }
    }


    override suspend fun callCreatePatient(patient: PatientRequestModel): Result<PatientResponseModel> {
        val response = apiServices.createPatient(patient)

        return if (response.isSuccessful) {
            response.body()?.let {
                Result.success(it)
            }.orElse {
                Result.failure(Throwable("Falha ao criar paciente"))
            }
        } else {
            Result.failure(Throwable("Erro na requisição"))
        }
    }

    override suspend fun callUpdatePatient(idPatient: String, patient: PatientRequestModel) : Result<PatientResponseModel> {
        val response = apiServices.updatePatient(idPatient, patient)

        return if (response.isSuccessful) {
            response.body()?.let {
                Result.success(it)
            }.orElse {
                Result.failure(Throwable("Falha ao atualizar paciente"))
            }
        } else {
            Result.failure(Throwable("Erro na requisição"))
        }
    }

    override suspend fun callDeletePatient(idPatient: String) : Result<Boolean> {
        val response = apiServices.deletePatient(idPatient)

        return if (response.isSuccessful) {
            Result.success(true)
        } else {
            Result.failure(Throwable("Erro na requisição"))

        }
    }
}