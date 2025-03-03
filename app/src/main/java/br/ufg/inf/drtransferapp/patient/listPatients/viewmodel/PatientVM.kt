package br.ufg.inf.drtransferapp.patient.listPatients.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.ufg.inf.drtransferapp.patient.commons.model.PatientRequestModel
import br.ufg.inf.drtransferapp.patient.listPatients.usecase.PatientUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PatientVM(private val useCase: PatientUseCase) : ViewModel() {
    private val _patient = MutableLiveData<PatientStates>()
    val patient: LiveData<PatientStates> = _patient

    fun interpret(interpreter: PatientInterpreter) {
        when (interpreter) {
            is PatientInterpreter.CallLoading ->
                _patient.postValue(PatientStates.OnLoading)

            is PatientInterpreter.CallListPatientsApi ->
                callUseCaseListAllPatients()

            is PatientInterpreter.CallUpdatePatientApi ->
                callUseCaseUpdatePatient(interpreter.idPatient, interpreter.patient)

            is PatientInterpreter.CallDeletePatientApi ->
                callUseCaseDeletePatient(interpreter.idPatient)
        }
    }

    private fun callUseCaseListAllPatients() {
        CoroutineScope(Dispatchers.IO).launch {
            _patient.postValue(useCase.listAllPatients())
        }
    }

    private fun callUseCaseUpdatePatient(idPatient: String, patient: PatientRequestModel) {
        CoroutineScope(Dispatchers.IO).launch {
            _patient.postValue(useCase.updatePatient(idPatient, patient))
        }
    }

    private fun callUseCaseDeletePatient(idPatient: String) {
        CoroutineScope(Dispatchers.IO).launch {
            _patient.postValue(useCase.deletePatient(idPatient))
        }
    }
}