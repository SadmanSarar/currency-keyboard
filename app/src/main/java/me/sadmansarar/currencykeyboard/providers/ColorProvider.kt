package me.sadmansarar.currencykeyboard.providers

interface ColorProvider {
    fun getActiveTextColor(): Int
    fun getInactiveTextColor(): Int
}