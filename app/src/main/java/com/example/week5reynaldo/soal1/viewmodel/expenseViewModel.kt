package com.example.week5reynaldo.soal1.viewmodel

import androidx.lifecycle.ViewModel
import com.example.week5reynaldo.soal1.model.expense
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.collections.filter
import kotlin.collections.map

class expenseViewModel: ViewModel() {
    private val _expenseList = MutableStateFlow<List<expense>>(emptyList())
    val expenseList: StateFlow<List<expense>> = _expenseList.asStateFlow()

    fun total(): Int {
        return _expenseList.value.sumOf { it.amount }
    }

    private val _nama = MutableStateFlow("")
    val nama: StateFlow<String> = _nama.asStateFlow()
    private val _amount = MutableStateFlow("")
    val amount: StateFlow<String> = _amount.asStateFlow()
    private val _namaError = MutableStateFlow<String?>(null)
    val namaError: StateFlow<String?> = _namaError.asStateFlow()
    private val _amountError = MutableStateFlow<String?>(null)
    val amountError: StateFlow<String?> = _amountError.asStateFlow()
    private val _lagiEdit = MutableStateFlow<expense?>(null)
    val lagiEdit: StateFlow<expense?> = _lagiEdit.asStateFlow()
    private val _editAmount = MutableStateFlow("")
    val editAmount = _editAmount.asStateFlow()
    private val _editError = MutableStateFlow(false)
    val editError = _editError.asStateFlow()


    private fun nextId(): Int {
        val idNow = if (_expenseList.value.isEmpty()) {0}
        else {_expenseList.value.last().id}
        return idNow + 1
    }

    fun namaChange(newNama: String) {
        _nama.value = newNama
        _namaError.value = null
    }
    fun amountChange(newAmount: String) {
        _amount.value = newAmount.filter {it.isDigit()}
        _amountError.value = null
    }
    fun addExpense() {
        val namaTemp = _nama.value.trim()
        val amountTemp = _amount.value.toIntOrNull()
        if (namaTemp.isEmpty()) {
            _namaError.value = "Your name have to not be empty!"
            return
        }
        if (amountTemp == null || amountTemp <= 0) {
            _amountError.value = "Amount must be greater than 0"
            return
        }
        _expenseList.update {it + expense(nextId(), namaTemp, amountTemp)}
        _nama.value = ""
        _amount.value = ""
        _namaError.value = null
        _amountError.value = null
    }
    fun deleteExpense(id: Int) {
        _expenseList.update { list ->
            list.filter{it.id != id}
        }
    }
    fun updateExpense(id: Int, newAmount: String): Boolean {
        val amountTemp: Int? = newAmount.filter {it.isDigit()}.toIntOrNull()
        if (amountTemp == null || amountTemp <= 0) {
            return false
        }
        _expenseList.update { list ->
            list.map {
                if (it.id == id) {
                    it.copy(amount = amountTemp)
                } else {
                    it
                }
            }
        }
        return true


    }

    fun edit(expense: expense) {
        _lagiEdit.value = expense
        _editAmount.value = expense.amount.toString()
        _editError.value = false
    }
    fun editAmount(newAmount: String) {
        _editAmount.value = newAmount.filter {it.isDigit()}
        _editError.value = false
    }
    fun cancel() {
        _lagiEdit.value = null
    }
    fun save() {
        val expense = _lagiEdit.value
        if (expense == null) {
            return
        }
        if (updateExpense(expense.id, _editAmount.value)) {
            _lagiEdit.value = null
        } else {
            _editError.value = true
        }
    }

    fun nominalFormat(amount: Int):String {
        return "Rp "+String.format("%,d", amount).replace(',', '.')
    }

}