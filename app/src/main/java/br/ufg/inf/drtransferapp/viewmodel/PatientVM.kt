package br.ufg.inf.drtransferapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.ufg.inf.drtransferapp.model.PatientResponseModel
import br.ufg.inf.drtransferapp.usecase.PatientStates
import br.ufg.inf.drtransferapp.usecase.PatientUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PatientVM(private val useCase: PatientUseCase): ViewModel() {
    private val _patient = MutableLiveData<PatientStates>()
    val patient : LiveData<PatientStates> = _patient

    fun interpret(interpreter: PatientInterpreter) {
        when(interpreter) {
            is PatientInterpreter.CallListPatientsApi ->
                callUseCaseListAllPatients()
        }
    }

    private fun callUseCaseListAllPatients() {
        CoroutineScope(Dispatchers.IO).launch {
            _patient.postValue(useCase.listAllPatients())
        }
    }
}