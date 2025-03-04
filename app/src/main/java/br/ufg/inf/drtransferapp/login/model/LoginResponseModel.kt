package br.ufg.inf.drtransferapp.login.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponseModel(
    @SerializedName("user") val user: User,
    @SerializedName("token") val token: String
): Parcelable

@Parcelize
data class User(
    @SerializedName("id") val id: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
): Parcelable
