package br.ufg.inf.drtransferapp.home.viewmodel

import br.ufg.inf.drtransferapp.home.model.PatientResponseModel

sealed class PatientInterpreter {
    object CallListPatientsApi : PatientInterpreter()
}