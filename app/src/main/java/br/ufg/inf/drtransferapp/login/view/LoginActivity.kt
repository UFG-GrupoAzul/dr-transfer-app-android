package br.ufg.inf.drtransferapp.login.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import br.ufg.inf.drtransferapp.R
import br.ufg.inf.drtransferapp.databinding.ActivityLoginBinding
import br.ufg.inf.drtransferapp.login.viewmodel.LoginFactory
import br.ufg.inf.drtransferapp.login.viewmodel.LoginStates
import br.ufg.inf.drtransferapp.login.viewmodel.LoginVM

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initObserver()
    }

    private fun initObserver() {
        viewModel.login.observe(this) {
            when (it) {
                is LoginStates.OnSuccessLogin -> {
                    // TODO: Chamar lista de pacientes
                }
                is LoginStates.OnError -> {
                    // TODO: Mostrar erro
                }
            }
        }
    }
}