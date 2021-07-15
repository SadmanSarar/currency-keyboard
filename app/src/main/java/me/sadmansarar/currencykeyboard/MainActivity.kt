package me.sadmansarar.currencykeyboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import me.sadmansarar.currencykeyboard.MainViewModel.Companion.NUM_DEL

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnNum1.setOnClickListener { onTextButtonClicked("1") }
        btnNum2.setOnClickListener { onTextButtonClicked("2") }
        btnNum3.setOnClickListener { onTextButtonClicked("3") }
        btnNum4.setOnClickListener { onTextButtonClicked("4") }
        btnNum5.setOnClickListener { onTextButtonClicked("5") }
        btnNum6.setOnClickListener { onTextButtonClicked("6") }
        btnNum7.setOnClickListener { onTextButtonClicked("7") }
        btnNum8.setOnClickListener { onTextButtonClicked("8") }
        btnNum9.setOnClickListener { onTextButtonClicked("9") }
        btnNum0.setOnClickListener { onTextButtonClicked("0") }
        btnNumDot.setOnClickListener { onTextButtonClicked(".") }
        btnNumDel.setOnClickListener { onTextButtonClicked(NUM_DEL) }

        viewModel.textToShow.observe(this, Observer {
            txtCurrency.text = it
        })
    }

    private fun onTextButtonClicked(num: String) {
        viewModel.addDigit(num)
    }
}