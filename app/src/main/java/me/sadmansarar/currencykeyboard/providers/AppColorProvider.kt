package me.sadmansarar.currencykeyboard.providers

import android.content.Context
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import me.sadmansarar.currencykeyboard.R
import javax.inject.Inject


class AppColorProvider @Inject constructor(@ApplicationContext private val context: Context) :
    ColorProvider {
    override fun getActiveTextColor(): Int {
        return ContextCompat.getColor(context, R.color.text_active)
    }

    override fun getInactiveTextColor(): Int {
        return ContextCompat.getColor(context, R.color.text_inactive)
    }
}