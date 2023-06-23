package vn.thonghkk.calculatorapplication.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import vn.thonghkk.calculatorapplication.enums.CalculationEnum
import vn.thonghkk.calculatorapplication.enums.NumberEnum
import vn.thonghkk.calculatorapplication.internal.CalculatorUtils
import vn.thonghkk.calculatorapplication.internal.isNumber

class CalculatorViewModel : ViewModel() {

    private val calculatorShowingSubject = BehaviorSubject.create<List<String>>()

    private val inputCompositeDisposable = CompositeDisposable()

    private val _textShowTotal = MutableLiveData<String>()
    private val _textEntering = MutableLiveData<String>()
    private val _isBlockKeyboard = MutableLiveData(false)

    val textShowTotal: LiveData<String> get() = _textShowTotal
    val textEntering: LiveData<String> get() = _textEntering

    init {
        registerHandleNumberInputFromUser()
    }

    private val listInput = mutableListOf<String>()

    private var inputNumber = ""

    private fun registerHandleNumberInputFromUser() {
        calculatorShowingSubject.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                if (it.isNullOrEmpty()) _textShowTotal.postValue("")
                if (it.lastOrNull()?.isNumber() == true) {
                    try {
                        val result = CalculatorUtils.getResultAfterCalculatorFromUserInput(it)
                        _textShowTotal.postValue(result.toString())
                        _isBlockKeyboard.postValue(false)
                    } catch (e: Exception) {
                        _isBlockKeyboard.postValue(true)
                    }

                }
            }.addTo(inputCompositeDisposable)
    }

    fun inputNumber(numberEnum: NumberEnum) {
        if (_isBlockKeyboard.value == false) {
            inputNumber += numberEnum.value.toString()
            listInput.add(numberEnum.value.toString())
            _textEntering.postValue(listInput.joinToString(""))
            calculatorShowingSubject.onNext(listInput)
        }
    }

    fun removeTextEntering() {
        if (listInput.isNotEmpty()) {
            listInput.removeLast()
            _textEntering.postValue(listInput.joinToString(""))
            calculatorShowingSubject.onNext(listInput)
        }
    }

    fun clearAllUserInput() {
        listInput.clear()
        _textEntering.postValue(listInput.joinToString(""))
        calculatorShowingSubject.onNext(listInput)
    }

    fun inputCalculation(calculationEnum: CalculationEnum) {
        if (inputNumber.isNotEmpty() && _isBlockKeyboard.value == false) {
            inputNumber = ""
            listInput.add(calculationEnum.value)
            _textEntering.postValue(listInput.joinToString(""))
            calculatorShowingSubject.onNext(listInput)
        }
    }

}