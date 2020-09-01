package com.dicoding.github.lastsubmission.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.dicoding.github.lastsubmission.R
import com.dicoding.github.lastsubmission.ui.alarm.AlarmReceiver

class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var reminderPreferences : SwitchPreferenceCompat
    private lateinit var reminder: String
    private lateinit var alarmReceiver: AlarmReceiver

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        alarmReceiver = AlarmReceiver()

        initReminder()
        initSharedPref()
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }


    private fun initSharedPref() {
        val sf = preferenceManager.sharedPreferences
        reminderPreferences.isChecked = sf.getBoolean(reminder, false)
    }

    private fun initReminder() {
        reminder = getString(R.string.reminder)
        reminderPreferences = findPreference<SwitchPreferenceCompat>(reminder) as SwitchPreferenceCompat
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == reminder) {
            if (sharedPreferences != null) {
                reminderPreferences.isChecked = sharedPreferences.getBoolean(reminder, false)
            }
        }
    }
}