package br.ufg.inf.drtransferapp.patient.updatePatient.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import br.ufg.inf.drtransferapp.R
import br.ufg.inf.drtransferapp.databinding.ActivityUpdatePatientBinding
import br.ufg.inf.drtransferapp.erro.ErroGenericoActivity
import br.ufg.inf.drtransferapp.patient.commons.model.PatientRequestModel
import br.ufg.inf.drtransferapp.patient.commons.model.PatientResponseModel
import br.ufg.inf.drtransferapp.patient.updatePatient.viewmodel.UpdatePatientFactory
import br.ufg.inf.drtransferapp.patient.updatePatient.viewmodel.UpdatePatientInterpreter
import br.ufg.inf.drtransferapp.patient.updatePatient.viewmodel.UpdatePatientStates
import br.ufg.inf.drtransferapp.patient.updatePatient.viewmodel.UpdatePatientVM

class UpdatePatientActivity : AppCompatActivity() {

    private val binding: ActivityUpdatePatientBinding by lazy {
        ActivityUpdatePatientBinding.inflate(
            layoutInflater
        )
    }

    private val viewModel: UpdatePatientVM by lazy {
        ViewModelProvider(
            this,
            UpdatePatientFactory()
        ).get(UpdatePatientVM::class.java)
    }

    private val patient: PatientResponseModel by lazy {
        intent.getParcelableExtra(PATIENT, PatientResponseModel::class.java)!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        iniViews()
        setClickListener()
        initObserver()
    }

    private fun iniViews() {
        binding.etNome.setText(patient.person.name)
        binding.etTelefone.setText(patient.person.phone)
        binding.tbAppBar.toolBar.title = "Atualizar Dados do Paciente"
    }

    private fun initObserver() {
        viewModel.updatePatient.observe(this) {
            when(it) {
                is UpdatePatientStates.OnSuccess -> {
                    showAlertSuccess()
                }
                is UpdatePatientStates.OnError -> {
                    startActivity(Intent(this, ErroGenericoActivity::class.java))
                    finish()
                }
            }
        }
    }

    private fun setClickListener() {
        binding.tbAppBar.toolBar.setNavigationOnClickListener {
            finish()
        }

        binding.btnConfirmar.setOnClickListener {
            val updatePatient = PatientRequestModel(
                nome = binding.etNome.text.toString(),
                cpf = patient.person.cpf,
                telefone = binding.etTelefone.text.toString(),
                genero = patient.person.gender,
                dataNascimento = patient.birthDate,
                tipoSanguineo = patient.bloodType
            )
            viewModel.intepret(UpdatePatientInterpreter.CallUpdatePatientApi(patient.id, updatePatient))
        }
    }

    private fun showAlertSuccess() {
        val buildAlertDialog = AlertDialog.Builder(this)
        buildAlertDialog.setMessage("Paciente atualizado com sucesso")
        buildAlertDialog.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
            finish()
        }
        buildAlertDialog.create().show()
    }

    companion object {
        const val PATIENT = "patient"

        fun newInstance(context: Context, patient: PatientResponseModel) =
            Intent(context, UpdatePatientActivity::class.java).apply {
                putExtra(PATIENT, patient)
            }
    }
}