package vn.thonghkk.calculatorapplication.internal

import vn.thonghkk.calculatorapplication.enums.CalculationEnum

fun String.isNumber(): Boolean {
    return try {
        Integer.parseInt(this)
        true
    } catch (e: Exception) {
        false
    }
}

fun String.isCalculationPriority(): Boolean {
    return this.lowercase() == "x" || this.lowercase() == "/"
}

fun String.calculation(firstNumber: Double, secondNumber: Double): Double {
    return when(this) {
        CalculationEnum.PLUS.value.lowercase() -> firstNumber.plus(secondNumber)
        CalculationEnum.TIME.value.lowercase() -> firstNumber.times(secondNumber)
        CalculationEnum.MINUS.value.lowercase() -> firstNumber.minus(secondNumber)
        CalculationEnum.DIVIDE.value.lowercase() -> firstNumber.div(secondNumber)
        else -> 0.0
    }
}
