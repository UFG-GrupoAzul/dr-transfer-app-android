package br.ufg.inf.drtransferapp.patient.listPatients.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.ufg.inf.drtransferapp.R
import br.ufg.inf.drtransferapp.databinding.ActivityHomeBinding
import br.ufg.inf.drtransferapp.erro.ErroGenericoActivity
import br.ufg.inf.drtransferapp.patient.commons.model.PatientResponseModel
import br.ufg.inf.drtransferapp.patient.listPatients.view.adapter.ListPatientsAdapter
import br.ufg.inf.drtransferapp.patient.listPatients.viewmodel.PatientStates
import br.ufg.inf.drtransferapp.patient.listPatients.viewmodel.PatientFactory
import br.ufg.inf.drtransferapp.patient.listPatients.viewmodel.PatientInterpreter
import br.ufg.inf.drtransferapp.patient.listPatients.viewmodel.PatientVM
import br.ufg.inf.drtransferapp.patient.registerNewPatient.view.RegisterNewPatientActivity
import br.ufg.inf.drtransferapp.patient.updatePatient.view.UpdatePatientActivity

class ListPatientActivity : AppCompatActivity() {

    private val binding: ActivityHomeBinding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    private lateinit var patientList: List<PatientResponseModel>

    private val patientAdapter by lazy {
        ListPatientsAdapter(
            onClickEdit = {
                startActivity(UpdatePatientActivity.newInstance(this, it))
            },
            onClickDelete = { patient ->
                showAlertDelete(patient.person.name, callBack = {
                    if(it) {
                        viewModel.interpret(PatientInterpreter.CallDeletePatientApi(patient.id))
                    }
                })
            }
        )
    }

    private val viewModel: PatientVM by lazy {
        ViewModelProvider(this, PatientFactory()).get(
            PatientVM::class.java
        )
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

        viewModel.interpret(PatientInterpreter.CallLoading)

        initRecyclerView()
        setClickListener()
        initObserver()
    }

    override fun onStart() {
        super.onStart()
        viewModel.interpret(PatientInterpreter.CallListPatientsApi)
    }

    private fun initObserver() {
        viewModel.patient.observe(this) {
            when (it) {
                is PatientStates.OnLoading -> {
                    showShimmer()
                }

                is PatientStates.OnSuccessListPatients -> {
                    binding.tvEmptyList.visibility = View.GONE
                    patientAdapter.updateList(it.patients)
                    hideShimmer()
                }

                is PatientStates.OnSuccessDeletePatient -> {
                    showShimmer()
                    viewModel.interpret(PatientInterpreter.CallListPatientsApi)
                }

                is PatientStates.OnError -> {
                    startActivity(Intent(this, ErroGenericoActivity::class.java))
                }
            }
        }
    }

    private fun showShimmer() {
        // TODO: mostrar a animação do shimmer
    }

    private fun hideShimmer() {
        // TODO: ocultar a animação do shimmer
    }

    private fun setClickListener() {
        binding.fabAddPatient.setOnClickListener {
            startActivity(Intent(this, RegisterNewPatientActivity::class.java))
        }
    }

    private fun initRecyclerView() {
        binding.rvPatients.layoutManager = LinearLayoutManager(this)
        binding.rvPatients.setHasFixedSize(true)
        binding.rvPatients.adapter = patientAdapter
    }

    private fun showAlertDelete(patientName: String, callBack: (Boolean) -> Unit) {
        val builderAlertDialog = AlertDialog.Builder(this)
        builderAlertDialog.setTitle("Excluir Paciente")
        builderAlertDialog.setMessage("Deseja realmente excluir o paciente ${patientName}?")
        builderAlertDialog.setPositiveButton("Sim") { dialog, _ ->
            callBack.invoke(true)
            dialog.dismiss()
        }
        builderAlertDialog.setNegativeButton("Não") { dialog, _ ->
            callBack.invoke(false)
            dialog.dismiss()
        }
        builderAlertDialog.create().show()
    }
}