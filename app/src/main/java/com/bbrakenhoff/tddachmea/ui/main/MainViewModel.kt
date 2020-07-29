package com.bbrakenhoff.tddachmea.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bbrakenhoff.tddachmea.PasswordValidator

class MainViewModel(private val passwordValidator: PasswordValidator) : ViewModel() {

    val password = MutableLiveData<String>()
    val passwordConfirm = MutableLiveData<String>()

    private val _message = MediatorLiveData<String>()
    val message: LiveData<String> = _message

    init {
        _message.addSource(password) { onPasswordChanged(it) }
        _message.addSource(passwordConfirm) { onPasswordConfirmChanged() }
    }

    private fun onPasswordChanged(password: String) {
        if (!passwordValidator.isPasswordValid(password)) {
            _message.value = "Het gaat mis"
        }
    }

    private fun onPasswordConfirmChanged() {
        if (!passwordsAreEqual()) {
            _message.value = "Wachtwoorden ongelijk"
        }
    }

    private fun passwordsAreEqual() = password.value == passwordConfirm.value

    fun changeButtonPressed() {
        if (passwordsAreEqual() && passwordValidator.isPasswordValid(password.value!!)) {
            _message.value = "Gelukt!"
        }
    }
}

//Choose password:
//
//Password must be filled in twice
//Password must exist of at least 8 chars
//Password must contain at least 1 digit
//Password must contain at least 1 capital letter
//Password must contain at least 1 lowercase letter
//Password must contain at least one punctuation char of @ ! # $ % _ *
//Password cannot contain whitespaces
//A message should be shown when passwords are not equal and button is clicked
//A message should be shown when passwords are invalid
//A message should be shown when choosing password succeeded
