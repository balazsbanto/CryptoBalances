package feature.info_screen.domain

import com.example.cryptobalances.core.utils.ConstData
import feature.info_screen.view.InfoScreenViewState
import io.reactivex.Observable

class InfoScreenInteractor {
    fun showTokens() : Observable<InfoScreenViewState> {
        return Observable.just(InfoScreenViewState.ShowTokenslState())
    }

    fun initialState() : Observable<InfoScreenViewState> {
        return Observable.just(InfoScreenViewState.InitialState(ConstData.ETH_ACCOUNT))
    }
}