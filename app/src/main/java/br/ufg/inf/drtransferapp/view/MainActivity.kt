package br.ufg.inf.drtransferapp.view

import android.app.job.JobService
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.ufg.inf.drtransferapp.R
import br.ufg.inf.drtransferapp.api.ApiListener
import br.ufg.inf.drtransferapp.api.RetrofitClient
import br.ufg.inf.drtransferapp.model.PatientResponseModel
import br.ufg.inf.drtransferapp.repository.PatientRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    private lateinit var res : List<PatientResponseModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val service = RetrofitClient().createService()
        val repository = PatientRepositoryImpl(service)
        repository.callAllPatients().observe(this) {
            res = it
        }
    }
}