package me.sadmansarar.currencykeyboard.providers

import androidx.annotation.StringRes

interface StringProvider {
    fun getString(@StringRes id: Int): String
}