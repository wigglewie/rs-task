package com.gmail.wigglewie.rsfinaltask.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.gmail.wigglewie.rsfinaltask.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        val toolbar: Toolbar = findViewById(R.id.toolbar_settings)
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.label_activity_settings)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
            bindPreferenceSummaryToValue(findPreference(getString(R.string.key_dark_mode))!!)
        }
    }

    companion object {

        private fun bindPreferenceSummaryToValue(preference: Preference) {
            preference.onPreferenceChangeListener = sBindPreferenceSummaryToValueListener

            sBindPreferenceSummaryToValueListener.onPreferenceChange(
                preference,
                PreferenceManager
                    .getDefaultSharedPreferences(preference.context)
                    .getBoolean(preference.key, false)
            )
        }

        private val sBindPreferenceSummaryToValueListener =
            Preference.OnPreferenceChangeListener { preference, newValue ->
                val b = newValue as Boolean
                if (b) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                true
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
