package com.devgalan.caloriecalculator.domain

data class CalculationData(var male: Boolean = true, var weight: Float = 75f, var height: Float = 175f, var age: Byte = 25, var activityLevel: ActivityLevel = ActivityLevel.ModeratelyActive, var goal: Objetive = Objetive.MAINTAIN_WEIGHT) {
}