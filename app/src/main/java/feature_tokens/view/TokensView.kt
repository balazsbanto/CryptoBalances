package feature_tokens.view

import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable
import org.koin.core.KoinComponent

interface TokensView : MvpView, KoinComponent {

    fun initializeEmptyStateIntent() : Observable<Any>
    fun getTokensByName() : Observable<String>
    fun searchIntent():Observable<String>
    fun render(viewState: TokensViewState)
}