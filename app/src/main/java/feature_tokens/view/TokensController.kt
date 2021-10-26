package feature_tokens.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptobalances.core.Navigator
import com.example.cryptobalances.core.utils.remove
import com.example.cryptobalances.core.utils.show
import com.example.cryptobalances.databinding.ControllerTokensScreenBinding
import com.hannesdorfmann.mosby3.MviController
import com.jakewharton.rxbinding3.widget.textChanges
import feature_tokens.domain.ERC20Token
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
    private var tokenListAdapter: TokenListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        binding = ControllerTokensScreenBinding.inflate(inflater, container, false)
        binding.swipeContainer.setOnRefreshListener {
            binding.tokenSearchBox.apply {
                setText(text.toString())
                setSelection(text.length)
            }
        }
        initRecyclerView()
        return binding.root

    }

    private fun initRecyclerView() {
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(activity!!)
        binding.tokenList.layoutManager = layoutManager
    }

    override fun createPresenter(): TokensPresenter = get()

    override fun render(viewState: TokensViewState) {
        when (viewState) {
            is TokensViewState.InitialState -> renderInitialState()
            is TokensViewState.MatchedTokensState -> renderTokenList(viewState)
            is TokensViewState.LoadingState -> renderLoadingState()
            is TokensViewState.ErrorState -> renderErrorState(viewState)
            is TokensViewState.NoTokenFoundState -> renderNoTokenFoundState()
            is TokensViewState.NoInternetState -> renderNoInternetState()
        }
    }

    private fun renderNoInternetState() {
        binding.loadingIndicator.root.remove()
        binding.infoLabel.show()
        binding.infoLabel.text = "No internet connection"

        setAdapterData(emptyList())
    }

    private fun renderNoTokenFoundState() {
        binding.swipeContainer.isRefreshing = false
        binding.infoLabel.show()
        binding.infoLabel.text = "No token found"

        setAdapterData(emptyList())
    }

    private fun renderErrorState(viewState: TokensViewState.ErrorState) {
        binding.swipeContainer.isRefreshing = false
        binding.infoLabel.show()
        binding.infoLabel.text = "Error: ${viewState.message}"

        setAdapterData(emptyList())

    }

    private fun renderLoadingState() {
        binding.swipeContainer.isRefreshing = true
    }

    private fun renderTokenList(viewState: TokensViewState.MatchedTokensState) {
        binding.swipeContainer.isRefreshing = false
        binding.infoLabel.remove()
        binding.tokenList.show()

        setAdapterData(viewState.tokenList)
    }

    private fun setAdapterData(tokenList:List<ERC20Token>) {
        tokenListAdapter = TokenListAdapter(ArrayList(tokenList.sortedBy { it.name }))
        binding.tokenList.adapter = tokenListAdapter
    }

    private fun renderInitialState() {
        Timber.d("Rendered initial state")
        binding.tokenList.remove()
        binding.swipeContainer.isRefreshing = false
        binding.infoLabel.show()
        binding.infoLabel.text = "Search for a token"
    }

    override fun handleBack(): Boolean {
        Timber.d("popCurrentController")
        navigator.popCurrentController()
        return super.handleBack()
    }

    override fun searchIntent(): Observable<String> {
        return binding.tokenSearchBox
            .textChanges().debounce(800, TimeUnit.MILLISECONDS)
            .map { it.toString() }
    }

    override fun initializeEmptyStateIntent(): Observable<Any> {
        return Observable.just(Any())
    }
    override fun getTokensByName(): Observable<String> = getTokenByNameSubject
}