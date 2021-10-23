package com.example.cryptobalances.core

import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import feature_tokens.view.TokensController

class NavigatorImpl : Navigator {

    lateinit var router: Router
    override fun pushTokensControllerOnInfoScreenController() {
        val transaction = RouterTransaction.with(TokensController()).tag(TokensController.TAG)
        router.pushController(transaction.pushChangeHandler(FadeChangeHandler()).popChangeHandler(
            FadeChangeHandler()
        ))
    }

    override fun popCurrentController() {
        router.popCurrentController()
    }
}