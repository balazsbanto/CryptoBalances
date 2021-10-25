package com.example.cryptobalances.core.network

import com.example.cryptobalances.core.utils.ConstData
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestClient {

    private var baseUrl: String = ConstData.BASE_URL
    private var networkService: NetworkService

    init {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        networkService = retrofit.create(NetworkService::class.java)
    }

    fun getNetworkService(): NetworkService {
        return networkService
    }

}