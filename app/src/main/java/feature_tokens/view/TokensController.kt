package feature_tokens.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cryptobalances.core.Navigator
import com.example.cryptobalances.databinding.ControllerTokensScreenBinding
import com.hannesdorfmann.mosby3.MviController
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import org.koin.core.get
import org.koin.core.inject
import timber.log.Timber
import java.util.concurrent.TimeUnit

class TokensController: MviController<TokensView, TokensPresenter>(), TokensView {

    companion object {
        const val TAG = "TokensController"
    }

    private val navigator: Navigator by inject()
    private lateinit var binding:ControllerTokensScreenBinding
    private val getTokenByNameSubject = PublishSubject.create<String>()
//    private val initializeSubject = PublishSubject.create<Any>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        binding = ControllerTokensScreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun createPresenter(): TokensPresenter = get()

    override fun render(viewState: TokensViewState) {
        when (viewState) {
            is TokensViewState.EmtpyState -> renderEmtpyState()
            is TokensViewState.TokenList -> renderTokenList(viewState)
        }
    }

    private fun renderTokenList(viewState: TokensViewState.TokenList) {
        val l = viewState.tokenList
        Timber.d(l.toString())
    }

    private fun renderEmtpyState() {
        Timber.d("Rendered empty state")
        binding.tokenSearchHint.text = "Search token"
    }

    override fun handleBack(): Boolean {
        Timber.d("popCurrentController")
        navigator.popCurrentController()
        return super.handleBack()
    }

    override fun searchIntent(): Observable<String> {
//        binding.cattleHeaderSearchText.clearFocus()
//        binding.focusableItem.requestFocus()
        return binding.tokenSearchBox
            .textChanges().debounce(500, TimeUnit.MILLISECONDS)
            .map { it.toString() }
    }

    override fun initializeEmptyStateIntent(): Observable<Any> {
        return Observable.just(Any())
    }
    override fun getTokensByName(): Observable<String> = getTokenByNameSubject
}