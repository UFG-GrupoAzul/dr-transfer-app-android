package br.ufg.inf.drtransferapp.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    /* Neste métoddo nós criamos uma instância do Retrofit e retornamos ela dizendo que ela será criada a partir da interface PatientApiServices. */
    fun createService(): PatientApiServices {
        return getRetrofitInstance().create(PatientApiServices::class.java)
    }

    companion object {
        private lateinit var INSTANCE: Retrofit // Utilizo essa estratégia para garantir que a instância será inicializada apenas uma vez, ou seja, fazer uso do design pattern Singleton
        private const val BASE_URL = "http://10.0.2.2:3000/" // O IP 10.0.2.2 é uma representação do localhost na rede Android, ou seja, como o emulador usa uma rede virtual, e portanto, "diferente" da rede da máquina local, o 10.0.2.2 é como se fosse o IP da máquina local.

        /* Neste métoddo a gente configura nossa instância do Retrofit e retornamos ela. */
        private fun getRetrofitInstance(): Retrofit {
            val http = OkHttpClient.Builder() // aqui nos configuramos um cliente HTTP para interagir com a API
            if (!::INSTANCE.isInitialized) { // aqui nos certificamos que a instância não foi inicializada
                synchronized(RetrofitClient::class.java) {
                    INSTANCE = Retrofit.Builder() // e aqui nos construímos a instância do Retrofit
                        .baseUrl(BASE_URL) // onde aqui informamos a URL base da API
                        .client(http.build()) // e aqui nos informamos o cliente HTTP que criamos, ou seja, o orquestrador de requisições HTTP que será utilizado para interagir com a nossa API
                        .addConverterFactory(GsonConverterFactory.create()) // e aqui nos informamos o conversor de JSON que será utilizado para converter as respostas da API em objetos Kotlin
                        .build() // e aqui nos construímos a instância do Retrofit, ou seja, nossa instância do Retrofit será criada e pronta para ser utilizada com as configurações anteriores que passamos para nosso Retrofit
                }
            }
            return INSTANCE // e aqui nos retornamos a instância do Retrofit que criamos, caso já tenha sido inicializada será retornada a que já foi instanciada antes.
        }
    }
}