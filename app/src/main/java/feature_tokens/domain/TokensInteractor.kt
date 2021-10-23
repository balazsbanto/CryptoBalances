package feature_tokens.domain

import feature_tokens.view.TokensViewState
import io.reactivex.Observable

class TokensInteractor {
    init {

    }

    fun initEmptyState() : Observable<TokensViewState> {

        return Observable.just(TokensViewState.EmtpyState())
    }
}