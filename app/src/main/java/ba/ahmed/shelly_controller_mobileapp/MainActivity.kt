package ba.ahmed.shelly_controller_mobileapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.SeekBar
import androidx.activity.ComponentActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val shellyDAO = ShellyDAO()

    private lateinit var openRollersButton1: Button
    private lateinit var closeRollersButton1: Button
    private lateinit var seekBar1: SeekBar

    private lateinit var openRollersButton2: Button
    private lateinit var closeRollersButton2: Button
    private lateinit var seekBar2: SeekBar

    private lateinit var openRollersButton3: Button
    private lateinit var closeRollersButton3: Button
    private lateinit var seekBar3: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Kid room rollers

        openRollersButton1 = findViewById(R.id.openRollersBtn1)
        closeRollersButton1 = findViewById(R.id.closeRollersBtn1)
        seekBar1 = findViewById(R.id.seekBar1)

        seekBar1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // add functionalities for when progress is changed
                controlRollers(2, "to_pos", progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // add functionalities for when the user starts touching the seekbar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                //  add functionalities for when the user stops touching the seekbar
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
        seekBar2 = findViewById(R.id.seekBar2)

        seekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // add functionalities for when progress is changed
                controlRollers(3, "to_pos", progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // add functionalities for when the user starts touching the seekbar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                //  add functionalities for when the user stops touching the seekbar
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
        seekBar3 = findViewById(R.id.seekBar3)

        seekBar3.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // add functionalities for when progress is changed
                controlRollers(4, "to_pos", progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // add functionalities for when the user starts touching the seekbar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                //  add functionalities for when the user stops touching the seekbar
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

    }

     private fun controlRollers(host: Int, go: String, rollerPos: Int) {
         CoroutineScope(Dispatchers.Main).launch {
             val result = shellyDAO.controlRollers(host, go, rollerPos)
             Log.d("CONTROL", "Control rollers ${host-1} result: $result")
         }
    }

}