package com.example.lab2_verstka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import java.time.DayOfWeek

class MainActivity : AppCompatActivity() {
    val LKEY = "light"
    lateinit var degreesOfWeek: Map<String, Pair<Int, Int>>
    lateinit var resOfWeather: Map<String, Int>
    lateinit var weatherOfWeek: Map<String, String>
    lateinit var idOfWeek: Map<String, Int>
    var isLight: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isLight = savedInstanceState?.getBoolean(LKEY) ?: true
        setContentView(R.layout.activity_main)
        val changeTheme = findViewById<ImageView>(R.id.theme)
        if(isLight) {
            changeTheme.setImageResource(R.drawable.sun)
        } else {
            changeTheme.setImageResource(R.drawable.cloudy)
        }
        changeTheme.setOnClickListener(View.OnClickListener {
            if(isLight) {
                isLight = !isLight
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)

            } else {
                isLight = !isLight
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
            }
        })
        setTodayExtra()
        val SUN = getString(R.string.sunday)
        val MON = getString(R.string.monday)
        val TUE = getString(R.string.tuesday)
        val WED = getString(R.string.wednesday)
        val THU = getString(R.string.thursday)
        val FRI = getString(R.string.friday)


        val SAT = getString(R.string.saturday)
        val W_SUN = "SUNNY"
        val W_CLO = "CLOUDY"
        val W_RAI = "RAINY"
        val W_WIN = "WINDY"
        degreesOfWeek = hashMapOf<String, Pair<Int, Int>>(
            SUN to Pair(13, 5),
            MON to Pair(11, 6),
            TUE to Pair(12, 4),
            WED to Pair(12, 5),
            THU to Pair(12, 7),
            FRI to Pair(14, 6),
            SAT to Pair(14, 7)
        )
        resOfWeather = hashMapOf<String, Int>(
            W_SUN to R.drawable.sun,
            W_CLO to R.drawable.cloudy,
            W_RAI to R.drawable.rain,
            W_WIN to R.drawable.wind
        )
        weatherOfWeek = hashMapOf<String, String>(
            SUN to W_SUN,
            MON to W_RAI,
            TUE to W_CLO,
            WED to W_WIN,
            THU to W_WIN,
            FRI to W_SUN,
            SAT to W_CLO
        )
        idOfWeek = hashMapOf<String, Int>(
            SUN to R.id.sun,
            MON to R.id.mon,
            TUE to R.id.tue,
            WED to R.id.wed,
            THU to R.id.thu,
            FRI to R.id.fri,
            SAT to R.id.sat
        )
        setNextDays()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(LKEY, isLight)
    }
    private fun setNextDays() {
        for (entry in idOfWeek) {
            val temp = findViewById<LinearLayout>(entry.value)
            temp.findViewById<TextView>(R.id.day_of_week).text =
                entry.key
            temp.findViewById<ImageView>(R.id.weather).setImageResource(
                resOfWeather.get(weatherOfWeek.get(entry.key))!!
            )
            val degr = degreesOfWeek.get(entry.key)!!
            temp.findViewById<TextView>(R.id.degrees).text =
                degr.first.toString()+"°/"+degr.second.toString()+"°"
        }
    }

    private fun setTodayExtra() {
        val windflow = findViewById<LinearLayout>(R.id.wind_flow)
        val perception = findViewById<LinearLayout>(R.id.perception)
        val humidity = findViewById<LinearLayout>(R.id.humidity)

        val windflow_image = windflow.findViewById<ImageView>(R.id.extra_image)
        windflow_image.setImageResource(R.drawable.icon_windflow)
        val windflow_content = windflow.findViewById<TextView>(R.id.extra_content)

        val perception_content = perception.findViewById<TextView>(R.id.extra_content)
        val perception_image = perception.findViewById<ImageView>(R.id.extra_image)
        perception_image.setImageResource(R.drawable.icon_perception)
        val perception_name = perception.findViewById<TextView>(R.id.extra_kind)
        perception_name.text = getString(R.string.perception)

        val humidity_content = humidity.findViewById<TextView>(R.id.extra_content)
        val humidity_image = humidity.findViewById<ImageView>(R.id.extra_image)
        humidity_image.setImageResource(R.drawable.icon_humidity)
        val humidity_name = humidity.findViewById<TextView>(R.id.extra_kind)
        humidity_name.text = getString(R.string.humidity)

        perception_content.text = getString(R.string.test_perception)
        humidity_content.text = getString(R.string.test_humidity)
        windflow_content.text = getString(R.string.test_wind_flow)
    }
}