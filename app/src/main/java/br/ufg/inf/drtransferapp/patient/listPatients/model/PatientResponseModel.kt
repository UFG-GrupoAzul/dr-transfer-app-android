package br.ufg.inf.drtransferapp.patient.listPatients.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize // Parcelize é uma forma mais eficiente do que o Serialization de serializar e deserializar objetos
data class PatientResponseModel(
    @SerializedName("id") val id: String, // o @SerializedName é usado para mapear o nome do campo da API com o nome do campo da classe
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String,
    @SerializedName("birthDate") val birthDate: String,
    @SerializedName("bloodType") val bloodType: String,
    @SerializedName("person") val person: Person,
) : Parcelable // Quando se usa a annotation @Parcelize é preciso fazer uso da interface Parcelable

@Parcelize
data class Person(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("cpf") val cpf: String,
    @SerializedName("dType") val dType: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("userId") val userId: String,
    @SerializedName("gender") val gender: String
) : Parcelable
