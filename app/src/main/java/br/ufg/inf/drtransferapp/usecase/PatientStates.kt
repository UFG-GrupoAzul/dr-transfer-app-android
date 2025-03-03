package br.ufg.inf.drtransferapp.usecase

import br.ufg.inf.drtransferapp.model.PatientResponseModel

sealed class PatientStates {
    object OnLoading : PatientStates()
    data class OnSuccessListPatients(val patients: List<PatientResponseModel>) : PatientStates()
    data class OnSuccessPatient(val patient: PatientResponseModel) : PatientStates()
    data class OnSuccessDeletePatient(val isDeleted: Boolean) : PatientStates()
    data class OnError(val error: Throwable) : PatientStates()

}