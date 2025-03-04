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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
//        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initRecyclerView()
        setClickListener()
        initObserver()
    }

//    override fun onStart() {
//        super.onStart()
//        viewModel.interpret(PatientInterpreter.CallListPatientsApi)
//    }

    override fun onResume() {
        super.onResume()
        viewModel.interpret(PatientInterpreter.CallLoading)
    }

    private fun initObserver() {
        viewModel.patient.observe(this) {
            when (it) {
                is PatientStates.OnLoading -> {
                    CoroutineScope(Dispatchers.Main).launch {
                        showShimmer()
                        delay(3000)
                        viewModel.interpret(PatientInterpreter.CallListPatientsApi)
                    }
                }

                is PatientStates.OnSuccessListPatients -> {
                    if (it.patients.isEmpty()) {
                        binding.rvPatients.visibility = View.GONE
                        binding.fabAddPatient.visibility = View.GONE
                        binding.tvEmptyList.visibility = View.VISIBLE
                    } else {
                        binding.tvEmptyList.visibility = View.GONE
                        patientAdapter.updateList(it.patients)
                    }
                    hideShimmer()
                }

                is PatientStates.OnSuccessDeletePatient -> {
                    viewModel.interpret(PatientInterpreter.CallLoading)
                }

                is PatientStates.OnError -> {
                    startActivity(Intent(this, ErroGenericoActivity::class.java))
                }
            }
        }
    }

    private fun showShimmer() {
        binding.rvPatients.visibility = View.GONE
        binding.fabAddPatient.visibility = View.GONE
        binding.shimmer.visibility = View.VISIBLE
        binding.shimmer.startShimmer()
    }

    private fun hideShimmer() {
        binding.shimmer.stopShimmer()
        binding.shimmer.visibility = View.GONE
        binding.rvPatients.visibility = View.VISIBLE
        binding.fabAddPatient.visibility = View.VISIBLE
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