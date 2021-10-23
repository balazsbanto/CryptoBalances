package com.example.cryptobalances.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.example.cryptobalances.databinding.ActivityMainBinding
import feature.info_screen.view.InfoScreenController
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent
import timber.log.Timber

class MainActivity : AppCompatActivity(), KoinComponent{
    private lateinit var binding: ActivityMainBinding
    private lateinit var router:Router

    private val navigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        router = Conductor.attachRouter(this, binding.controllerContainer, savedInstanceState)
        (navigator as NavigatorImpl).router = router

        if (!router.hasRootController()) {
            Timber.d("noRoot")

            router.setRoot(RouterTransaction.with(InfoScreenController()))
        } else {
            Timber.d("hasRoot")
        }
    }

    override fun onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }

}