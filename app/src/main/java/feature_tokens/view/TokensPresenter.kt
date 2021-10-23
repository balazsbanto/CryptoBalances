package feature_tokens.view

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import feature.info_screen.domain.InfoScreenInteractor
import feature.info_screen.view.InfoScreenView
import feature.info_screen.view.InfoScreenViewState
import feature_tokens.domain.TokensInteractor
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber

class TokensPresenter(
    private val interactor: TokensInteractor
) : MviBasePresenter<TokensView, TokensViewState>() {
    override fun bindIntents() {
        val initializeEmptyStateIntent = intent(TokensView::initializeEmptyState)
            .doOnNext { Timber.d("Intent: initializeEmptyState") }
            .switchMap { interactor.initEmptyState() }

        val viewState: Observable<TokensViewState> = Observable.merge(
            arrayListOf(
                initializeEmptyStateIntent
            )
        )
            .doOnNext {
                    viewState -> Timber.d("State %s", viewState.toString())
            }
            .observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(viewState, TokensView::render)
    }
}