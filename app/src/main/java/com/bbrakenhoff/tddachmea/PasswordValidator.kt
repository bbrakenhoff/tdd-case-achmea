package com.bbrakenhoff.tddachmea

class PasswordValidator {

    fun isPasswordValid(password: String): Boolean =
        isPasswordLengthCorrect(password) && containsPasswordDigit(password)

    private fun isPasswordLengthCorrect(password: String) = password.length >= 8

    private fun containsPasswordDigit(password: String) = password.any { it.isDigit() }
}
