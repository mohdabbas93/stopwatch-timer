package com.mohdabbas.stopwatchtimer.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mohdabbas.stopwatchtimer.R
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
        pauseResumeButton.setOnClickListener { onPauseResumeButtonClicked() }
    }

    private var isTimerStarted = false
    private lateinit var countDownTimer: CountDownTimer
    private var remainingMilliseconds = 0L

    private fun onStartCancelClicked(totalTimeInMillis: Long) {
        if (!isTimerStarted) {
            isTimerStarted = true
            binding?.startCancelButton?.text = getString(R.string.cancel)

            countDownTimer = object : CountDownTimer(totalTimeInMillis, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    remainingMilliseconds = millisUntilFinished
                    binding?.timerText?.text = getTimeText(millisUntilFinished)
                }

                override fun onFinish() {
                    Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
                }
            }.apply { start() }
        } else {
            isTimerStarted = false
            countDownTimer.cancel()
            binding?.startCancelButton?.text = getString(R.string.start)
            binding?.timerText?.text = getTimeText(totalTimeInMillis)
        }
    }

    private var isTimerPaused = false

    private fun onPauseResumeButtonClicked() {
        if (isTimerPaused) {
            isTimerPaused = false
            binding?.pauseResumeButton?.text = getString(R.string.pause)
            countDownTimer = object : CountDownTimer(remainingMilliseconds, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    remainingMilliseconds = millisUntilFinished
                    binding?.timerText?.text = getTimeText(millisUntilFinished)
                }

                override fun onFinish() {
                    Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
                }
            }.apply { start() }
        } else {
            isTimerPaused = true
            countDownTimer.cancel()
            binding?.pauseResumeButton?.text = getString(R.string.resume)
        }
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