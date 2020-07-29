package com.bbrakenhoff.tddachmea

class PasswordValidator {

    fun isPasswordValid(password: String): Boolean =
        isPasswordLengthCorrect(password) && containsPasswordDigit(password) && containsUppercaseCharacter(password) && containsLowercaseCharacter(password)

    private fun containsUppercaseCharacter(password: String) = password.any { it.isUpperCase() }

    private fun containsLowercaseCharacter(password: String) = password.any { it.isLowerCase() }

    private fun isPasswordLengthCorrect(password: String) = password.length >= 8

    private fun containsPasswordDigit(password: String) = password.any { it.isDigit() }
}
