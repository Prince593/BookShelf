package com.prince.authentication.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prince.authentication.R
import com.prince.theme.BookShelfTheme

@Composable
fun LoginHeaderView() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = Modifier.fillMaxWidth(0.2f),
            painter = painterResource(id = R.drawable.login), contentDescription = null
        )
        Column(Modifier.padding(start = 16.dp)) {
            Text(
                text = stringResource(id = R.string.login),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.login_to_your_account),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLoginHeaderView() {
    BookShelfTheme {
        LoginHeaderView()
    }
}