package feature_tokens.di

import feature_tokens.domain.TokensInteractor
import feature_tokens.view.TokensPresenter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val tokensFeatureModule = module {
    factory { TokensInteractor(get(), androidContext()) }
    factory { TokensPresenter(get()) }
}