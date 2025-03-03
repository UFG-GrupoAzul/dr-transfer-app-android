package br.ufg.inf.drtransferapp.patient.registerNewPatient.viewmodel

import br.ufg.inf.drtransferapp.patient.commons.model.PatientRequestModel

sealed class RegisterNewPatientInterpreter {
    class CallCreatePatientApi(val patient: PatientRequestModel) : RegisterNewPatientInterpreter()
}