package com.cameronsh.ui

abstract class UISuccess() {
    data class Value(val value: Any? = null) : UISuccess()
}