package com.amanullah.alarmclock.activity

import android.media.RingtoneManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.amanullah.alarmclock.databinding.ActivityDestinationBinding
import kotlin.random.Random
import android.app.AlarmManager

import android.app.PendingIntent
import android.content.Context

import android.content.Intent
import android.media.Ringtone
import android.net.Uri
import com.amanullah.alarmclock.adapter.AlarmReceiver


class DestinationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDestinationBinding

    private lateinit var alarmManager: AlarmManager
    private lateinit var alarm: Uri
    private lateinit var track: Ringtone

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDestinationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        track = RingtoneManager.getRingtone(applicationContext, alarm)

        val firstRandomNumber = Random.nextInt(0, 10)
        val secondRandomNumber = Random.nextInt(20, 30)


        binding.descriptionTextView.text = "What is the Sum of ${firstRandomNumber} & ${secondRandomNumber}?"

        binding.submitButton.setOnClickListener {

            val answer = binding.outlinedTextField.editText?.text.toString().toInt()

            val result = firstRandomNumber + secondRandomNumber

            if (answer == result) {
                stop()

                Toast.makeText(applicationContext, "Pass", Toast.LENGTH_SHORT).show()
            } else {
                play()
                Toast.makeText(applicationContext, "Fail", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun play() {
        try {
            track.play()
        } catch (e: Exception) {
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun stop() {
        try {
            //track.stop()

            alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(applicationContext, AlarmReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                applicationContext,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or Intent.FILL_IN_DATA
            )
            alarmManager.cancel(pendingIntent)

        } catch (e: Exception) {
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
        }
    }
}