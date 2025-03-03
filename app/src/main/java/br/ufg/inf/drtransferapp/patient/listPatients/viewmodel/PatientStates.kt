package br.ufg.inf.drtransferapp.patient.listPatients.viewmodel

import br.ufg.inf.drtransferapp.patient.commons.model.PatientResponseModel

sealed class PatientStates {
    object OnLoading : PatientStates()
    data class OnSuccessListPatients(val patients: List<PatientResponseModel>) : PatientStates()
    data class OnSuccessDeletePatient(val isDeleted: Boolean) : PatientStates()
    data class OnError(val error: Throwable) : PatientStates()
}