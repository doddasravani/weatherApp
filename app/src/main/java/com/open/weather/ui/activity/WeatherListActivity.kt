package com.open.weather.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.open.weather.R
import com.open.weather.repository.model.Result
import com.open.weather.ui.adapter.WeatherAdapter
import com.open.weather.viewmodel.WeatherViewModel
import com.open.weather.viewmodel.WeatherViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_weather_list.*
import javax.inject.Inject

class WeatherListActivity : AppCompatActivity(), Observer<Result> {

    private lateinit var weatherAdapter: WeatherAdapter
    private lateinit var viewModel: WeatherViewModel
    @Inject
    lateinit var viewModelFactory: WeatherViewModelFactory
    private lateinit var cityName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_list)

        AndroidInjection.inject(this)

        cityName = intent.getStringExtra(Constants.CITY_KEY)!!
        setRecyclerView()

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setToolbar(toolbar, cityName)

        viewModel = ViewModelProvider(this, viewModelFactory).get(WeatherViewModel::class.java)
        viewModel.getWeatherResponse(cityName).observe(this, this)
    }

    private fun setRecyclerView(){
        val layoutManager = LinearLayoutManager(this)
        weatherAdapter = WeatherAdapter(this)
        temp_list.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(
            temp_list.context,
            layoutManager.orientation
        )
        temp_list.addItemDecoration(dividerItemDecoration)
        temp_list.adapter = weatherAdapter
    }

    private fun setToolbar(toolbar: Toolbar, city: String) {
        toolbar.setNavigationIcon(R.drawable.arrow_back)
        toolbar.setTitleTextColor(resources.getColor(R.color.white))
        toolbar.title = city
        toolbar.setNavigationOnClickListener{
            onBackPressed()
        }
    }

    override fun onChanged(result: Result?) {
        result?.let {
            when (it) {
                is Result.Success -> {
                    val responseList = it.tempList
                    if (responseList.isNullOrEmpty()) {
                        Toast.makeText(applicationContext, "No results for the city!!", Toast.LENGTH_LONG).show()
                    } else {
                        weatherAdapter.setData(responseList, cityName)
                    }
                }

                is Result.Error -> {
                    Toast.makeText(applicationContext, it.exception.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}