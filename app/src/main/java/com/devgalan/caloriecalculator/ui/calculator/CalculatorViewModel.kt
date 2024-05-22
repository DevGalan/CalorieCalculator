package com.devgalan.caloriecalculator.ui.calculator

import androidx.lifecycle.ViewModel
import com.devgalan.caloriecalculator.domain.CalculationData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor() : ViewModel() {
    private var _calculationData: CalculationData = CalculationData()
    val calculationData: CalculationData
        get() = _calculationData
}