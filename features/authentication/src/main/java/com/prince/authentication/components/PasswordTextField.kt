package com.prince.authentication.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prince.authentication.R
import com.prince.authentication.custom.CustomOutlinedTextField
import com.prince.authentication.custom.ErrorText
import com.prince.theme.BookShelfTheme

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    obscureText: Boolean = true,
    isError : Boolean = false,
    errorText : String = "",
    togglePasswordVisibility: () -> Unit = {}
) {
    CustomOutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        label = {
            Text(
                text = stringResource(id = R.string.password),
                style = MaterialTheme.typography.bodyLarge
            )
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Rounded.Lock,
                contentDescription = null
            )
        },
        isError = isError,
        supportingText = {
            if (isError) {
                ErrorText(text = errorText)
            }
        },
        trailingIcon = {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        togglePasswordVisibility()
                    },
                painter = painterResource(id = if (obscureText) R.drawable.ic_visibility_on else R.drawable.ic_visibility_off),
                contentDescription = null
            )
        },
        value = value,
        onValueChange = onValueChange,
        visualTransformation = if (obscureText) PasswordVisualTransformation() else VisualTransformation.None,
    )
}


//Previews

@Preview(showBackground = true)
@Composable
private fun PasswordTextFieldPreview() {
    BookShelfTheme {
        PasswordTextField(Modifier.padding(12.dp), value = "", onValueChange = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFieldPreviewObscured() {
    BookShelfTheme {
        PasswordTextField(Modifier.padding(12.dp), value = "12345678", onValueChange = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFieldPreviewNonObscured() {
    BookShelfTheme {
        PasswordTextField(
            Modifier.padding(12.dp), value = "12345678", onValueChange = {}, obscureText = false
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFieldErrorPreview() {
    BookShelfTheme {
        PasswordTextField(
            Modifier.padding(12.dp), value = "", onValueChange = {}, isError = true, errorText = "Enter password"
        )
    }
}