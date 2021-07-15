package me.sadmansarar.currencykeyboard

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface ColorProvider {
    fun getActiveTextColor(): Int
    fun getInactiveTextColor(): Int
}

class AppColorProvider @Inject constructor(@ApplicationContext private val context: Context) : ColorProvider {
    override fun getActiveTextColor(): Int {
        return ContextCompat.getColor(context,R.color.text_active)
    }

    override fun getInactiveTextColor(): Int {
        return ContextCompat.getColor(context,R.color.text_inactive)
    }
}