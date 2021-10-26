package feature_tokens.domain

data class ErrorToken(val status:String?, val message:String?, val result:String?) : Token {
}