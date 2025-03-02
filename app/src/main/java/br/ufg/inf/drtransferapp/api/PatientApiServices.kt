package br.ufg.inf.drtransferapp.api

import br.ufg.inf.drtransferapp.model.PatientRequestModel
import br.ufg.inf.drtransferapp.model.PatientResponseModel
import okhttp3.Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PatientApiServices {
    @GET("patients") // patients é o endpoint que queremos acessar da nossa API
    fun getAllPatients(): Call<List<PatientResponseModel>> // List<PatientResponseModel> é o tipo de resposta que queremos receber da nossa API

    @POST("patients")
    fun createPatient(
        @Body patient: PatientRequestModel // @Body é usado para mapear o objeto patient para o corpo da requisição HTTP, neste caso, o corpo da requisição HTTP será o objeto patient do tipo PatientRequestModel
    ): Call<PatientResponseModel> // PatientResponseModel é o tipo de resposta que queremos receber da nossa API

    @PUT("patients/{id}")
    fun updatePatient(
        @Path("id") idPatient: String, // @Path é usado para mapear o parâmetro idPatient para o path da requisição HTTP, neste caso, o path da requisição HTTP será o id do paciente no qual o valor do id será substituído pelo valor do parâmetro idPatient
        @Body patient: PatientRequestModel // @Body é usado para mapear o objeto patient para o corpo da requisição HTTP, neste caso, o corpo da requisição HTTP será o objeto patient do tipo PatientRequestModel
    ): Call<PatientResponseModel>

    @DELETE("patients/{id}")
    fun deletePatient(
        @Path("id") idPatient: String // @Path é usado para mapear o parâmetro idPatient para o path da requisição HTTP, neste caso, o path da requisição HTTP será o id do paciente no qual o valor do id será substituído pelo valor do parâmetro idPatient
    ): Call<Response> // Response é o tipo de resposta que queremos receber da nossa API, como o métoddo delete não retorna nada, então a resposta será do tipo Response para a gente pegar o status da requisição se foi com sucesso ou com falha

}