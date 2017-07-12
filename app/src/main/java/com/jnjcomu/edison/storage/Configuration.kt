package com.jnjcomu.edison.storage

import android.content.Context
import com.jnjcomu.edison.encrypt.Encrypter
import org.json.JSONObject

/**
 * Configuration Class
 *
 * 키스토어에 의해 암호화된 키를 해독하는 클레스.
 *
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-04-13
 */
class Configuration(context: Context) {
    var json: JSONObject? = null
        private set

    var loplatId: String? = null
        private set

    var loplatPw: String? = null
        private set

    var hostUrl: String? = null
        private set

    init {
        val encrypt = Encrypter(context)

        context.assets.open(CONFIGURATION_JSON_NAME).use {
            val size = it.available()
            val buffer = ByteArray(size)

            it.read(buffer)
            val readString = String(buffer)

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
        }
    }

    companion object {
        private val TAG = "Configuration"
        private val ENCODING = "UTF-8"
        private val CONFIGURATION_JSON_NAME = "Edison.json"
    }
}