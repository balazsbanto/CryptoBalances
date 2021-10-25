package feature_tokens.view

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import feature_tokens.domain.TokensInteractor
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber

class TokensPresenter(
    private val interactor: TokensInteractor
) : MviBasePresenter<TokensView, TokensViewState>() {
    override fun bindIntents() {
        val initializeEmptyStateIntent = intent(TokensView::initializeEmptyStateIntent)
            .doOnNext { Timber.d("Intent: initializeEmptyState") }
            .switchMap { interactor.createInitialState() }

        val searchIntent = intent(TokensView::searchIntent)
            .doOnNext { Timber.d("Intent: searchIntent") }
            .switchMap { interactor.searchIntent(it) }

        val viewState: Observable<TokensViewState> = Observable.merge(
            arrayListOf(
                initializeEmptyStateIntent,
                searchIntent
            )
        )
            .doOnNext {
                    viewState -> Timber.d("State %s", viewState.toString())
            }
            .observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(viewState, TokensView::render)
    }
}