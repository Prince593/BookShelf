package com.prince.booklist.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import com.prince.booklist.R

@Composable
fun LogoutConfirmationDialog(
    onLogout: () -> Unit,
    onDismissRequest: () -> Unit = {},
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = stringResource(R.string.logout))
        },
        text = {
            Text(stringResource(R.string.do_you_want_to_logout))
        },
        confirmButton = {
            Button(
                onClick = onLogout
            ) {
                Text(stringResource(R.string.yes))
            }
        },
        dismissButton = {
            Button(
                onClick = onDismissRequest
            ) {
                Text(stringResource(R.string.no))
            }
        },
        properties = DialogProperties(dismissOnClickOutside = true)
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewLogoutConfirmationDialog() {
    LogoutConfirmationDialog(onLogout = {}, onDismissRequest = {})
}