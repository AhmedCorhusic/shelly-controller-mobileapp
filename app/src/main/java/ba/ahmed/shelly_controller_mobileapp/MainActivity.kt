package ba.ahmed.shelly_controller_mobileapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.slider.Slider
import androidx.activity.ComponentActivity
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {
    private val shellyDAO = ShellyDAO()

    private var isAvailableLocally = true

    private lateinit var openRollersButton1: Button
    private lateinit var closeRollersButton1: Button
    private lateinit var slider1: Slider

    private lateinit var openRollersButton2: Button
    private lateinit var closeRollersButton2: Button
    private lateinit var slider2: Slider

    private lateinit var openRollersButton3: Button
    private lateinit var closeRollersButton3: Button
    private lateinit var slider3: Slider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Kid room rollers

        openRollersButton1 = findViewById(R.id.openRollersBtn1)
        closeRollersButton1 = findViewById(R.id.closeRollersBtn1)
        slider1 = findViewById(R.id.slider1)

        slider1.addOnChangeListener { slider, value, fromUser ->
            Log.d("SLIDER", "Slider 1 value changed to: ${value.toInt()}")
        }

        slider1.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
                // Called when the user first touches the slider thumb
                Log.d("SLIDER", "Slider 1 touch started")
            }

            override fun onStopTrackingTouch(slider: Slider) {
                // Called when the user releases the slider thumb
                val finalValue = slider.value.toInt()
                controlRollers(2, "to_pos", finalValue)
                Log.d("SLIDER", "Slider 1 touch stopped. Final value: $finalValue")
            }
        })

        openRollersButton1.setOnClickListener {
            Log.d("OPEN", "Open rollers 1 button clicked")
            controlRollers(2, "to_pos", 100)
        }

        closeRollersButton1.setOnClickListener {
            Log.d("CLOSE", "Close rollers 1 button clicked")
            controlRollers(2, "to_pos", 0)
        }

        // Living room rollers

        openRollersButton2 = findViewById(R.id.openRollersBtn2)
        closeRollersButton2 = findViewById(R.id.closeRollersBtn2)
        slider2 = findViewById(R.id.slider2)

        slider2.addOnChangeListener { slider, value, fromUser ->
            Log.d("SLIDER", "Slider 2 value changed to: ${value.toInt()}")
        }

        slider2.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
                // Called when the user first touches the slider thumb
                Log.d("SLIDER", "Slider 2 touch started")
            }

            override fun onStopTrackingTouch(slider: Slider) {
                // Called when the user releases the slider thumb
                val finalValue = slider.value.toInt()
                controlRollers(3, "to_pos", finalValue)
                Log.d("SLIDER", "Slider 2 touch stopped. Final value: $finalValue")
            }
        })

        openRollersButton2.setOnClickListener {
            Log.d("OPEN", "Open rollers 2 button clicked")
            controlRollers(3, "to_pos", 100)
        }

        closeRollersButton2.setOnClickListener {
            Log.d("CLOSE", "Close rollers 2 button clicked")
            controlRollers(3, "to_pos", 0)
        }

        // Bedroom rollers

        openRollersButton3 = findViewById(R.id.openRollersBtn3)
        closeRollersButton3 = findViewById(R.id.closeRollersBtn3)
        slider3 = findViewById(R.id.slider3)

        slider3.addOnChangeListener { slider, value, fromUser ->
            Log.d("SLIDER", "Slider 3 value changed to: ${value.toInt()}")
        }

        slider3.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
                // Called when the user first touches the slider thumb
                Log.d("SLIDER", "Slider 3 touch started")
            }

            override fun onStopTrackingTouch(slider: Slider) {
                // Called when the user releases the slider thumb
                val finalValue = slider.value.toInt()
                controlRollers(4, "to_pos", finalValue)
                Log.d("SLIDER", "Slider 3 touch stopped. Final value: $finalValue")
            }
        })

        openRollersButton3.setOnClickListener {
            Log.d("OPEN", "Open rollers 3 button clicked")
            controlRollers(4, "to_pos", 100)
        }

        closeRollersButton3.setOnClickListener {
            Log.d("CLOSE", "Close rollers 3 button clicked")
            controlRollers(4, "to_pos", 0)
        }

        initializeSliderValues()

    }

    private fun initializeSliderValues() {
        CoroutineScope(Dispatchers.Main).launch {
            var value1 = shellyDAO.getLocalCurrentPos(2)
            if (value1 == -1) {
                // Device is not available on a local network -> use Cloud API
                isAvailableLocally = false
                value1 = shellyDAO.getCloudCurrentPos(2)
                delay(1000L)
            }

            slider1.value = (Math.round(value1.toDouble() / 10) * 10).toFloat()
            Log.d("INITIALIZE", "Slider for roller 1 initialized to: $value1")

            val value2: Int
            if (isAvailableLocally) {
                // Device is available on a local network -> use Local API
                value2 = shellyDAO.getLocalCurrentPos(3)
            }
            else {
                // Device is not available on a local network -> use Cloud API
                value2 = shellyDAO.getCloudCurrentPos(3)
                delay(1000L)
            }

            slider2.value = (Math.round(value2.toDouble() / 10) * 10).toFloat()
            Log.d("INITIALIZE", "Slider for roller 2 initialized to: $value2")

            val value3: Int
            if (isAvailableLocally) {
                // Device is available on a local network -> use Local API
                value3 = shellyDAO.getLocalCurrentPos(4)
            }
            else {
                // Device is not available on a local network -> use Cloud API
                value3 = shellyDAO.getCloudCurrentPos(4)
                delay(1000L)
            }

            slider3.value = (Math.round(value3.toDouble() / 10) * 10).toFloat()
            Log.d("INITIALIZE", "Slider for roller 3 initialized to: $value3")
        }
    }

    private fun controlRollers(host: Int, go: String, rollerPos: Int) {
         CoroutineScope(Dispatchers.Main).launch {
             var result = ""
             if (isAvailableLocally) {
                 // Device is available on a local network -> use Local API
                 result = shellyDAO.controlLocalRollers(host, go, rollerPos)
                 if (result.contains("FAILED"))
                     isAvailableLocally = false
             }

             if (!isAvailableLocally) {
                 // Device is not available on a local network -> use Cloud API
                 result = shellyDAO.controlCloudRollers(host, go, rollerPos)
                 delay(1000L)
             }

             if (host == 2)
                 slider1.value = rollerPos.toFloat()
             if (host == 3)
                 slider2.value = rollerPos.toFloat()
             if (host == 4)
                 slider3.value = rollerPos.toFloat()
             Log.d("CONTROL", "Control rollers ${host-1} result: $result")
         }
    }

}