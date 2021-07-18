package me.sadmansarar.currencykeyboard

import me.sadmansarar.currencykeyboard.MainViewModel.Companion.NUM_DEL
import me.sadmansarar.currencykeyboard.MainViewModel.Companion.NUM_DOT
import me.sadmansarar.currencykeyboard.providers.ColorProvider
import me.sadmansarar.currencykeyboard.providers.StringProvider
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class MainViewModelTest {
    private lateinit var  model :MainViewModel

    @Before
    fun setUp() {
        model = object: MainViewModel(
            Mockito.mock(ColorProvider::class.java),
            Mockito.mock(StringProvider::class.java)
        ) {
            override fun updateText() {
            }
        }
    }

    @Test
    fun test_startsWithDot_ReturnsNumberWithDot() {
        model.addDigit(NUM_DOT)
        model.addDigit("1")
        model.addDigit("2")

        assertEquals(".12",model.enteredNumbers.joinToString(""))
    }

    @Test
    fun test_acceptsOnly2DigitAfterDot() {
        model.addDigit(NUM_DOT)
        model.addDigit("1")
        model.addDigit("2")
        model.addDigit("3")
        model.addDigit("4")

        assertEquals(".12",model.enteredNumbers.joinToString(""))
    }

    @Test
    fun test_ifDelIsPressed_removesLastDigit() {
        model.addDigit(NUM_DOT)
        model.addDigit("1")
        model.addDigit("2")
        model.addDigit("3")
        model.addDigit(NUM_DEL)

        assertEquals(".1",model.enteredNumbers.joinToString(""))
    }

    @Test
    fun test_ifDelIsPressedMoreThanDigits_returnsEmptyString() {
        model.addDigit("1")
        model.addDigit("2")
        model.addDigit("3")
        model.addDigit(NUM_DEL)
        model.addDigit(NUM_DEL)
        model.addDigit(NUM_DEL)
        model.addDigit(NUM_DEL)

        assertEquals("",model.enteredNumbers.joinToString(""))
    }

    @Test
    fun test_ifDelIsPressedMoreThanDigitsWithDot_returnsEmptyString() {
        model.addDigit("1")
        model.addDigit("3")
        model.addDigit(NUM_DOT)
        model.addDigit("2")
        model.addDigit("3")
        model.addDigit(NUM_DEL)
        model.addDigit(NUM_DEL)
        model.addDigit(NUM_DEL)
        model.addDigit(NUM_DEL)
        model.addDigit(NUM_DEL)

        assertEquals("",model.enteredNumbers.joinToString(""))
    }
}