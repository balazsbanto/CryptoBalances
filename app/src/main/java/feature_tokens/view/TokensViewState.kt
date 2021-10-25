package feature_tokens.view

import com.example.cryptobalances.core.network.response.ERC20Token

sealed class TokensViewState {
    class EmtpyState() : TokensViewState()
    data class TokenList(val tokenList:List<ERC20Token>) : TokensViewState()
    data class MatchedToken(val token:ERC20Token):TokensViewState()
}