package br.ufg.inf.drtransferapp.patient.registerNewPatient.view

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.MultiAutoCompleteTextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import br.ufg.inf.drtransferapp.R
import br.ufg.inf.drtransferapp.databinding.ActivityRegisterNewPatientBinding
import br.ufg.inf.drtransferapp.erro.ErroGenericoActivity
import br.ufg.inf.drtransferapp.patient.commons.model.PatientRequestModel
import br.ufg.inf.drtransferapp.patient.commons.utils.enum.Genero
import br.ufg.inf.drtransferapp.patient.commons.utils.enum.TipoSanguineo
import br.ufg.inf.drtransferapp.patient.registerNewPatient.viewmodel.RegisterNewPatientFactory
import br.ufg.inf.drtransferapp.patient.registerNewPatient.viewmodel.RegisterNewPatientInterpreter
import br.ufg.inf.drtransferapp.patient.registerNewPatient.viewmodel.RegisterNewPatientStates
import br.ufg.inf.drtransferapp.patient.registerNewPatient.viewmodel.RegisterNewPatientVM
import com.google.android.material.textfield.TextInputLayout

class RegisterNewPatientActivity : AppCompatActivity() {
    private val binding: ActivityRegisterNewPatientBinding by lazy {
        ActivityRegisterNewPatientBinding.inflate(
            layoutInflater
        )
    }

    private lateinit var autoCompleteTipoSangue: AutoCompleteTextView
    private lateinit var autoCompleteGenero: AutoCompleteTextView

    private val viewmodel: RegisterNewPatientVM by lazy {
        ViewModelProvider(
            this,
            RegisterNewPatientFactory()
        ).get(RegisterNewPatientVM::class.java)
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
        autoCompleteTipoSangue = binding.autoCompleteTipoSangue
        val options= listOf(
            "A-Positivo",
            "A-Negativo",
            "B-Positivo",
            "B-Negativo",
            "AB-Positivo",
            "AB-Negativo",
            "O-Positivo",
            "O-Negativo"
        )
        val adpater = ArrayAdapter(this, android.R.layout.simple_list_item_1, options)
        autoCompleteTipoSangue.setAdapter(adpater)

        autoCompleteGenero = binding.autoCompleteGenero
        val optionsGenero = listOf("Masculino", "Feminino")
        val adpaterGenero = ArrayAdapter(this, android.R.layout.simple_list_item_1, optionsGenero)
        autoCompleteGenero.setAdapter(adpaterGenero)

        binding.tbAppBar.toolBar.title = "Adicionar Novo Paciente"
    }

    private fun initObserver() {
        viewmodel.patient.observe(this) {
            when(it) {
                is RegisterNewPatientStates.OnSuccess -> {
                    showAlertSuccess()
                }
                is RegisterNewPatientStates.OnError -> {
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
            if (binding.etNome.text.isNullOrBlank() || binding.etDataAniversario.text.isNullOrBlank()
                || binding.etTelefone.text.isNullOrBlank() || binding.etCpf.text.isNullOrBlank()
                || isItemDropdownSelected(autoCompleteGenero,binding.tfGenero) && autoCompleteGenero.text.toString().isBlank()
                || isItemDropdownSelected(autoCompleteTipoSangue,binding.tfTipoSangue) && autoCompleteTipoSangue.text.toString().isBlank()
            ) {
                showAlertAtention()
            } else {
                val nome = binding.etNome.text.toString()
                val dataAniversario = binding.etDataAniversario.text.toString()
                val telefone = binding.etTelefone.text.toString()
                val cpf = binding.etCpf.text.toString()
                val genero = fixGender(binding.autoCompleteGenero.text.toString())
                val tipoSangue = fixBloodType(binding.autoCompleteTipoSangue.text.toString())

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

        autoCompleteTipoSangue.setOnClickListener {
            autoCompleteTipoSangue.showDropDown()
        }

        autoCompleteGenero.setOnClickListener {
            autoCompleteGenero.showDropDown()
        }
    }

    private fun isItemDropdownSelected(autoCompleteTextView: AutoCompleteTextView, textInputLayout: TextInputLayout): Boolean {
        return if (autoCompleteTextView.text.toString().isNullOrBlank()) {
            textInputLayout.error  = "Selecione uma opção"
            true
        } else {
            textInputLayout.error = null
            false
        }
    }

    private fun fixBloodType(autoCompleteTipoSangue: String) : String {
        return when (autoCompleteTipoSangue) {
            "A-Positivo" -> TipoSanguineo.A_POSITIVE.toString()
            "A-Negativo" -> TipoSanguineo.A_NEGATIVE.toString()
            "B-Positivo" -> TipoSanguineo.B_POSITIVE.toString()
            "B-Negativo" -> TipoSanguineo.B_NEGATIVE.toString()
            "AB-Positivo" -> TipoSanguineo.AB_POSITIVE.toString()
            "AB-Negativo" -> TipoSanguineo.AB_NEGATIVE.toString()
            "O-Positivo" -> TipoSanguineo.O_POSITIVE.toString()
            "O-Negativo" -> TipoSanguineo.O_NEGATIVE.toString()
            else -> ""
        }
    }

    private fun fixGender(autoCompleteGenero: String): String {
        return when (autoCompleteGenero) {
            "Masculino" -> Genero.MALE.toString()
            "Feminino" -> Genero.FEMALE.toString()
            else -> ""
        }
    }

    private fun showAlertAtention() {
        val buildAlertDialog = AlertDialog.Builder(this)
        buildAlertDialog.setTitle("Atenção!")
        buildAlertDialog.setMessage("Preencha todos os campos")
        buildAlertDialog.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        buildAlertDialog.create().show()
    }

    private fun showAlertSuccess() {
        val buildAlertDialog = AlertDialog.Builder(this)
        buildAlertDialog.setMessage("Paciente criado com sucesso")
        buildAlertDialog.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
            finish()
        }
        buildAlertDialog.create().show()
    }
}