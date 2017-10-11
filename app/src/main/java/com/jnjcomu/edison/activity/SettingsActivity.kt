package com.jnjcomu.edison.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.support.v7.app.AppCompatActivity
import com.jnjcomu.edison.R
import com.jnjcomu.edison.storage.AppSettingStorage

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-10-04
 */

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        fragmentManager.beginTransaction().replace(R.id.container, PrefsFragment()).commit()
    }

    class PrefsFragment : PreferenceFragment() {

        private var appStorage: AppSettingStorage? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.settings)

            appStorage = AppSettingStorage(activity)

            val clicks = arrayOf("profile", "terms", "location", "privacy", "openSource", "tip", "appVersion")

            for(p in clicks)
                setOnPreferenceClick(findPreference(p))

            val changes = arrayOf("autoscan")

            for(c in changes)
                setOnPreferenceChange(findPreference(c))

            try {
                val packageManager = activity.packageManager
                val info = packageManager.getPackageInfo(activity.packageName, PackageManager.GET_META_DATA)
                findPreference("appVersion").summary = info.versionName
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

        }

        private fun setOnPreferenceClick(mPreference: Preference) {
            mPreference.onPreferenceClickListener = onPreferenceClickListener
        }

        private fun setOnPreferenceChange(mPreference: Preference) {
            mPreference.onPreferenceChangeListener = onPreferenceChangeListener
        }

        private val onPreferenceClickListener = Preference.OnPreferenceClickListener { preference ->
            val getKey = preference.key
            when (getKey) {
                "profile" -> {
                }
                "terms" -> {
                }
                "location" -> {
                }
                "privacy" -> {
                }
                "openSource" -> {
                }
                "tip" -> {
                }
                "appVersion" -> {
                    val update = Intent(Intent.ACTION_VIEW)
                    update.data = Uri.parse("https://play.google.com/store/apps/details?id=com.jnjcomu.edison")
                    startActivity(update)
                }
            }
            true
        }

        private val onPreferenceChangeListener = Preference.OnPreferenceChangeListener { preference, newValue ->
            val getKey = preference.key
            when (getKey) {
                "autoscan" -> {
                    appStorage!!.saveActive(newValue as Boolean)
                }
            }
            true
        }

    }

}