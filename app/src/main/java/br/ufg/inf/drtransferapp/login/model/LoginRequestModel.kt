package br.ufg.inf.drtransferapp.login.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginRequestModel(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
) : Parcelable
