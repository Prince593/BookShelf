package com.prince.authentication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prince.authentication.domain.CountryUseCase
import com.prince.authentication.domain.UserUseCases
import com.prince.models.user.User
import com.prince.utils.di.IoDispatcher
import com.prince.utils.login.isValidEmail
import com.prince.utils.login.isValidPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val countryUseCase: CountryUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private var _authState = MutableStateFlow<AuthState>(AuthState.Initial)
    val authState = _authState.asStateFlow()

    val defaultCountry: String = "Select Country"
    private var _countryListState = MutableStateFlow(listOf<String>())
    val countryListState = _countryListState.asStateFlow()

    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var country by mutableStateOf(defaultCountry)
        private set
    var obscurePassword by mutableStateOf(true)
        private set

    var emailErrorMessage by mutableStateOf("")
    var passwordErrorMessage by mutableStateOf("")
    var countryErrorMessage by mutableStateOf("")

    fun updateEmail(value: String) {
        // on value change of email, reset error message
        emailErrorMessage = ""
        email = value
    }

    fun updatePassword(value: String) {
        // on value change of password, reset error message
        passwordErrorMessage = ""
        password = value
    }

    fun updateCountry(value: String) {
        // on value change of country, reset error message
        country = value
        countryErrorMessage = ""
    }

    fun togglePasswordVisibility() {
        // toggle password visibility on click of eye icon
        obscurePassword = !obscurePassword
    }

    /**
     * Get country list and country from ip using country use case
     * Update country list state and country state accordingly
     */
    fun getCountryList() {
        viewModelScope.launch(dispatcher) {
            countryUseCase.getCountryList().collect {
                _countryListState.value = mutableListOf<String>().apply {
                    add(defaultCountry)
                    addAll(it.mapNotNull { c -> c.country })
                }
            }

            countryUseCase.getCountryFromIp().collect {
                country = it
            }
        }
    }

    /**
     * Login function to check if email exists and then login
     * If email doesn't exist, return AuthState.Error with message
     */

    fun login() {
        // Login
        if (isDataValid()) {
            _authState.value = AuthState.Loading

            viewModelScope.launch(dispatcher) {
                delay(2000)
                if (!userUseCases.checkIfEmailExists(email.trim())) {
                    _authState.value = AuthState.Error("Account doesn't exist, Please sign up")
                } else {
                    userUseCases.getUser(email.trim(), password)?.let {
                        _authState.value = AuthState.Success(it)
                    } ?: run {
                        _authState.value = AuthState.Error("Invalid credentials")
                    }
                }
            }
        }
    }

    /**
     * Signup function to check if email exists and then signup
     * If email exists, return AuthState.Error with message
     */

    fun signUp() {
        // Sign up
        if (isDataValidForSignup()) {
            _authState.value = AuthState.Loading

            viewModelScope.launch(dispatcher) {
                delay(2000)
                if (userUseCases.checkIfEmailExists(email.trim())) {
                    _authState.value = AuthState.Error("Email already exists")
                } else {
                    userUseCases.insertUser(email.trim(), password, country)
                    _authState.value = AuthState.Success(userUseCases.getUser(email, password)!!)
                }
            }
        }
    }

    /**
     * Signup Data Validation
     * Check if email, password, and country are valid
     * If not, set error message accordingly
     * @return Boolean
     */
    private fun isDataValidForSignup(): Boolean {
        if (isDataValid()) {
            if (country == defaultCountry) {
                countryErrorMessage = "Country is required"
            } else {
                return true
            }
        }

        return false
    }

    /**
     * Data Validation
     * Check if email and password are valid
     * If not, set error message accordingly
     * @return Boolean
     */

    private fun isDataValid(): Boolean {
        when {
            email.trim().isEmpty() -> {
                emailErrorMessage = "Email is required"
            }

            !isValidEmail(email.trim()) -> {
                emailErrorMessage = "Invalid email"
            }

            password.isEmpty() -> {
                passwordErrorMessage = "Password is required"
            }

            !isValidPassword(password) -> {
                passwordErrorMessage =
                    "Password should be at least 8 characters, including at least one number, " +
                            "one special character -> !@#\$%^&*(), " +
                            "one lowercase letter, and one uppercase letter."
            }

            else -> {
                return true
            }
        }

        return false
    }
}

sealed class AuthState {
    data object Initial : AuthState()
    data object Loading : AuthState()
    data class Success(val user: User) : AuthState()
    data class Error(val message: String) : AuthState()
}