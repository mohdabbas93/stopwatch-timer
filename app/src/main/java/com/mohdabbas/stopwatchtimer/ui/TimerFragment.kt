package com.mohdabbas.stopwatchtimer.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mohdabbas.stopwatchtimer.databinding.FragmentTimerBinding
import java.util.concurrent.TimeUnit

class TimerFragment : Fragment() {

    private var binding: FragmentTimerBinding? = null
    private var totalTimeInMillis = 30000L

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTimerBinding.inflate(inflater, container, false)
        binding?.timerText?.text = getTimeText(totalTimeInMillis)
        binding?.setupButtons()
        return binding?.root
    }

    private fun FragmentTimerBinding.setupButtons() {
        startCancelButton.setOnClickListener { onStartCancelClicked(totalTimeInMillis) }
    }

    private fun onStartCancelClicked(totalTimeInMillis: Long) {
        object : CountDownTimer(totalTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding?.timerText?.text = getTimeText(millisUntilFinished)
            }

            override fun onFinish() {
                Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    private fun getTimeText(time: Long): String {
        return String.format(
            "%02d:%02d:%02d",
            TimeUnit.MILLISECONDS.toHours(time),
            TimeUnit.MILLISECONDS.toMinutes(time),
            TimeUnit.MILLISECONDS.toSeconds(time)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}