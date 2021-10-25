package com.example.cryptobalances.core.di

import com.example.cryptobalances.core.Navigator
import com.example.cryptobalances.core.NavigatorImpl
import com.example.cryptobalances.core.network.NetworkService
import com.example.cryptobalances.core.network.RestClient
import feature.info_screen.di.infoScreenFeatureModule
import feature_tokens.di.tokensFeatureModule
import org.koin.dsl.module

val appComponenet = listOf(
    infoScreenFeatureModule,
    tokensFeatureModule,
    module {
        single { NavigatorImpl() as Navigator }
        single { RestClient().getNetworkService() as NetworkService }
    }
)