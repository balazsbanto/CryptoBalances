package feature.info_screen.view

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import feature.info_screen.domain.InfoScreenInteractor
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber

class InfoScreenPresenter(
    private val interactor: InfoScreenInteractor
) : MviBasePresenter<InfoScreenView, InfoScreenViewState>() {
    override fun bindIntents() {
        val showTokensIntent = intent(InfoScreenView::showTokensIntent)
            .doOnNext { Timber.d("Intent: showTokens") }
            .switchMap { interactor.showTokens() }

        val initialStateIntent = intent(InfoScreenView::initializeIntent)
            .doOnNext { Timber.d("Intent: initializeIntent") }
            .switchMap { interactor.initialState() }

        val viewState: Observable<InfoScreenViewState> = Observable.merge(
            arrayListOf(
                showTokensIntent,
                initialStateIntent
            )
        )
            .doOnNext {
                viewState -> Timber.d("State %s", viewState.toString())
            }
            .observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(viewState, InfoScreenView::render)
    }

}