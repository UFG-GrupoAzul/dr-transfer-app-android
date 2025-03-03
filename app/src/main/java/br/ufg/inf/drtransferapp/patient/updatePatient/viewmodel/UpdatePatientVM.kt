package br.ufg.inf.drtransferapp.patient.updatePatient.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.ufg.inf.drtransferapp.patient.commons.model.PatientRequestModel
import br.ufg.inf.drtransferapp.patient.updatePatient.usecase.UpdatePatientUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdatePatientVM(private val useCase: UpdatePatientUseCase) : ViewModel() {
    private val _updatePatient = MutableLiveData<UpdatePatientStates>()
    val updatePatient: LiveData<UpdatePatientStates> = _updatePatient

    fun intepret(interpreter: UpdatePatientInterpreter) {
        when (interpreter) {
            is UpdatePatientInterpreter.CallUpdatePatientApi ->
                callUseCaseUpdatePatient(interpreter.idPatient, interpreter.patient)
        }
    }

    private fun callUseCaseUpdatePatient(idPatient: String, patient: PatientRequestModel) {
        CoroutineScope(Dispatchers.IO).launch {
            _updatePatient.postValue(useCase.updatePatient(idPatient, patient))
        }
    }
}