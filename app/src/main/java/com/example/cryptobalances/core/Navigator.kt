package com.example.cryptobalances.core

import com.bluelinelabs.conductor.Router

interface Navigator {
    fun pushTokensControllerOnInfoScreenController()
    fun popCurrentController()
}