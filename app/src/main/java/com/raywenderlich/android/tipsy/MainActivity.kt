/*
 * Copyright (c) 2020 Razeware LLC
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 * 
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.tipsy

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.raywenderlich.android.tipsy.databinding.ActivityMainBinding
import java.text.NumberFormat

/**
 * Main Screen
 */

private const val INITIAL_TIP_PERCENT = 15

class MainActivity : AppCompatActivity() {
  //1:
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    setTheme(R.style.AppTheme)
    super.onCreate(savedInstanceState)

    //2:
    binding = ActivityMainBinding.inflate(layoutInflater)

    binding.tipPercentEditText.setText( "$INITIAL_TIP_PERCENT")
    binding.tipPercentTextView.text = "$INITIAL_TIP_PERCENT%"


    binding.billEditText.addTextChangedListener(object : TextWatcher {
      override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

      override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

      override fun afterTextChanged(p0: Editable?) {
        calculateTip()
      }
    })

    binding.tipPercentEditText.addTextChangedListener(object : TextWatcher {
      override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

      override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

      override fun afterTextChanged(p0: Editable?) {
        binding.tipPercentTextView.text = "${binding.tipPercentEditText.text}%"
        calculateTip()
      }
    })
    //3:
    setContentView(binding.root)
  }

  fun calculateTip() {

    if (binding.billEditText.text.toString().isEmpty() || binding.tipPercentEditText.text.toString()
                    .isEmpty()) {
      binding.tipAmountTextView.text = ""
      binding.totalAmountTextView.text = ""
      return
    }

//1:
    val bill = binding.billEditText.text.toString().toDouble()
//2:
    val tipPercent = binding.tipPercentEditText.text.toString().toDouble()
//3:
    val tipAmount = (tipPercent * bill) / 100
//4:
    val totalAmount = bill + tipAmount
//5:
    val formattedTip = NumberFormat.getCurrencyInstance().format(tipAmount)
//6:
    val formattedTotalAmount = NumberFormat.getCurrencyInstance().format(totalAmount)
//7:
    binding.tipAmountTextView.text = formattedTip.toString()
//8:
    binding.totalAmountTextView.text = formattedTotalAmount.toString()
  }
}

