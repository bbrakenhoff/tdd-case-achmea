package com.bbrakenhoff.tddachmea

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class PasswordValidatorTest {

    private lateinit var passwordValidator: PasswordValidator

    @Before
    fun before() {
        passwordValidator = PasswordValidator()
    }

    @Test
    fun `isPasswordValid() returns false when password exists of less the minimum 8 chars`() {
        // Arrange
        val password = "pizzzaa"

        // Act
        val isValid = passwordValidator.isPasswordValid(password)

        // Assert
        assertThat(isValid).isFalse()
    }

    @Test
    fun `isPasswordValid() returns true when password is 8 or more chars`() {
        // Arrange
        val password = "12345678"

        // Act
        val isValid = passwordValidator.isPasswordValid(password)

        // Assert
        assertThat(isValid).isTrue()
    }

    @Test
    fun `isPasswordValid() returns false when password contains no digit`(){
        // Arrange
        val password = "pizzzaaa"

        // Act
        val isValid = passwordValidator.isPasswordValid(password)

        // Assert
        assertThat(isValid).isFalse()
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
