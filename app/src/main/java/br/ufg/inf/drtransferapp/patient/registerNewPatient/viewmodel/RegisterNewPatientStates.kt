package br.ufg.inf.drtransferapp.patient.registerNewPatient.viewmodel

import br.ufg.inf.drtransferapp.patient.commons.model.PatientResponseModel

sealed class RegisterNewPatientStates {
    data class OnSuccess(val patient: PatientResponseModel) : RegisterNewPatientStates()
    data class OnError(val error: Throwable) : RegisterNewPatientStates()
}