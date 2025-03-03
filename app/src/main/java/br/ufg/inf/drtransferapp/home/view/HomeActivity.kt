package br.ufg.inf.drtransferapp.home.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import br.ufg.inf.drtransferapp.R
import br.ufg.inf.drtransferapp.home.model.PatientResponseModel
import br.ufg.inf.drtransferapp.home.usecase.PatientStates
import br.ufg.inf.drtransferapp.home.viewmodel.PatientFactory
import br.ufg.inf.drtransferapp.home.viewmodel.PatientInterpreter
import br.ufg.inf.drtransferapp.home.viewmodel.PatientVM

class HomeActivity : AppCompatActivity() {

    private lateinit var res: List<PatientResponseModel>

    private val viewModel: PatientVM by lazy {
        ViewModelProvider(this, PatientFactory()).get(
            PatientVM::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel.interpret(PatientInterpreter.CallListPatientsApi)

        initObserver()
    }

    private fun initObserver() {
        viewModel.patient.observe(this) {
            when (it) {
                is PatientStates.OnSuccessListPatients -> {
                    res = it.patients
                }
                else -> {}
            }
        }
    }
}