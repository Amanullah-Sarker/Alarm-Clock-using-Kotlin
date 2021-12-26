package com.amanullah.alarmclock.activity

import android.media.RingtoneManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.amanullah.alarmclock.databinding.ActivityDestinationBinding
import kotlin.random.Random

class DestinationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDestinationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDestinationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firstRandomNumber = Random.nextInt(0, 10)
        val secondRandomNumber = Random.nextInt(20, 30)


        binding.descriptionTextView.text = "What is the Sum of ${firstRandomNumber} & ${secondRandomNumber}?"

        binding.submitButton.setOnClickListener {

            val answer = binding.outlinedTextField.editText?.text.toString().toInt()

            val result = firstRandomNumber + secondRandomNumber

            if (answer == result) {
                play()
                Toast.makeText(applicationContext, "Pass", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Fail", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun play() {
        try {
            val alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
            val track = RingtoneManager.getRingtone(applicationContext, alarm)
            track.play()
        } catch (e: Exception) {
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
        }
    }
}