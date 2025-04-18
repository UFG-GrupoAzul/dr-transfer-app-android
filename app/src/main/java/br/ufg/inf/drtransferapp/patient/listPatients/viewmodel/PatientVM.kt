package br.ufg.inf.drtransferapp.patient.listPatients.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.ufg.inf.drtransferapp.patient.commons.model.PatientRequestModel
import br.ufg.inf.drtransferapp.patient.listPatients.usecase.PatientUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PatientVM(private val useCase: PatientUseCase) : ViewModel() {
    private val _patient = MutableLiveData<PatientStates>()
    val patient: LiveData<PatientStates> = _patient

    fun interpret(interpreter: PatientInterpreter) {
        when (interpreter) {
            is PatientInterpreter.CallLoading ->
                callLoadingShimmer()

            is PatientInterpreter.CallListPatientsApi ->
                callUseCaseListAllPatients()

            is PatientInterpreter.CallDeletePatientApi ->
                callUseCaseDeletePatient(interpreter.idPatient)
        }
    }

    private fun callLoadingShimmer() {
            _patient.postValue(PatientStates.OnLoading)
    }

    private fun callUseCaseListAllPatients() {
        CoroutineScope(Dispatchers.IO).launch {
            _patient.postValue(useCase.listAllPatients())
        }
    }

    private fun callUseCaseDeletePatient(idPatient: String) {
        CoroutineScope(Dispatchers.IO).launch {
            _patient.postValue(useCase.deletePatient(idPatient))
        }
    }
}