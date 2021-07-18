package me.sadmansarar.currencykeyboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import me.sadmansarar.currencykeyboard.MainViewModel.Companion.NUM_DEL
import me.sadmansarar.currencykeyboard.MainViewModel.Companion.NUM_DOT

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnNum1.setOnClickListener { onTextButtonClicked(getString(R.string.num_1)) }
        btnNum2.setOnClickListener { onTextButtonClicked(getString(R.string.num_2)) }
        btnNum3.setOnClickListener { onTextButtonClicked(getString(R.string.num_3)) }
        btnNum4.setOnClickListener { onTextButtonClicked(getString(R.string.num_4)) }
        btnNum5.setOnClickListener { onTextButtonClicked(getString(R.string.num_5)) }
        btnNum6.setOnClickListener { onTextButtonClicked(getString(R.string.num_6)) }
        btnNum7.setOnClickListener { onTextButtonClicked(getString(R.string.num_7)) }
        btnNum8.setOnClickListener { onTextButtonClicked(getString(R.string.num_8)) }
        btnNum9.setOnClickListener { onTextButtonClicked(getString(R.string.num_9)) }
        btnNum0.setOnClickListener { onTextButtonClicked(getString(R.string.num_0)) }
        btnNumDot.setOnClickListener { onTextButtonClicked(NUM_DOT) }
        btnNumDel.setOnClickListener { onTextButtonClicked(NUM_DEL) }

        viewModel.textToShow.observe(this, Observer {
            txtCurrency.text = it
        })
    }

    private fun onTextButtonClicked(num: String) {
        viewModel.addDigit(num)
    }
}