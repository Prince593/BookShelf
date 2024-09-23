package com.prince.authentication.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.prince.authentication.R
import com.prince.theme.BookShelfTheme

@Composable
fun SignupHeaderView() {
    Column {
        Text(
            text = stringResource(id = R.string.signup),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(R.string.create_your_account),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSignupHeaderView() {
    BookShelfTheme {
        SignupHeaderView()
    }
}