package com.jnjcomu.edison.storage

import android.content.Context
import android.util.Log

import com.jnjcomu.edison.encrypt.Encrypter

import org.json.JSONObject

import java.io.InputStream

/**
 * Configuration Class
 *
 * 키스토어에 의해 암호화된 키를 해독하는 클레스.
 *
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-04-13
 */
class Configuration(context: Context) {
    private var json: JSONObject? = null
    private var loplatId: String? = null
    private var loplatPw: String? = null
    private var hostUrl: String? = null

    init {
        var inputStream: InputStream? = null
        val encrypt = Encrypter(context)

        try {
            // read edison.json file
            inputStream = context.assets.open(CONFIGURATION_JSON_NAME)

            val size = inputStream!!.available()
            val buffer = ByteArray(size)

            inputStream.read(buffer)
            val readString = String(buffer, ENCODING)

            // get json object
            json = JSONObject(readString)

            // get decrypted key
            val loplatJson = json!!.getJSONObject("loplat")

            val encryptHostUrl = json!!.getString("host_url")
            val encryptLoplatId = loplatJson.getString("id")
            val encryptLoplatPw = loplatJson.getString("pw")

            hostUrl = encrypt.decrypt(encryptHostUrl)
            loplatId = encrypt.decrypt(encryptLoplatId)
            loplatPw = encrypt.decrypt(encryptLoplatPw)
        } catch (ignored: Exception) {
            Log.d(TAG, "Cant read json from assets")
        } finally {
            try {
                inputStream!!.close()
            } catch (ignored: Exception) {
                Log.d(TAG, "Fail to stop work")
            }

        }
    }

    companion object {
        private val TAG = "Configuration"
        private val ENCODING = "UTF-8"
        private val CONFIGURATION_JSON_NAME = "Edison.json"
    }
}

val Context.configurator: Configuration
    get() = Configuration(this)
