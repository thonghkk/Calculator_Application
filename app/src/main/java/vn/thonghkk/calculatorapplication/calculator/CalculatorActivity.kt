package vn.thonghkk.calculatorapplication.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.layout_keyboard.*
import vn.thonghkk.calculatorapplication.databinding.ActivityCalculatorBinding
import vn.thonghkk.calculatorapplication.enums.CalculationEnum
import vn.thonghkk.calculatorapplication.enums.NumberEnum

class CalculatorActivity : AppCompatActivity() {

    private val viewModel = CalculatorViewModel()

    private var binding: ActivityCalculatorBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel
        setContentView(binding?.root)
        initUI()
    }

    private fun initUI() {
        initOnclickInputNumber()
        initOnclickInputCalculator()
    }

    private fun initOnclickInputNumber() {
        txtNumberOne.setOnClickListener {
            viewModel.inputNumber(NumberEnum.ONE)
        }
        txtNumberTwo.setOnClickListener {
            viewModel.inputNumber(NumberEnum.TWO)
        }
        txtNumberThree.setOnClickListener {
            viewModel.inputNumber(NumberEnum.THREE)
        }
        txtNumberFour.setOnClickListener {
            viewModel.inputNumber(NumberEnum.FOUR)
        }
        txtNumberFive.setOnClickListener {
            viewModel.inputNumber(NumberEnum.FIVE)
        }
        txtNumberSix.setOnClickListener {
            viewModel.inputNumber(NumberEnum.SIX)
        }
        txtNumberSeven.setOnClickListener {
            viewModel.inputNumber(NumberEnum.SEVEN)
        }
        txtNumberEight.setOnClickListener {
            viewModel.inputNumber(NumberEnum.EIGHT)
        }
        txtNumberNine.setOnClickListener {
            viewModel.inputNumber(NumberEnum.NINE)
        }
        txtNumberZero.setOnClickListener {
            viewModel.inputNumber(NumberEnum.ZERO)
        }
    }

    private fun initOnclickInputCalculator() {
        txtPlus.setOnClickListener {
            viewModel.inputCalculation(CalculationEnum.PLUS)
        }
        txtMinus.setOnClickListener {
            viewModel.inputCalculation(CalculationEnum.MINUS)
        }

        txtTime.setOnClickListener {
            viewModel.inputCalculation(CalculationEnum.TIME)
        }
        divide.setOnClickListener {
            viewModel.inputCalculation(CalculationEnum.DIVIDE)
        }
        divideByRemainder.setOnClickListener {
//            viewModel.inputCalculation(CalculationEnum.DIVIDE_BY_REMAINDER)
        }
    }
}