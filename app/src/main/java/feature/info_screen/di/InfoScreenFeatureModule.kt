package feature.info_screen.di

import feature.info_screen.domain.InfoScreenInteractor
import feature.info_screen.view.InfoScreenPresenter
import org.koin.dsl.module

val infoScreenFeatureModule = module {
    factory { InfoScreenInteractor() }
    factory { InfoScreenPresenter(get()) }
}