package com.open.weather.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.open.weather.R

class WeatherDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_detail)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val temp = findViewById<TextView>(R.id.temp)
        val feelsLike = findViewById<TextView>(R.id.feels_like)
        val main = findViewById<TextView>(R.id.main)
        val description = findViewById<TextView>(R.id.description)

        val intent = intent
        intent.apply {
            val cityName = getStringExtra(Constants.CITY_KEY)
            setToolbar(toolbar, cityName!!)

            temp.text = getStringExtra(Constants.TEMP_KEY)
            feelsLike.text =
                String.format("Feels like: %s", getStringExtra(Constants.FEELS_LIKE_KEY))
            main.text = getStringExtra(Constants.MAIN_KEY)
            description.text = getStringExtra(Constants.DESC_KEY)
        }

    }

    private fun setToolbar(toolbar: Toolbar, city: String) {
        toolbar.setNavigationIcon(R.drawable.arrow_back)
        toolbar.title = city
        toolbar.setTitleTextColor(resources.getColor(R.color.white))

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}