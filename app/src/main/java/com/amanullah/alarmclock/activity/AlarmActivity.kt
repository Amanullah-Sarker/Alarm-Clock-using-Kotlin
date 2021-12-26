package com.amanullah.alarmclock.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.amanullah.alarmclock.R
import com.amanullah.alarmclock.databinding.ActivityAlarmBinding
import com.amanullah.alarmclock.fragment.AlarmHomeFragment

class AlarmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(AlarmHomeFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }
}