package vn.thonghkk.calculatorapplication

import vn.thonghkk.calculatorapplication.internal.CalculatorUtils

fun main() {
    val list = listOf("7","2","x","8","8","9","9","9","9","9","-","9")
    val total = CalculatorUtils.getResultAfterCalculatorFromUserInput(list)
    println(total)
}
