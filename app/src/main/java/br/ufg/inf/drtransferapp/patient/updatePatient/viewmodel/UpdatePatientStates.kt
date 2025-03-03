package br.ufg.inf.drtransferapp.patient.updatePatient.viewmodel

import br.ufg.inf.drtransferapp.patient.commons.model.PatientResponseModel

sealed class UpdatePatientStates {
    data class OnSuccess(val patient: PatientResponseModel) : UpdatePatientStates()
    data class OnError(val error: Throwable) : UpdatePatientStates()
}