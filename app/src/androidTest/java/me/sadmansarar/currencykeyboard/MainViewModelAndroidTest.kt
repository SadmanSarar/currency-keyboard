package me.sadmansarar.currencykeyboard

import android.graphics.Color
import me.sadmansarar.currencykeyboard.providers.ColorProvider
import me.sadmansarar.currencykeyboard.providers.StringProvider
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class MainViewModelAndroidTest {

    private lateinit var viewModel : MainViewModel

    @Before
    fun setUp() {
        val colorProvider = object: ColorProvider {
            override fun getActiveTextColor(): Int {
                return Color.WHITE
            }

            override fun getInactiveTextColor(): Int {
                return Color.BLACK
            }
        }

        val stringProvider = object: StringProvider {
            override fun getString(id: Int): String {
                return when(id) {
                    R.string.label_aed -> "AED"
                    R.string.currency_text_initial -> "AED 0.00"
                    else -> ""
                }
            }
        }
        viewModel = MainViewModel(colorProvider, stringProvider)
    }

    @Test
    fun test_appendNothing_IfDecimalDigitsAre2() {
        val text = viewModel.createTextFromDigit(
            listOf("1","2",".","3","4")
        ).toString()
        assertEquals("AED 12.34",text)
    }

    @Test
    fun test_appendOne0_IfDecimalDigitsAre1() {
        val text = viewModel.createTextFromDigit(
            listOf("1","2",".","3")
        ).toString()
        assertEquals("AED 12.30",text)
    }

    @Test
    fun test_appendTwo0_IfDecimalDigitsAre0() {
        val text = viewModel.createTextFromDigit(
            listOf("1","2",".")
        ).toString()
        assertEquals("AED 12.00",text)
    }

    @Test
    fun test_appendCommaInThousandsPosition() {
        val text = viewModel.createTextFromDigit(
            listOf("1","2","2","2")
        ).toString()
        assertEquals("AED 1,222.00",text)
    }

    @Test
    fun test_appendCommaInMillionthPosition() {
        val text = viewModel.createTextFromDigit(
            listOf("1","2","2","2","1","2","2","2")
        ).toString()
        assertEquals("AED 12,221,222.00",text)
    }

    @Test
    fun test_ifEmpty_ReturnsDefault() {
        val text = viewModel.createTextFromDigit(
            listOf()
        ).toString()
        assertEquals("AED 0.00",text)
    }
}