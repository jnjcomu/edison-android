package com.jnjcomu.edison.encrypt

import android.content.Context
import android.content.pm.PackageManager
import android.util.Base64
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * @author kimwoojae <wj1187@naver.com>
 * @author coderi13 <ruto1924@gmail.com>
 *
 * @since 2017-04-05
 *
 * TODO 코드 완벽하게 이해하기
 * TODO String 에러 고치기
 */

class Encrypter(private val context: Context) {
    private var keyIV: String? = null

    /**
     * @return secretKey
     */
    fun fetchSignature(): SecretKey? {
        var secretKey: SecretKey? = null

        try {
            val signature = context.packageManager.getPackageInfo(
                    context.packageName, PackageManager.GET_SIGNATURES
            ).signatures[0]

            val md = MessageDigest.getInstance(DIGEST_ALGORITHM)
            md.update(signature.toByteArray())

            keyIV = signature.toCharsString().substring(0, 16)
            secretKey = SecretKeySpec(md.digest(), "AES")
        } catch (ignored: Exception) {
        }

        return secretKey
    }

    /**
     * @param plainText String
     *
     * @return encrypted
     */
    fun encrypt(plainText: String): String {
        val cipher: Cipher
        val ivParameterSpec: IvParameterSpec
        val encryptedData: ByteArray
        var encrypted: String
        val secretKey: SecretKey

        try {
            cipher = Cipher.getInstance(ENCRYPT_ALGORITHM)
            secretKey = fetchSignature()!!
            ivParameterSpec = IvParameterSpec(keyIV!!.toByteArray())

            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec)

            encryptedData = cipher.doFinal(plainText.toByteArray(charset(ENCODING)))
            encrypted = Base64.encodeToString(encryptedData, Base64.DEFAULT)
        } catch (ignored: Exception) {
            encrypted = ""
        }

        return encrypted
    }

    /**
     * @param encrypted String
     *
     * @return decrypted
     */
    fun decrypt(encrypted: String): String {
        val cipher: Cipher
        val ivParameterSpec: IvParameterSpec
        var decrypted: String
        val secretKey: SecretKey

        try {
            val data = Base64.decode(encrypted, Base64.DEFAULT)

            cipher = Cipher.getInstance(ENCRYPT_ALGORITHM)
            secretKey = fetchSignature()!!
            ivParameterSpec = IvParameterSpec(keyIV!!.toByteArray())

            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec)

            decrypted = String(cipher.doFinal(data), ENCODING)
        } catch (ignored: Exception) {
            decrypted = ""
        }

        return decrypted
    }

    companion object {
        private val DIGEST_ALGORITHM = "SHA-256"
        private val ENCRYPT_ALGORITHM = "AES/CBC/PKCS5Padding"
        private val ENCODING = "UTF-8"
    }
}