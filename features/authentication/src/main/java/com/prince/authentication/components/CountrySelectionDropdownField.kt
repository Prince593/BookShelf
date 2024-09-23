package com.prince.authentication.signup

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.prince.authentication.AuthViewModel
import com.prince.authentication.custom.CustomOutlinedTextField
import com.prince.authentication.custom.ErrorText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountrySelectionDropdownField(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    var isCountryListExpanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = isCountryListExpanded, onExpandedChange = {
            isCountryListExpanded = it
        }) {
        val countryList by authViewModel.countryListState.collectAsStateWithLifecycle()

        // Country Field Text
        CustomOutlinedTextField(
            value = authViewModel.country.ifEmpty { authViewModel.defaultCountry },
            onValueChange = {
                authViewModel.updateCountry(it)
            },
            readOnly = true,
            isError = authViewModel.countryErrorMessage.isNotEmpty(),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isCountryListExpanded)
            },
            supportingText = {
                // Error message for country field
                if (authViewModel.countryErrorMessage.isNotEmpty()) {
                    ErrorText(text = authViewModel.countryErrorMessage)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
        )

        // Country Field Dropdown
        ExposedDropdownMenu(
            expanded = isCountryListExpanded,
            onDismissRequest = { isCountryListExpanded = false }) {
            countryList.forEach { country ->
                DropdownMenuItem(
                    onClick = {
                        authViewModel.updateCountry(country)
                        isCountryListExpanded = false
                    },
                    text = {
                        Text(text = country)
                    }
                )
            }
        }
    }
}