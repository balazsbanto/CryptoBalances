package feature_tokens.view

sealed class TokensViewState {
    class EmtpyState() : TokensViewState()
    data class TokenList(val tokenList:List<String>) : TokensViewState()
}