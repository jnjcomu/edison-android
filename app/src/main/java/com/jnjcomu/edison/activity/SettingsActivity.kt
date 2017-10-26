package com.jnjcomu.edison.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.SwitchPreference
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.jnjcomu.edison.R
import com.jnjcomu.edison.storage.AppSettingStorage
import com.jnjcomu.edison.storage.CookieStorage

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

            val switch = findPreference("autoscan") as SwitchPreference
            switch.isChecked = appStorage!!.isActiveScanning

            val profile = findPreference("profile")
            val name = appStorage!!.userName
            profile.summary = "현재 사용자 : " + name

            val clicks = arrayOf("profile", "terms", "location", "privacy", "openSource", "tip", "appVersion", "dev")

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
                    CookieStorage(activity).clear()
                    appStorage!!.clear()
                    val intent = Intent(activity, LoginActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                "terms" -> {
                    val intent = Intent(activity, WebviewActivity::class.java)
                    intent.putExtra("title", "서비스 이용약관")
                    intent.putExtra("doc", "file:///android_asset/terms.html")
                    startActivity(intent)
                }
                "location" -> {
                    Toast.makeText(activity, "준비중입니다.", Toast.LENGTH_SHORT).show()
                }
                "privacy" -> {
                    val intent = Intent(activity, WebviewActivity::class.java)
                    intent.putExtra("title", "개인정보처리방침")
                    intent.putExtra("doc", "file:///android_asset/terms_privacy.html")
                    startActivity(intent)
                }
                "openSource" -> {
                    val intent = Intent(activity, WebviewActivity::class.java)
                    intent.putExtra("title", "오픈소스 정보")
                    intent.putExtra("doc", "file:///android_asset/license.html")
                    startActivity(intent)
                }
                "tip" -> {
                    startActivity(Intent(activity, IntroActivity::class.java))
                }
                "appVersion" -> {
                    val update = Intent(Intent.ACTION_VIEW)
                    update.data = Uri.parse("https://play.google.com/store/apps/details?id=com.jnjcomu.edison")
                    startActivity(update)
                }
                "dev" -> {
                    Toast.makeText(activity, "우재 기요미", Toast.LENGTH_SHORT).show()
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