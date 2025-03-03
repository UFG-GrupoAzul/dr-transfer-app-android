package br.ufg.inf.drtransferapp.patient.registerNewPatient.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import br.ufg.inf.drtransferapp.R
import br.ufg.inf.drtransferapp.databinding.ActivityRegisterNewPatientBinding
import br.ufg.inf.drtransferapp.patient.commons.model.PatientRequestModel
import br.ufg.inf.drtransferapp.patient.registerNewPatient.viewmodel.RegisterNewPatientFactory
import br.ufg.inf.drtransferapp.patient.registerNewPatient.viewmodel.RegisterNewPatientInterpreter
import br.ufg.inf.drtransferapp.patient.registerNewPatient.viewmodel.RegisterNewPatientStates
import br.ufg.inf.drtransferapp.patient.registerNewPatient.viewmodel.RegisterNewPatientVM

class RegisterNewPatientActivity : AppCompatActivity() {
    private val binding: ActivityRegisterNewPatientBinding by lazy {
        ActivityRegisterNewPatientBinding.inflate(
            layoutInflater
        )
    }

    private val viewmodel: RegisterNewPatientVM by lazy {
        ViewModelProvider(
            this,
            RegisterNewPatientFactory()
        ).get(RegisterNewPatientVM::class.java)
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

        setClickListener()
        initObserver()
    }

    private fun initObserver() {
        viewmodel.patient.observe(this) {
            when(it) {
                is RegisterNewPatientStates.OnSuccess -> {
                    Toast.makeText(this, "Paciente criado com sucesso", Toast.LENGTH_SHORT).show()
                    finish()
                }
                is RegisterNewPatientStates.OnError -> {
                    Toast.makeText(this, it.error.message, Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }

    private fun setClickListener() {
        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.btnConfirmar.setOnClickListener {
            if (binding.etNome.text.isNullOrBlank() || binding.etDataAniversario.text.isNullOrBlank()
                || binding.etTelefone.text.isNullOrBlank() || binding.etCpf.text.isNullOrBlank()
                || binding.etGenero.text.isNullOrBlank() || binding.etTipoSangue.text.isNullOrBlank()
            ) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                val nome = binding.etNome.text.toString()
                val dataAniversario = binding.etDataAniversario.text.toString()
                val telefone = binding.etTelefone.text.toString()
                val cpf = binding.etCpf.text.toString()
                val genero = binding.etGenero.text.toString().uppercase()
                val tipoSangue = binding.etTipoSangue.text.toString()

                val patient =
                    PatientRequestModel(
                        nome = nome,
                        dataNascimento = dataAniversario,
                        telefone = telefone,
                        cpf = cpf,
                        genero = genero,
                        tipoSanguineo = tipoSangue
                    )

                viewmodel.interpret(RegisterNewPatientInterpreter.CallCreatePatientApi(patient))
            }
        }
    }
}