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
        headers = listOf("What is a calorie?", "How to calculate BMR?", "How to calculate TDEE?")
        bodies = HashMap()
        bodies["What is a calorie?"] = listOf("A calorie is a unit of energy. In nutrition, calories refer to the energy people get from the food and drink they consume, and the energy they use in physical activity.")
        bodies["How to calculate BMR?"] = listOf("The Harris-Benedict equation is a method used to estimate an individual's basal metabolic rate (BMR) and daily kilocalorie requirements. The estimated BMR value is multiplied by a number that corresponds to the individual's activity level.")
        bodies["How to calculate TDEE?"] = listOf("Total Daily Energy Expenditure (TDEE) is an estimate of how many calories you burn per day when exercise is taken into account. It is calculated by first figuring out your Basal Metabolic Rate, then multiplying that value by an activity multiplier.")
    }

    private fun initUI() {
        expandableListView = binding.expandableListView
        expandableListAdapter = HelpExpandableListAdapter(binding.expandableListView.context, headers, bodies)
        expandableListView.setAdapter(expandableListAdapter)
    }
}