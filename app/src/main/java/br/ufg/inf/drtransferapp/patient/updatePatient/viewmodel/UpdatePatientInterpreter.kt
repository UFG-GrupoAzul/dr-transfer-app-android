package br.ufg.inf.drtransferapp.patient.updatePatient.viewmodel

import br.ufg.inf.drtransferapp.patient.commons.model.PatientRequestModel
import br.ufg.inf.drtransferapp.patient.listPatients.viewmodel.PatientInterpreter

sealed class UpdatePatientInterpreter {
    class CallUpdatePatientApi(val idPatient: String, val patient: PatientRequestModel) : UpdatePatientInterpreter()
}