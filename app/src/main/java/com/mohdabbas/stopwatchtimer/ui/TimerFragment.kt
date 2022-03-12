package com.mohdabbas.stopwatchtimer.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mohdabbas.stopwatchtimer.R
import com.mohdabbas.stopwatchtimer.databinding.FragmentTimerBinding
import com.mohdabbas.stopwatchtimer.util.makeGone
import com.mohdabbas.stopwatchtimer.util.makeVisible
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
            showPauseResumeButton()
            updateStartCancelButtonText(R.string.cancel)
            initializeAndStartCountDownTimer(totalTimeInMillis)
        } else {
            isTimerStarted = false
            hidePauseResumeButton()
            countDownTimer.cancel()
            updateStartCancelButtonText(R.string.start)
            binding?.timerText?.text = getTimeText(totalTimeInMillis)
        }
    }

    private fun showPauseResumeButton() {
        binding?.pauseResumeButton?.makeVisible()
    }

    private fun hidePauseResumeButton() {
        binding?.pauseResumeButton?.makeGone()
    }

    private var isTimerPaused = false

    private fun onPauseResumeButtonClicked() {
        if (isTimerPaused) {
            isTimerPaused = false
            updatePauseResumeButtonText(R.string.pause)
            initializeAndStartCountDownTimer(remainingMilliseconds)
        } else {
            isTimerPaused = true
            countDownTimer.cancel()
            updatePauseResumeButtonText(R.string.resume)
        }
    }

    private fun initializeAndStartCountDownTimer(timeInMillis: Long) {
        countDownTimer = object : CountDownTimer(timeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingMilliseconds = millisUntilFinished
                binding?.timerText?.text = getTimeText(millisUntilFinished)
            }

            override fun onFinish() {
                Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
                isTimerStarted = false
                updateStartCancelButtonText(R.string.start)
                updatePauseResumeButtonText(R.string.pause)
                hidePauseResumeButton()
            }
        }.apply { start() }
    }

    private fun getTimeText(time: Long): String {
        return String.format(
            "%02d:%02d:%02d",
            TimeUnit.MILLISECONDS.toHours(time),
            TimeUnit.MILLISECONDS.toMinutes(time),
            TimeUnit.MILLISECONDS.toSeconds(time)
        )
    }

    private fun updateStartCancelButtonText(textResId: Int) {
        binding?.startCancelButton?.updateButtonText(textResId)
    }

    private fun updatePauseResumeButtonText(textResId: Int) {
        binding?.pauseResumeButton?.updateButtonText(textResId)
    }

    private fun Button.updateButtonText(textResId: Int) {
        text = getString(textResId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}