package me.sadmansarar.currencykeyboard.providers

import android.content.Context
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import me.sadmansarar.currencykeyboard.R
import javax.inject.Inject


class AppStringProvider @Inject constructor(@ApplicationContext private val context: Context) : StringProvider {
    override fun getString(id: Int): String {
        return context.getString(id)
    }
}