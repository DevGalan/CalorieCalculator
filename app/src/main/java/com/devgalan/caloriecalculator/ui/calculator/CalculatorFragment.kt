package com.devgalan.caloriecalculator.ui.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devgalan.caloriecalculator.R
import com.devgalan.caloriecalculator.databinding.FragmentCalculatorBinding
import com.devgalan.caloriecalculator.domain.ActivityLevel
import com.devgalan.caloriecalculator.domain.Calculation
import com.devgalan.caloriecalculator.domain.Objetive
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class CalculatorFragment : Fragment() {

    private val calculatorViewModel by viewModels<CalculatorViewModel>()

    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!

    private var counter = 0
    private var firstAdShowed = false
    private var interstitial: InterstitialAd? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAds()

        initListeners()

        initUI()
    }

    private fun initListeners() {
        interstitial?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdShowedFullScreenContent() {
                interstitial = null
            }
        }
    }

    private fun initAds() {
        var adRequest = AdRequest.Builder().build()
        val context = binding.rsAge.context

        InterstitialAd.load(context, context.getString(R.string.admob_interstitial_id), adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    interstitial = interstitialAd
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    interstitial = null
                }
            })
    }

    private fun initUI() {

        reloadGenderButtons()

        binding.rsHeight.setValues(calculatorViewModel.calculationData.height.toFloat())
        binding.tvHeight.text =
            calculatorViewModel.calculationData.height.toString() + " " + context?.getString(R.string.cm)

        binding.rsWeight.setValues(calculatorViewModel.calculationData.weight.toFloat())
        binding.tvWeight.text =
            calculatorViewModel.calculationData.weight.toString() + " " + context?.getString(R.string.kg)

        binding.rsAge.setValues(calculatorViewModel.calculationData.age.toFloat())
        binding.tvAge.text = calculatorViewModel.calculationData.age.toInt()
            .toString() + " " + context?.getString(R.string.years)

        initEvents()
    }

    private fun initEvents() {
        binding.cvMale.setOnClickListener() {
            calculatorViewModel.calculationData.male = true
            reloadGenderButtons()
        }

        binding.cvFemale.setOnClickListener() {
            calculatorViewModel.calculationData.male = false
            reloadGenderButtons()
        }

        binding.fabSubtractHeight.setOnClickListener {
            calculatorViewModel.calculationData.height -= 0.25f
            if (calculatorViewModel.calculationData.height < 110f) calculatorViewModel.calculationData.height =
                110f
            binding.rsHeight.setValues(calculatorViewModel.calculationData.height.toFloat())
            binding.tvHeight.text =
                calculatorViewModel.calculationData.height.toString() + " " + context?.getString(R.string.cm)
        }

        binding.fabAddHeight.setOnClickListener {
            calculatorViewModel.calculationData.height += 0.25f
            if (calculatorViewModel.calculationData.height > 220) calculatorViewModel.calculationData.height =
                220f
            binding.rsHeight.setValues(calculatorViewModel.calculationData.height.toFloat())
            binding.tvHeight.text =
                calculatorViewModel.calculationData.height.toString() + " " + context?.getString(R.string.cm)
        }

        binding.fabSubtractWeight.setOnClickListener {
            calculatorViewModel.calculationData.weight -= 0.25f
            if (calculatorViewModel.calculationData.weight < 30f) calculatorViewModel.calculationData.weight =
                30f
            binding.rsWeight.setValues(calculatorViewModel.calculationData.weight.toFloat())
            binding.tvWeight.text =
                calculatorViewModel.calculationData.weight.toString() + " " + context?.getString(R.string.kg)
        }

        binding.fabAddWeight.setOnClickListener {
            calculatorViewModel.calculationData.weight += 0.25f
            if (calculatorViewModel.calculationData.weight > 300) calculatorViewModel.calculationData.weight =
                300f
            binding.rsWeight.setValues(calculatorViewModel.calculationData.weight.toFloat())
            binding.tvWeight.text =
                calculatorViewModel.calculationData.weight.toString() + " " + context?.getString(R.string.kg)
        }

        binding.fabSubtractAge.setOnClickListener {
            calculatorViewModel.calculationData.age--
            if (calculatorViewModel.calculationData.age < 1) calculatorViewModel.calculationData.age =
                1
            binding.rsAge.setValues(calculatorViewModel.calculationData.age.toFloat())
            binding.tvAge.text = calculatorViewModel.calculationData.age.toInt()
                .toString() + " " + context?.getString(R.string.years)
        }

        binding.fabAddAge.setOnClickListener {
            calculatorViewModel.calculationData.age++
            if (calculatorViewModel.calculationData.age > 120) calculatorViewModel.calculationData.age =
                120
            binding.rsAge.setValues(calculatorViewModel.calculationData.age.toFloat())
            binding.tvAge.text = calculatorViewModel.calculationData.age.toInt()
                .toString() + " " + context?.getString(R.string.years)
        }

        binding.rsHeight.addOnChangeListener { slider, value, fromUser ->
            calculatorViewModel.calculationData.height = value
            binding.tvHeight.text = value.toString() + " " + context?.getString(R.string.cm)
        }

        binding.rsWeight.addOnChangeListener { slider, value, fromUser ->
            calculatorViewModel.calculationData.weight = value
            binding.tvWeight.text = value.toString() + " " + context?.getString(R.string.kg)
        }

        binding.rsAge.addOnChangeListener { slider, value, fromUser ->
            calculatorViewModel.calculationData.age = value.toInt().toByte()
            binding.tvAge.text = value.toInt().toString() + " " + context?.getString(R.string.years)
        }

        binding.rgActivityLevel.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbSedentary -> calculatorViewModel.calculationData.activityLevel =
                    ActivityLevel.Sedentary

                R.id.rbLightlyActive -> calculatorViewModel.calculationData.activityLevel =
                    ActivityLevel.LightlyActive

                R.id.rbModeratelyActive -> calculatorViewModel.calculationData.activityLevel =
                    ActivityLevel.ModeratelyActive

                R.id.rbVeryActive -> calculatorViewModel.calculationData.activityLevel =
                    ActivityLevel.VeryActive

                R.id.rbExtraActive -> calculatorViewModel.calculationData.activityLevel =
                    ActivityLevel.ExtraActive
            }
        }

        binding.rgObjetive.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbLoseWeightFast -> calculatorViewModel.calculationData.goal =
                    Objetive.LOSE_WEIGHT_FAST

                R.id.rbLoseWeight -> calculatorViewModel.calculationData.goal = Objetive.LOSE_WEIGHT
                R.id.rbMaintainWeight -> calculatorViewModel.calculationData.goal =
                    Objetive.MAINTAIN_WEIGHT

                R.id.rbGainWeight -> calculatorViewModel.calculationData.goal = Objetive.GAIN_WEIGHT
                R.id.rbGainWeightFast -> calculatorViewModel.calculationData.goal =
                    Objetive.GAIN_WEIGHT_FAST
            }
        }

        binding.btnCalculate.setOnClickListener {
            val calculation = Calculation(calculatorViewModel.calculationData)
            val result = calculation.calculateAndGetResult()
            findNavController().navigate(
                CalculatorFragmentDirections.actionCalculatorFragmentToCalculationResultActivity(
                    result,
                    calculatorViewModel.calculationData.goal
                )
            )
            checkCounter()
        }
    }

    private fun reloadGenderButtons() {
        val context = binding.cvFemale.context
        if (calculatorViewModel.calculationData.male) {
            binding.cvMale.setCardBackgroundColor(context.getColor(R.color.primary_accent))
            binding.cvFemale.setCardBackgroundColor(context.getColor(R.color.secondary))
        } else {
            binding.cvMale.setCardBackgroundColor(context.getColor(R.color.secondary))
            binding.cvFemale.setCardBackgroundColor(context.getColor(R.color.primary_accent))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkCounter() {
        if (++counter >= 3 || !firstAdShowed) {
            firstAdShowed = true
            counter = 0
            showInterstitial()
            initAds()
        }
    }

    private fun showInterstitial() {
        interstitial?.show(requireActivity())
    }
}