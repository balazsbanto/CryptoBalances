package feature_tokens.view

import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable
import org.koin.core.KoinComponent

interface TokensView : MvpView, KoinComponent {

    fun initializeEmptyState() : Observable<Any>
    fun render(viewState: TokensViewState)
}