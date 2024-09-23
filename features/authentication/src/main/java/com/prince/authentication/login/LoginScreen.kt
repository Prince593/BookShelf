package com.prince.authentication.login

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.prince.authentication.AuthState
import com.prince.authentication.AuthViewModel
import com.prince.authentication.R
import com.prince.authentication.components.EmailTextField
import com.prince.authentication.components.PasswordTextField
import com.prince.theme.BookShelfTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = hiltViewModel(),
    onClickSignup: () -> Unit = {},
    onLoginIn: (userId: String) -> Unit
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .then(modifier)
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.15f))
        //Header
        LoginHeaderView()

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

        val authState by authViewModel.authState.collectAsStateWithLifecycle()

        when (authState) {
            is AuthState.Initial, is AuthState.Error -> {
                //Login button
                Button(modifier = Modifier.fillMaxWidth(), onClick = {
                    keyboardController?.hide()
                    authViewModel.login()
                }) {
                    Text(text = stringResource(R.string.login))
                }

                //Error message after login is processed
                if (authState is AuthState.Error) {
                    Toast.makeText(
                        context,
                        (authState as AuthState.Error).message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            AuthState.Loading -> {
                // Show loading indicator when login is in progress
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(16.dp), strokeWidth = 2.dp)
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = stringResource(R.string.logging_you_in))
                }
            }

            is AuthState.Success -> {
                // Show success message when login is successful
                Toast.makeText(
                    context,
                    stringResource(R.string.login_successful), Toast.LENGTH_SHORT
                ).show()

                // call this lambda and provider user email
                onLoginIn((authState as AuthState.Success).user.email)
            }
        }

        // Signup text
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(R.string.don_t_have_an_account))
            TextButton(onClick = onClickSignup) {
                Text(text = stringResource(R.string.signup))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLoginScreen() {
    BookShelfTheme {
        LoginScreen {

        }
    }
}