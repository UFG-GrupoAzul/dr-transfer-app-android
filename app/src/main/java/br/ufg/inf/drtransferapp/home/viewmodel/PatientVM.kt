package br.ufg.inf.drtransferapp.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.ufg.inf.drtransferapp.home.model.PatientRequestModel
import br.ufg.inf.drtransferapp.home.usecase.PatientUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PatientVM(private val useCase: PatientUseCase) : ViewModel() {
    private val _patient = MutableLiveData<PatientStates>()
    val patient: LiveData<PatientStates> = _patient

    fun interpret(interpreter: PatientInterpreter) {
        when (interpreter) {
            is PatientInterpreter.CallListPatientsApi ->
                callUseCaseListAllPatients()

            is PatientInterpreter.CallCreatePatientApi ->
                callUseCaseCreatePatient(interpreter.patient)

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

    private fun callUseCaseCreatePatient(patient: PatientRequestModel) {
        CoroutineScope(Dispatchers.IO).launch {
            _patient.postValue(useCase.createPatient(patient))
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