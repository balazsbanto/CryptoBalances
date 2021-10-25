package com.example.cryptobalances.core.utils

import android.view.View

//http://kotlinextensions.com/

/**
 * Show the view  (show())
 */
fun View.show() : View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

/**
 * Hide the view. (hide())
 */
fun View.hide() : View {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
    return this
}

/**
 * Remove the view (remove())
 */
fun View.remove() : View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}