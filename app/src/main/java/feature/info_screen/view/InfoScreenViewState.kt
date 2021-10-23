package feature.info_screen.view

import android.icu.text.IDNA

sealed class InfoScreenViewState {
    class InitialState(val ethAddress:String) : InfoScreenViewState()
    class ShowTokenslState() : InfoScreenViewState()
}