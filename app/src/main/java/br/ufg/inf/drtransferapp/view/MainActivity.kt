package br.ufg.inf.drtransferapp.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.ufg.inf.drtransferapp.R
import br.ufg.inf.drtransferapp.api.RetrofitClient
import br.ufg.inf.drtransferapp.model.PatientRequestModel
import br.ufg.inf.drtransferapp.model.PatientResponseModel
import br.ufg.inf.drtransferapp.repository.PatientRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var res : PatientResponseModel
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
        CoroutineScope(Dispatchers.IO).launch {
            repository.callCreatePatient(PatientRequestModel(
                nome = "Sergio Cria Teste Paciente",
                cpf = "12345678910",
                telefone = "77777777",
                genero = "MALE",
                tipoSanguineo = "A_NEGATIVE",
                dataNascimento = "2024-09-26T00:00:00.000Z"

            ))
        }
    }
}