package feature.info_screen.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cryptobalances.databinding.ControllerInfoScreenBinding
import com.hannesdorfmann.mosby3.MviController
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import org.koin.core.get


class InfoScreenController : MviController<InfoScreenView, InfoScreenPresenter>(), InfoScreenView {

    private lateinit var binding: ControllerInfoScreenBinding
    private val showTokensIntent: PublishSubject<Any> = PublishSubject.create()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        binding = ControllerInfoScreenBinding.inflate(inflater, container, false)
        binding.showTokensButton.setOnClickListener {
            showTokensIntent.onNext(Any())
        }

        return binding.root
    }

    override fun createPresenter(): InfoScreenPresenter  = get()

    override fun showTokensIntent(): Observable<Any>  = showTokensIntent

    override fun initializeIntent(): Observable<Any> = Observable.just(Any())

    override fun render(viewState: InfoScreenViewState) {
        when (viewState) {
            is InfoScreenViewState.InitialState -> renderInitialState(viewState)
            is InfoScreenViewState.ShowTokenslState -> renderShowTokensState()
        }
    }

    private fun renderShowTokensState() {

    }

    private fun renderInitialState(viewState: InfoScreenViewState.InitialState) {
        binding.walletAddress.text = viewState.ethAddress
    }
}