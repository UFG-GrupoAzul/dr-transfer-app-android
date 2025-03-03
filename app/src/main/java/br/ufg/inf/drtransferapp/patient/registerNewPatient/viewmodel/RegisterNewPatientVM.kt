package br.ufg.inf.drtransferapp.patient.registerNewPatient.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.ufg.inf.drtransferapp.patient.commons.model.PatientRequestModel
import br.ufg.inf.drtransferapp.patient.registerNewPatient.usecase.RegisterNewPatientUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterNewPatientVM(private val useCase: RegisterNewPatientUseCase) : ViewModel() {
    private val _patient = MutableLiveData<RegisterNewPatientStates>()
    val patient: LiveData<RegisterNewPatientStates> = _patient

    fun interpret(interpreter: RegisterNewPatientInterpreter) {
        when (interpreter) {
            is RegisterNewPatientInterpreter.CallCreatePatientApi ->
                callUseCaseCreatePatient(interpreter.patient)
        }
    }

    private fun callUseCaseCreatePatient(patient: PatientRequestModel) {
        CoroutineScope(Dispatchers.IO).launch {
            _patient.postValue(useCase.createPatient(patient))
        }
    }
}