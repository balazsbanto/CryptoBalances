package feature_tokens.view

import feature_tokens.domain.ERC20Token

sealed class TokensViewState {
    class InitialState() : TokensViewState()
    data class MatchedTokensState(val tokenList: List<ERC20Token>) : TokensViewState()
    class NoTokenFoundState() : TokensViewState()
    class LoadingState : TokensViewState()
    class NoInternetState : TokensViewState()
    data class ErrorState(val message: String?) : TokensViewState()
}