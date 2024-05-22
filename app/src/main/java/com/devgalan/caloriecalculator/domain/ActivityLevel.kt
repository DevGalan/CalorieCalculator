package com.devgalan.caloriecalculator.domain

sealed class ActivityLevel {
    object Sedentary : ActivityLevel()
    object LightlyActive : ActivityLevel()
    object ModeratelyActive : ActivityLevel()
    object VeryActive : ActivityLevel()
    object ExtraActive : ActivityLevel()
}