package com.amanullah.alarmclock.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.amanullah.alarmclock.R
import com.amanullah.alarmclock.adapter.MainViewModel
import com.amanullah.alarmclock.databinding.ActivityAlarmBinding
import com.amanullah.alarmclock.fragment.AlarmHomeFragment

class AlarmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlarmBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.title.observe(this, Observer {
            supportActionBar?.title = it
        })

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = "Home"

        replaceFragment(AlarmHomeFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }
}