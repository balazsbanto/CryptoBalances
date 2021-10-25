package feature_tokens.domain

import android.content.Context
import com.example.cryptobalances.R
import com.example.cryptobalances.core.network.NetworkService
import com.example.cryptobalances.core.network.response.ERC20Token
import com.example.cryptobalances.core.utils.ConstData
import feature_tokens.view.TokensViewState
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class TokensInteractor(private val networkService: NetworkService, private val context:Context) {

    val tokenSubject: PublishSubject<TokensViewState.MatchedToken> = PublishSubject.create()


    fun initEmptyState(): Observable<TokensViewState> {
        val jsonContent = context.resources.openRawResource(R.raw.available_tokens)
            .bufferedReader().use { it.readText() }

        val answer = JSONObject(jsonContent)
        return Observable.just(TokensViewState.EmtpyState())
    }

    fun searchIntent(searchStr:String) :Observable<TokensViewState>{

        return Observable.just(TokensViewState.EmtpyState())
    }

    fun getTokenByName(tokenName:String) {
        networkService.getERC20Tokens(
            module = ConstData.ACCOUNT,
            action = ConstData.TOKEN_BALANCE,
            contractaddress = "0xa0b86991c6218b36c1d19d4a2e9eb0ce3606eb48",
            address = ConstData.ETH_ACCOUNT,
            tag = ConstData.LATEST,
            apikey = ConstData.API_KEY
        )
//            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { token: ERC20Token?, error:Throwable? ->
                if (error == null) {
                    Timber.d(token.toString())
                } else {
                    Timber.d(error.message)
                }
            }
    }
}