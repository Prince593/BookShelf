package com.prince.utils.pref

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.prince.utils.constants.LOGGED_IN_USER_ID

object MyPref {

    fun getLoggedInUserId(context : Context): String? {
        return getSharedPref(context).getString(LOGGED_IN_USER_ID, "")
    }

    private fun getSharedPref(context: Context): SharedPreferences {
        return context.getSharedPreferences("MyPref", Context.MODE_PRIVATE)
    }

    fun clear(context: Context) {
        // Clear all data from Shared Preferences
        getSharedPref(context).edit().clear().apply()
    }

    @SuppressLint("ApplySharedPref")
    fun setLoggedInUserId(context: Context, userId: String) {
        getSharedPref(context).edit().putString(LOGGED_IN_USER_ID, userId).commit()
    }
}