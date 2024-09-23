package com.prince.authentication.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prince.authentication.R
import com.prince.authentication.custom.CustomOutlinedTextField
import com.prince.authentication.custom.ErrorText
import com.prince.theme.BookShelfTheme

@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    isError : Boolean = false,
    errorText : String = ""
) {
    CustomOutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        label = {
            Text(
                text = stringResource(id = R.string.email),
                style = MaterialTheme.typography.bodyLarge
            )
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Rounded.Email,
                contentDescription = null
            )
        },
        value = value,
        onValueChange = onValueChange,
        isError = isError,
        supportingText = {
            if (isError) {
                ErrorText(text = errorText)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}

// Previews

@Preview(showBackground = true)
@Composable
private fun EmailTextFieldPreview() {
    BookShelfTheme {
        EmailTextField(modifier = Modifier.padding(12.dp), value = "", onValueChange = {})
    }
}


@Preview(showBackground = true)
@Composable
private fun EmailTextFieldErrorPreview() {
    BookShelfTheme {
        EmailTextField(modifier = Modifier.padding(12.dp), value = "test@gmail.com", onValueChange = {}, isError = true, errorText = "Invalid email")
    }
}