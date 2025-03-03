package br.ufg.inf.drtransferapp.viewmodel

import br.ufg.inf.drtransferapp.model.PatientResponseModel

sealed class PatientInterpreter {
    object CallListPatientsApi : PatientInterpreter()
}