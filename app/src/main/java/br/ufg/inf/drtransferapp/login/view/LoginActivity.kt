package br.ufg.inf.drtransferapp.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import br.ufg.inf.drtransferapp.R
import br.ufg.inf.drtransferapp.databinding.ActivityLoginBinding
import br.ufg.inf.drtransferapp.login.model.LoginRequestModel
import br.ufg.inf.drtransferapp.login.model.LoginResponseModel
import br.ufg.inf.drtransferapp.login.viewmodel.LoginFactory
import br.ufg.inf.drtransferapp.login.viewmodel.LoginInterpreter
import br.ufg.inf.drtransferapp.login.viewmodel.LoginStates
import br.ufg.inf.drtransferapp.login.viewmodel.LoginVM
import br.ufg.inf.drtransferapp.patient.listPatients.view.ListPatientActivity

class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val viewModel: LoginVM by lazy {
        ViewModelProvider(
            this,
            LoginFactory()
        )[LoginVM::class.java]
    }

    private lateinit var loginResponse: LoginResponseModel

    private var email: String = ""
    private var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setClickListeners()
        initObserver()
    }

    private fun setClickListeners() {
        binding.btnLogar.setOnClickListener {
            email = binding.etEmail.text.toString()
            password = binding.etPassword.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val loginRequest = LoginRequestModel(email = email, password = password)
            viewModel.interpret(LoginInterpreter.CallLogin(loginRequest))
        }

        binding.tvCriarConta.setOnClickListener {
            Toast.makeText(this, "Funcionalidade não implementada.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initObserver() {
        viewModel.login.observe(this) {
            when (it) {
                is LoginStates.OnSuccessLogin -> {
                    loginResponse = it.loginResponseModel
                    startActivity(Intent(this, ListPatientActivity::class.java))
                    finish()
                }
                is LoginStates.OnError -> {
                    Toast.makeText(this, "Email ou senha inválido!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}