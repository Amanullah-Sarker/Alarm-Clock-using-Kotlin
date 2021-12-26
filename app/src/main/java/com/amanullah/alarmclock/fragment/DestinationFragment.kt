package com.amanullah.alarmclock.fragment

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.amanullah.alarmclock.adapter.AlarmReceiver
import com.amanullah.alarmclock.adapter.MainViewModel
import com.amanullah.alarmclock.databinding.FragmentDestinationBinding
import kotlin.random.Random

class DestinationFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    private lateinit var binding: FragmentDestinationBinding

    private lateinit var alarmManager: AlarmManager
    private lateinit var alarm: Uri
    private lateinit var track: Ringtone

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDestinationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        activity?.run {
            viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        } ?: throw Throwable("invalid activity")
        viewModel.updateActionBarTitle("Snooze")

        alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        track = RingtoneManager.getRingtone(context, alarm)

        val firstRandomNumber = Random.nextInt(0, 10)
        val secondRandomNumber = Random.nextInt(20, 30)


        binding.descriptionTextView.text = "What is the Sum of ${firstRandomNumber} & ${secondRandomNumber}?"

        binding.submitButton.setOnClickListener {

            val answer = binding.outlinedTextField.editText?.text.toString().toInt()

            val result = firstRandomNumber + secondRandomNumber

            if (answer == result) {
                stop()
            } else {
                play()
            }
        }
    }

    private fun play() {
        try {
            track.play()
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun stop() {
        try {
            //track.stop()

            alarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, AlarmReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or Intent.FILL_IN_DATA
            )
            alarmManager.cancel(pendingIntent)

        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }
}