package com.bbrakenhoff.tddachmea

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bbrakenhoff.tddachmea.ui.main.MainViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var passwordValidatorMock: PasswordValidator

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        passwordValidatorMock = mockk(relaxed = true)
        mainViewModel = MainViewModel(passwordValidatorMock)

        mainViewModel.password.observeForever { }
        mainViewModel.message.observeForever {}
    }

    @Test
    fun `should show message when password is invalid`() {
        // Arrange
        every { passwordValidatorMock.isPasswordValid(any()) } returns false

        // Act
        mainViewModel.password.value = "pizza"

        // Assert
        assertThat(mainViewModel.message.value).isEqualTo("Het gaat mis")
    }

    @Test
    fun `should show message when passwords are not equal`() {
        // Arrange
        every { passwordValidatorMock.isPasswordValid(any()) } returns true

        // Act
        mainViewModel.password.value = "pizza"
        mainViewModel.passwordConfirm.value = "friet"

        // Assert
        assertThat(mainViewModel.message.value).isEqualTo("Wachtwoorden ongelijk")
    }

    @Test
    fun `changeButtonPressed() should show success message when password is successfully changed`() {
        // Arrange
        every { passwordValidatorMock.isPasswordValid(any()) } returns true

        // Act
        mainViewModel.password.value = "pizza"
        mainViewModel.passwordConfirm.value = "pizza"
        mainViewModel.changeButtonPressed()

        // Assert
        assertThat(mainViewModel.message.value).isEqualTo("Gelukt!")
    }

    @Test
    fun `changeButtonPressed() should not show success message when passwords are not equal`() {
        // Arrange
        every { passwordValidatorMock.isPasswordValid(any()) } returns true

        // Act
        mainViewModel.password.value = "pizza"
        mainViewModel.passwordConfirm.value = "friet"
        mainViewModel.changeButtonPressed()

        // Assert
        assertThat(mainViewModel.message.value).isNotEqualTo("Gelukt!")
    }

    @Test
    fun `changeButtonPressed() should not show success message when password is invalid`() {
        // Arrange
        every { passwordValidatorMock.isPasswordValid(any()) } returns false

        // Act
        mainViewModel.password.value = "pizza"
        mainViewModel.passwordConfirm.value = "pizza"
        mainViewModel.changeButtonPressed()

        // Assert
        assertThat(mainViewModel.message.value).isNotEqualTo("Gelukt!")
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
