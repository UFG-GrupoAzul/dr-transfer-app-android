package br.ufg.inf.drtransferapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize // Parcelize é uma forma mais eficiente do que o Serialization de serializar e deserializar objetos
data class PatientRequestModel(
    @SerializedName("name") val nome: String, // o @SerializedName é usado para mapear o nome do campo da API com o nome do campo da classe
    @SerializedName("cpf") val cpf: String,
    @SerializedName("phone") var telefone: String,
    @SerializedName("gender") val genero: String,
    @SerializedName("birthDate") val dataNascimento: String,
    @SerializedName("bloodType") val tipoSanguineo: String
) : Parcelable // Quando se usa a annotation @Parcelize é preciso fazer uso da interface Parcelable
