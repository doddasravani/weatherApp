package com.open.weather.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.open.weather.R
import com.open.weather.repository.remote.model.Temperature
import com.open.weather.ui.activity.Constants
import com.open.weather.ui.activity.WeatherDetailActivity

class WeatherAdapter(private val context: Context) :
    RecyclerView.Adapter<WeatherAdapter.WeatherHolder>() {

    private var tempList: List<Temperature> = emptyList()
    private var cityName: String? = null

    fun setData(tempList: List<Temperature>, cityName: String) {
        this.tempList = tempList
        this.cityName = cityName
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder =
        WeatherHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false))

    override fun getItemCount(): Int =
        if (tempList.isNotEmpty()) tempList.size else 0

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        val intent = Intent(context.applicationContext, WeatherDetailActivity::class.java)
        intent.putExtra(Constants.CITY_KEY, cityName)
        val temperature = tempList[position]
        temperature.let { temp ->
            val weatherList = temp.weather
            if (!weatherList.isNullOrEmpty()) {
                weatherList[0].apply {
                    holder.main.text = this.main
                    intent.putExtra(Constants.MAIN_KEY, this.main)
                    intent.putExtra(Constants.DESC_KEY, this.description)
                }
            }
            temp.main.let { main ->
                holder.temp.text = String.format("Temp: %s", main.temp.toString())
                intent.putExtra(Constants.TEMP_KEY, main.temp.toString())
                intent.putExtra(Constants.FEELS_LIKE_KEY, main.temp.toString())
            }

            holder.layout.setOnClickListener {
                context.startActivity(intent)
            }
        }
    }

    class WeatherHolder(view: View) : RecyclerView.ViewHolder(view) {
        val layout = view.findViewById<ConstraintLayout>(R.id.temp_layout)
        val main = view.findViewById<TextView>(R.id.main)
        val temp = view.findViewById<TextView>(R.id.temperature)
    }
}