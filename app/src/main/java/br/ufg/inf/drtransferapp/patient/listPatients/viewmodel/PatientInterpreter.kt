package br.ufg.inf.drtransferapp.patient.listPatients.viewmodel

import br.ufg.inf.drtransferapp.patient.commons.model.PatientRequestModel

sealed class PatientInterpreter {
    object CallLoading : PatientInterpreter()
    object CallListPatientsApi : PatientInterpreter()
    class CallDeletePatientApi(val idPatient: String) : PatientInterpreter()
}