package vn.thonghkk.calculatorapplication.internal

object CalculatorUtils {

    fun getResultAfterCalculatorFromUserInput(listInput: List<String>): Double {
        val mergeListInputToCalculator = mutableListOf<String>()
        mergeListInputToCalculator.addAll(mergeListInputToCalculator(listInput))
        val getListNumberCalculationPriority = mutableListOf<String>()
        getListNumberCalculationPriority.addAll(getListNumberCalculationPriority(mergeListInputToCalculator))
        return getResultAfterCalculator(getListNumberCalculationPriority)
    }

    private fun getResultAfterCalculator(listInput: List<String>): Double {
        if (listInput.size == 1) return listInput.firstOrNull()?.toDouble()?:0.0
        var result = 0.0
        var isFirstCalculation = false
        for (i in listInput.indices) {
            if (!listInput[i].isNumber()) {
                if (isFirstCalculation) {
                    result = listInput[i].calculation(result, listInput[i+1].toDouble())
                } else {
                    if (listInput.size > 1) {
                        result = listInput[i].calculation(listInput[i-1].toDouble(), listInput[i+1].toDouble())
                        isFirstCalculation = true
                    }
                }
            }
        }
        return result
    }

    private fun getListNumberCalculationPriority(listInput: List<String>): List<String> {
        val newList = mutableListOf<String>()
        var indexTempMerged = -1
        for (i in listInput.indices) {
            if (listInput[i].isCalculationPriority()) {
                newList.removeLastOrNull()
                val newValue = listInput[i].calculation(listInput[i-1].toDouble(), listInput[i+1].toDouble())
                newList.add(newValue.toString())
                indexTempMerged = i + 1
            } else {
                if (i != indexTempMerged) newList.add(listInput[i])
            }
        }
        return newList
    }

    private fun mergeListInputToCalculator(listInput: List<String>): List<String> {
        val listMergedNumber = mutableListOf<String>()
        var number = ""
        for (i in listInput.indices) {
            if (listInput[i].isNumber() && i != listInput.lastIndex) {
                number += listInput[i]
            } else {
                if (number.isNotEmpty()) {
                    if (!listInput[i].isNumber()) {
                        listMergedNumber.add(number)
                        listMergedNumber.add(listInput[i])
                    } else {
                        number += listInput[i]
                        listMergedNumber.add(number)
                    }
                    number =""
                } else {
                    listMergedNumber.add(listInput[i])
                }
            }
        }
        return listMergedNumber
    }
}