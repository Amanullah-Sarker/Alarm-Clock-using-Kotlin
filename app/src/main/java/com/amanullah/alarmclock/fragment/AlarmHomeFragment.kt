package com.amanullah.alarmclock.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amanullah.alarmclock.R
import com.amanullah.alarmclock.databinding.FragmentAlarmHomeBinding

class AlarmHomeFragment : Fragment() {

    private lateinit var binding: FragmentAlarmHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAlarmHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.floatingActionButton.setOnClickListener {
            replaceFragment(AddAlarmFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }
}
