package feature.info_screen.domain

import feature.info_screen.view.InfoScreenViewState
import io.reactivex.Observable

class InfoScreenInteractor {
    companion object {
        const val ETH_ADDRESS = "0xde57844f758a0a6a1910a4787ab2f7121c8978c3"
    }
    fun showTokens() : Observable<InfoScreenViewState> {
        return Observable.just(InfoScreenViewState.ShowTokenslState())
    }

    fun initialState() : Observable<InfoScreenViewState> {
        return Observable.just(InfoScreenViewState.InitialState(ETH_ADDRESS))
    }
}