package feature_tokens.view

import feature_tokens.domain.ERC20Token

sealed class TokensViewState {
    class InitialState() : TokensViewState()
    data class MatchedTokensState(val tokenList:List<ERC20Token>) : TokensViewState()
    class LoadingState : TokensViewState()
//    data class MatchedToken(val tokenList: List<ERC20TokenResponse>):TokensViewState()
}