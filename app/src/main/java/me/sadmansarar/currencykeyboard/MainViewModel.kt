package me.sadmansarar.currencykeyboard

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val colorProvider: ColorProvider) : ViewModel() {
    companion object {
        const val NUM_DEL = "NUM_DEL"
        const val NUM_DOT = "."
    }

    val textToShow = MutableLiveData<Spannable>()
    private var amount: Double? = null
    internal var enteredNumbers = mutableListOf<String>()

    fun addDigit(num: String) {
        if (num == NUM_DEL) {
            enteredNumbers.removeLastOrNull()
            updateText()
            return
        }
        if (num != NUM_DOT && !enteredNumbers.contains(NUM_DOT) && enteredNumbers.size == 9) return
        if (amount == null && num == NUM_DEL) {
            return
        }
        if (num == NUM_DOT && enteredNumbers.contains(NUM_DOT)) {
            return
        }
        if (enteredNumbers.contains(NUM_DOT) && enteredNumbers.indexOf(NUM_DOT) + 2 < enteredNumbers.size) {
            return
        }
        enteredNumbers.add(num)
        updateText()
    }

    private fun updateText() {
        val createTextFromDigit = createTextFromDigit(enteredNumbers)
        textToShow.postValue(createTextFromDigit)
    }

    internal fun createTextFromDigit(list: List<String>): Spannable {
        if (list.isEmpty()) {
            val span: Spannable = SpannableString("AED 0.00")
            span.setSpan(ForegroundColorSpan(colorProvider.getInactiveTextColor()), 0, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            return span
        }
        if (list.size == 1 && list.contains(NUM_DOT)) {
            val span: Spannable = SpannableString("AED 0.00")
            span.setSpan(ForegroundColorSpan(colorProvider.getActiveTextColor()), 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            span.setSpan(ForegroundColorSpan(colorProvider.getInactiveTextColor()), 7, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            return span
        }
        val enteredNumber = list.joinToString("").toDouble()
        val formatter = DecimalFormat(if (list.contains(NUM_DOT)) "#,###,###.##" else "#,###,###")
        var yourFormattedString: String = formatter.format(enteredNumber)
        var appendedString: String = ""
        val indexOfDot = list.indexOf(NUM_DOT)
        if (list.contains(NUM_DOT)) {
            if(!yourFormattedString.contains(NUM_DOT)) {
                yourFormattedString = "$yourFormattedString."
            }
            val decimalDigits = list.subList(indexOfDot + 1, list.size)
            val afterDot = decimalDigits.joinToString("").toIntOrNull()
            if(afterDot == null) {
                appendedString = "00"
            } else {
                appendedString = when {
                    decimalDigits.size == 1 -> "0"
                    decimalDigits.isEmpty() -> "00"
                    else -> ""
                }
                yourFormattedString = when {
                    decimalDigits.size == 1 && afterDot == 0 -> "$yourFormattedString${"0"}"
                    decimalDigits.size == 2 && afterDot == 0 -> "$yourFormattedString${"00"}"
                    decimalDigits.size == 2 && afterDot % 10 == 0 -> "$yourFormattedString${"0"}"
                    else -> yourFormattedString
                }
            }
        } else if (!list.contains(NUM_DOT)) {
            appendedString = ".00"
        }

        val s = "AED $yourFormattedString${appendedString}"
        val spannableString = SpannableString(s)
        spannableString.setSpan(
            ForegroundColorSpan(colorProvider.getActiveTextColor()),
            0, 3,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            ForegroundColorSpan(colorProvider.getActiveTextColor()),
            4,
            yourFormattedString.length + 4,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            ForegroundColorSpan(colorProvider.getInactiveTextColor()),
            yourFormattedString.length + 4, s.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannableString
    }
}