package com.example.cryptobalances.core.network

import com.example.cryptobalances.core.network.response.ERC20Token
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("api")
    fun getERC20Tokens(@Query("module") module: String?,
                          @Query("action") action: String?,
                          @Query("contractaddress") contractaddress: String?,
                          @Query("address") address: String?,
                          @Query("tag") tag: String?,
                          @Query("apikey") apikey: String?): Single<ERC20Token>
}