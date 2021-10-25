package feature_tokens.domain

import android.content.Context
import com.example.cryptobalances.R
import com.example.cryptobalances.core.network.NetworkService
import com.example.cryptobalances.core.utils.ConstData
import feature_tokens.view.TokensViewState
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.json.JSONArray
import org.json.JSONObject

class TokensInteractor(private val networkService: NetworkService, private val context: Context) {
    private val tokenNameToAddressMap: Map<String, String>
    private val availableTokenNames: List<String>

    init {
        tokenNameToAddressMap = readTokenMap()
        availableTokenNames = tokenNameToAddressMap.keys.toList()
    }

//    val tokenBalanceSubject: PublishSubject<TokensViewState.MatchedTokensState> = PublishSubject.create()

    private fun readTokenMap(): Map<String, String> {
        val jsonContent = context.resources.openRawResource(R.raw.available_tokens)
            .bufferedReader().use { it.readText() }

        val tokenNameToAddressMap = hashMapOf<String, String>()
        val jsonObject = JSONObject(jsonContent)
        val tokenArray = jsonObject["tokens"] as JSONArray
        for (i in 0 until tokenArray.length()) {
            val item = tokenArray.getJSONObject(i)
            val name = item["symbol"] as String
            val address = item["address"] as String
            tokenNameToAddressMap[name] = address
        }

        return tokenNameToAddressMap
    }

    fun getMatchedTokenNames(searchedToken: String): List<String> {

        val pattern = searchedToken.toRegex()
        val matchedTokenNames = mutableListOf<String>()
        availableTokenNames.forEach { tokenName ->
            if (pattern.containsMatchIn(tokenName)) {
                matchedTokenNames.add(tokenName)
            }
        }

        return matchedTokenNames
    }

    fun createInitialState(): Observable<TokensViewState> {
        return Observable.just(TokensViewState.InitialState())
    }

    fun searchIntent(searchStr: String): Observable<TokensViewState> {
        val matchedTokenNames = getMatchedTokenNames("USD")
        val singles = mutableListOf<Single<ERC20Token>>()
        matchedTokenNames.forEach { tokenName->
            singles.add(getTokenByName(tokenName))
        }

        return Observable.fromIterable(singles)
            .flatMap {
                it.observeOn(Schedulers.computation()).toObservable()
            }
            .toList()
            .flatMapObservable {
                Observable.just(TokensViewState.MatchedTokensState(it))
            }
    }

    private fun getTokenByName(tokenName: String): Single<ERC20Token> {
        return networkService.getERC20Tokens(
            module = ConstData.ACCOUNT,
            action = ConstData.TOKEN_BALANCE,
            contractaddress = tokenNameToAddressMap[tokenName],
//            contractaddress = "0xa0b86991c6218b36c1d19d4a2e9eb0ce3606eb48",
            address = ConstData.ETH_ACCOUNT,
            tag = ConstData.LATEST,
            apikey = ConstData.API_KEY
        )
//            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map {
                ERC20Token(name = tokenName, balance = it.result!!.toDouble())
            }
//            .subscribe { token: ERC20TokenResponse?, error: Throwable? ->
//                if (error == null) {
//                    Timber.d(token.toString())
//                } else {
//                    Timber.d(error.message)
//                }
//            }
    }
}