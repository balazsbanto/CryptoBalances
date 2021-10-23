package com.example.cryptobalances.core.di

import com.example.cryptobalances.core.Navigator
import com.example.cryptobalances.core.NavigatorImpl
import feature.info_screen.di.infoScreenFeatureModule
import org.koin.dsl.module

val appComponenet = listOf(infoScreenFeatureModule,
    module {
        single { NavigatorImpl() as Navigator }
    }
    )