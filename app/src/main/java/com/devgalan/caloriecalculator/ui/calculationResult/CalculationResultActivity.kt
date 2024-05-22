package com.devgalan.caloriecalculator.ui.calculationResult

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.navArgs
import com.devgalan.caloriecalculator.R
import com.devgalan.caloriecalculator.databinding.ActivityCalculationResultBinding
import com.devgalan.caloriecalculator.domain.Objetive
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalculationResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculationResultBinding
    private val calculationResultViewModel:CalculationResultViewModel by viewModels()

    private val args: CalculationResultActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCalculationResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        val context = binding.resultTitle.context
        binding.caloriesResult.text = context.getString(R.string.calories_result,
            when (args.goal) {
                Objetive.LOSE_WEIGHT_FAST -> context.getString(R.string.lose_weight_fast)
                Objetive.LOSE_WEIGHT -> context.getString(R.string.lose_weight)
                Objetive.MAINTAIN_WEIGHT -> context.getString(R.string.maintain_weight)
                Objetive.GAIN_WEIGHT -> context.getString(R.string.gain_weight)
                Objetive.GAIN_WEIGHT_FAST -> context.getString(R.string.gain_weight_fast)
            }, args.result)
    }
}