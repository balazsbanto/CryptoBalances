package feature_tokens.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cryptobalances.core.Navigator
import com.example.cryptobalances.databinding.ControllerTokensScreenBinding
import com.hannesdorfmann.mosby3.MviController
import io.reactivex.Observable
import org.koin.core.get
import org.koin.core.inject
import timber.log.Timber

class TokensController: MviController<TokensView, TokensPresenter>(), TokensView {

    companion object {
        const val TAG = "TokensController"
    }

    private val navigator: Navigator by inject()
    private lateinit var binding:ControllerTokensScreenBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        binding = ControllerTokensScreenBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun createPresenter(): TokensPresenter = get()

    override fun initializeEmptyState(): Observable<Any> = Observable.just(Any())

    override fun render(viewState: TokensViewState) {
        when (viewState) {
            is TokensViewState.EmtpyState -> renderEmtpyState()
        }
    }

    private fun renderEmtpyState() {
        Timber.d("Rendered empty state")
    }

    override fun handleBack(): Boolean {
        Timber.d("popCurrentController")
        navigator.popCurrentController()
        return super.handleBack()
    }
}