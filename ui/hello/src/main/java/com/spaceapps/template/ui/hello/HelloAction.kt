package com.spaceapps.template.ui.hello

sealed class HelloAction {
    object ButtonClicked : HelloAction()
    class CodeEntered(val code: String) : HelloAction()
}