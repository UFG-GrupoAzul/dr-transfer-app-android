package br.ufg.inf.drtransferapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.ufg.inf.drtransferapp.api.ApiListener
import br.ufg.inf.drtransferapp.api.PatientApiServices
import br.ufg.inf.drtransferapp.api.makeRequest
import br.ufg.inf.drtransferapp.model.PatientRequestModel
import br.ufg.inf.drtransferapp.model.PatientResponseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PatientRepositoryImpl(private val apiServices: PatientApiServices) : PatientRepository {
    override fun callAllPatients(): LiveData<List<PatientResponseModel>> {
        val livedData = MutableLiveData<List<PatientResponseModel>>()
        val call = apiServices.getAllPatients()
        call.enqueue(object : Callback<List<PatientResponseModel>> {
            override fun onResponse(
                call: Call<List<PatientResponseModel>>,
                response: Response<List<PatientResponseModel>>
            ) {
                livedData.postValue(response.body())
            }

            override fun onFailure(call: Call<List<PatientResponseModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return livedData
    }



    override fun callCreatePatient(patient: PatientRequestModel) {
        TODO("Not yet implemented")
    }

    override fun callUpdatePatient(idPatient: String, patient: PatientRequestModel) {
        TODO("Not yet implemented")
    }

    override fun callDeletePatient(idPatient: String) {
        TODO("Not yet implemented")
    }


}