package com.gmail.wigglewie.rs_task4.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.gmail.wigglewie.rs_task4.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        return
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.settings)
    }
}