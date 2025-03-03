package br.ufg.inf.drtransferapp.home.view

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.ufg.inf.drtransferapp.R
import br.ufg.inf.drtransferapp.databinding.ActivityHomeBinding
import br.ufg.inf.drtransferapp.home.model.PatientResponseModel
import br.ufg.inf.drtransferapp.home.view.adapter.ListPatientsAdapter
import br.ufg.inf.drtransferapp.home.viewmodel.PatientStates
import br.ufg.inf.drtransferapp.home.viewmodel.PatientFactory
import br.ufg.inf.drtransferapp.home.viewmodel.PatientInterpreter
import br.ufg.inf.drtransferapp.home.viewmodel.PatientVM

class HomeActivity : AppCompatActivity() {

    private val binding : ActivityHomeBinding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    private lateinit var patientList: List<PatientResponseModel>

    private val patientAdapter by lazy {
        ListPatientsAdapter(
            onClickEdit = {
                // TODO: chamar a tela de edição do paciente
            },
            onClickDelete = { patient ->
                viewModel.interpret(PatientInterpreter.CallDeletePatientApi(patient.id))
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
                    patientList = it.patients
                    patientAdapter.updateList(patientList)
                    hideShimmer()
                }
                is PatientStates.OnSuccessDeletePatient -> {
                    showShimmer()
                    viewModel.interpret(PatientInterpreter.CallListPatientsApi)
                }
                else -> {}
            }
        }
    }

    private fun showShimmer() {
        // TODO: mostrar a animação do shimmer
    }

    private fun hideShimmer(){
        // TODO: ocultar a animação do shimmer
    }

    private fun initRecyclerView() {
        binding.rvPatients.layoutManager = LinearLayoutManager(this)
        binding.rvPatients.setHasFixedSize(true)
        binding.rvPatients.adapter = patientAdapter
    }
}