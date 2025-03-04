package br.ufg.inf.drtransferapp.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.ufg.inf.drtransferapp.login.model.LoginRequestModel
import br.ufg.inf.drtransferapp.login.usecase.LoginUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginVM(private val useCase: LoginUseCase) : ViewModel() {

    private val _login = MutableLiveData<LoginStates>()
    val login: LiveData<LoginStates> = _login

    fun interpret(interpreter: LoginInterpreter) {
        when (interpreter) {
            is LoginInterpreter.CallLogin ->
                callLogin(interpreter.loginRequest)
        }
    }

    private fun callLogin(loginRequest: LoginRequestModel) {
        CoroutineScope(Dispatchers.IO).launch {
            _login.postValue(useCase.login(loginRequest))
        }
    }
}