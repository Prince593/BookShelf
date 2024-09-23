package com.prince.authentication.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.prince.authentication.AuthState
import com.prince.authentication.AuthViewModel
import com.prince.authentication.R
import com.prince.authentication.components.EmailTextField
import com.prince.authentication.components.PasswordTextField

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = hiltViewModel(),
    onClickLogin: () -> Unit = {},
    onSingUp: () -> Unit
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = Unit) {
        authViewModel.getCountryList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .then(modifier)
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        SignupHeaderView()

        //Email field
        EmailTextField(
            value = authViewModel.email,
            onValueChange = {
                authViewModel.updateEmail(it)
            },
            isError = authViewModel.emailErrorMessage.isNotEmpty(),
            errorText = authViewModel.emailErrorMessage
        )

        //Password field
        PasswordTextField(
            value = authViewModel.password,
            onValueChange = {
                authViewModel.updatePassword(it)
            },
            togglePasswordVisibility = {
                authViewModel.togglePasswordVisibility()
            },
            obscureText = authViewModel.obscurePassword,
            isError = authViewModel.passwordErrorMessage.isNotEmpty(),
            errorText = authViewModel.passwordErrorMessage
        )

        //Country Selection Field
        CountrySelectionDropdownField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 16.dp)
        )

        // Signup Button and its state handling

        val authState by authViewModel.authState.collectAsStateWithLifecycle()

        when (authState) {
            is AuthState.Initial, is AuthState.Error -> {
                // Signup button and its state handling
                Button(modifier = Modifier.fillMaxWidth(), onClick = {
                    keyboardController?.hide()
                    authViewModel.signUp()
                }) {
                    Text(text = stringResource(R.string.signup))
                }

                if (authState is AuthState.Error) {
                    // Error message after signup is processed
                    Toast.makeText(
                        context,
                        (authState as AuthState.Error).message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            AuthState.Loading -> {
                // Loading state for signup
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(16.dp),
                        strokeWidth = 2.dp
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = stringResource(R.string.creating_your_account))
                }
            }

            is AuthState.Success -> {
                // Success state for signup and show success message when signup is successful
                Toast.makeText(
                    context,
                    stringResource(R.string.account_created_successfully_please_login),
                    Toast.LENGTH_SHORT
                ).show()
                onSingUp()
            }
        }


        // Login text and its click handling
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(R.string.already_have_an_account))
            TextButton(onClick = onClickLogin) {
                Text(text = stringResource(R.string.login))
            }
        }
    }
}