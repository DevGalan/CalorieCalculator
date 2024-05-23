package com.devgalan.caloriecalculator.ui.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import androidx.fragment.app.Fragment
import com.devgalan.caloriecalculator.R
import com.devgalan.caloriecalculator.databinding.FragmentHelpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HelpFragment : Fragment() {

    private lateinit var expandableListView: ExpandableListView
    private lateinit var expandableListAdapter: ExpandableListAdapter
    private lateinit var headers: List<String>
    private lateinit var bodies: HashMap<String, List<String>>

    private var _binding: FragmentHelpBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHelpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initUI()
    }

    private fun initData() {
        headers = listOf(
            getString(R.string.help_header_calorie),
            getString(R.string.help_header_bmr_calculation),
            getString(R.string.help_header_healthy_weight_gain),
            getString(R.string.help_header_maintain_weight),
            getString(R.string.help_header_healthy_weight_loss),
            getString(R.string.help_header_bmr),
            getString(R.string.help_header_bmr_weight_effect),
            getString(R.string.help_header_increase_bmr),
            getString(R.string.help_header_age_bmr_effect),
            getString(R.string.help_header_gender_bmr_effect),
            getString(R.string.help_header_muscle_mass_bmr_effect),
            getString(R.string.help_header_diet_bmr_effect),
            getString(R.string.help_header_exercise_bmr_effect),
            getString(R.string.help_header_stress_bmr_effect),
            getString(R.string.help_header_climate_bmr_effect)
        )
        bodies = HashMap()
        bodies[getString(R.string.help_header_calorie)] = listOf(getString(R.string.help_body_calorie))
        bodies[getString(R.string.help_header_bmr_calculation)] = listOf(getString(R.string.help_body_bmr_calculation))
        bodies[getString(R.string.help_header_bmr)] = listOf(getString(R.string.help_body_bmr))
        bodies[getString(R.string.help_header_healthy_weight_gain)] = listOf(getString(R.string.help_body_healthy_weight_gain))
        bodies[getString(R.string.help_header_maintain_weight)] = listOf(getString(R.string.help_body_maintain_weight))
        bodies[getString(R.string.help_header_healthy_weight_loss)] = listOf(getString(R.string.help_body_healthy_weight_loss))
        bodies[getString(R.string.help_header_bmr_weight_effect)] = listOf(getString(R.string.help_body_bmr_weight_effect))
        bodies[getString(R.string.help_header_increase_bmr)] = listOf(getString(R.string.help_body_increase_bmr))
        bodies[getString(R.string.help_header_age_bmr_effect)] = listOf(getString(R.string.help_body_age_bmr_effect))
        bodies[getString(R.string.help_header_gender_bmr_effect)] = listOf(getString(R.string.help_body_gender_bmr_effect))
        bodies[getString(R.string.help_header_muscle_mass_bmr_effect)] = listOf(getString(R.string.help_body_muscle_mass_bmr_effect))
        bodies[getString(R.string.help_header_diet_bmr_effect)] = listOf(getString(R.string.help_body_diet_bmr_effect))
        bodies[getString(R.string.help_header_exercise_bmr_effect)] = listOf(getString(R.string.help_body_exercise_bmr_effect))
        bodies[getString(R.string.help_header_stress_bmr_effect)] = listOf(getString(R.string.help_body_stress_bmr_effect))
        bodies[getString(R.string.help_header_climate_bmr_effect)] = listOf(getString(R.string.help_body_climate_bmr_effect))
    }

    private fun initUI() {
        expandableListView = binding.expandableListView
        expandableListAdapter = HelpExpandableListAdapter(binding.expandableListView.context, headers, bodies)
        expandableListView.setAdapter(expandableListAdapter)
    }
}