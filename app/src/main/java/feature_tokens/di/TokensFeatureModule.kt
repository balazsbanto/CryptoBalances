package feature_tokens.di

import feature_tokens.domain.TokensInteractor
import feature_tokens.view.TokensPresenter
import org.koin.dsl.module

val tokensFeatureModule = module {
    factory { TokensInteractor() }
    factory { TokensPresenter(get()) }
}