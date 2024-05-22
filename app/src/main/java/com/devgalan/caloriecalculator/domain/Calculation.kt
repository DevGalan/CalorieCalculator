package com.devgalan.caloriecalculator.domain

import kotlin.math.roundToInt

class Calculation(val calculationData:CalculationData, private var result: Float = 0f) {

    public fun calculateAndGetResult(): Int {
        calculate()
        return result.toInt()
    }

    private fun calculate() {
        if (calculationData.male) {
            result = (10 * calculationData.weight) + (6.25f * calculationData.height) - (5 * calculationData.age) + 5
        } else {
            result = (10 * calculationData.weight) + (6.25f * calculationData.height) - (5 * calculationData.age) - 161
        }
        when (calculationData.activityLevel) {
            ActivityLevel.Sedentary -> result *= 1.2f
            ActivityLevel.LightlyActive -> result *= 1.375f
            ActivityLevel.ModeratelyActive -> result *= 1.55f
            ActivityLevel.VeryActive -> result *= 1.725f
            ActivityLevel.ExtraActive -> result *= 1.9f
        }
        when (calculationData.goal) {
            Objetive.LOSE_WEIGHT_FAST -> result -= 400
            Objetive.LOSE_WEIGHT -> result -= 200
            Objetive.MAINTAIN_WEIGHT -> result += 0
            Objetive.GAIN_WEIGHT -> result += 200
            Objetive.GAIN_WEIGHT_FAST -> result += 400
        }
        result = result.roundToInt().toFloat()
    }
}