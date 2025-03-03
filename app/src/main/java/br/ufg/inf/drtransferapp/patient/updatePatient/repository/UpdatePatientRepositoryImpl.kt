package br.ufg.inf.drtransferapp.patient.updatePatient.repository

import br.ufg.inf.drtransferapp.network.service.PatientApiServices
import br.ufg.inf.drtransferapp.patient.commons.model.PatientRequestModel
import br.ufg.inf.drtransferapp.patient.commons.model.PatientResponseModel
import br.ufg.inf.drtransferapp.patient.commons.utils.extension.orElse

class UpdatePatientRepositoryImpl(private val apiServices: PatientApiServices): UpdatePatientRepository {
    override suspend fun callUpdatePatient(
        idPatient: String,
        patient: PatientRequestModel
    ): Result<PatientResponseModel> {
        val result: Result<PatientResponseModel>
        return try {
            val response = apiServices.updatePatient(idPatient, patient)
            result = if( response.code() != 200 && response.code() != 201){
                Result.failure(Throwable(response.message()))
            } else {
                response.body()?.let {
                    Result.success(it)
                }.orElse {
                    Result.failure(Throwable("Falha ao atualizar paciente"))
                }
            }
            result
        } catch (exception: Throwable) {
            Result.failure(exception)
        }
    }
}