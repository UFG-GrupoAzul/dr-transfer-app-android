package br.ufg.inf.drtransferapp.home.viewmodel

import br.ufg.inf.drtransferapp.home.model.PatientRequestModel

sealed class PatientInterpreter {
    object CallListPatientsApi : PatientInterpreter()
    class CallCreatePatientApi(val patient: PatientRequestModel) : PatientInterpreter()
    class CallUpdatePatientApi(val idPatient: String, val patient: PatientRequestModel) : PatientInterpreter()
    class CallDeletePatientApi(val idPatient: String) : PatientInterpreter()
}