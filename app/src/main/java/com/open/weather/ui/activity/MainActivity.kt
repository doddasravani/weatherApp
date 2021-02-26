package com.open.weather.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import com.open.weather.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TextWatcher {

    private lateinit var cityEditText: EditText
    private lateinit var lookupBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cityEditText = findViewById(R.id.city_name)
        lookupBtn = findViewById(R.id.lookup)
        lookupBtn.isEnabled = false

        cityEditText.addTextChangedListener(this)
        lookupBtn.setOnClickListener {
            val intent = Intent(applicationContext, WeatherListActivity::class.java)
            intent.putExtra(Constants.CITY_KEY, cityEditText.text.toString())
            startActivity(intent)
        }
    }

    override fun afterTextChanged(editText: Editable?) {
        editText?.let { text ->
            lookupBtn.isEnabled = text.isNotBlank()
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }
}