package br.ufg.inf.drtransferapp.patient.registerNewPatient.repository

import br.ufg.inf.drtransferapp.network.service.PatientApiServices
import br.ufg.inf.drtransferapp.patient.commons.model.PatientRequestModel
import br.ufg.inf.drtransferapp.patient.commons.model.PatientResponseModel
import br.ufg.inf.drtransferapp.patient.commons.utils.extension.orElse

class RegisterNewPatientRepositoryImpl(private val apiServices: PatientApiServices) :
    RegisterNewPatientRepository {
    override suspend fun callCreatePatient(patient: PatientRequestModel): Result<PatientResponseModel> {
        val result: Result<PatientResponseModel>
        return try {
            val response = apiServices.createPatient(patient)
            result = if (response.code() != 200 && response.code() != 201) {
                Result.failure(Throwable(response.message()))
            } else {
                response.body()?.let {
                    Result.success(it)
                }.orElse {
                    Result.failure(Throwable("Falha ao criar paciente"))
                }
            }
            result
        } catch (exception: Throwable) {
            Result.failure(exception)
        }
    }
}