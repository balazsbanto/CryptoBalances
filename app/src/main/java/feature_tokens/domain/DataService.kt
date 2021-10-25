package feature_tokens.domain

import com.example.cryptobalances.core.response.ERC20Token
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface DataService {

    @GET("api")
    fun getERC20Tokens(@Query("module") module: String?,
                          @Query("action") action: String?,
                          @Query("contractaddress") contractaddress: String?,
                          @Query("address") address: String?,
                          @Query("tag") tag: String?,
                          @Query("apikey") apikey: String?): Single<ERC20Token>
}