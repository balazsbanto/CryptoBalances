package feature.info_screen.view

import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable
import org.koin.core.KoinComponent

interface InfoScreenView: MvpView, KoinComponent {

    fun showTokensIntent() : Observable<Any>
    fun initializeIntent(): Observable<Any>
    fun render(viewState: InfoScreenViewState)
}