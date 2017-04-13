package com.jnjcomu.edison.storage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-04-05
 */

public class Encrypt {
    private static final String DIGEST_ALGORITHM = "SHA-256";
    private static final String ENCRYPT_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String ENCODING = "UTF-8";

    private Context context;
    private String keyIV;

    public Encrypt(Context context) {
        this.context = context;
    }

    /**
     *
     * @return
     */
    private SecretKey fetchSignature() {
        SecretKey secretKey = null;

        try {
            Signature signature = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES
            ).signatures[0];

            MessageDigest md = MessageDigest.getInstance(DIGEST_ALGORITHM);
            md.update(signature.toByteArray());

            keyIV = signature.toCharsString().substring(0, 16);
            secretKey = new SecretKeySpec(md.digest(), "AES");
        } catch (Exception ignored) {
        }

        return secretKey;
    }

    /**
     *
     * @param plainText
     * @return
     */
    public String encrypt(String plainText) {
        Cipher cipher;
        IvParameterSpec ivParameterSpec;
        byte[] encryptedData;

        String encrypted;

        try {
            cipher = Cipher.getInstance(ENCRYPT_ALGORITHM);
            ivParameterSpec = new IvParameterSpec(keyIV.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, fetchSignature(), ivParameterSpec);

            encryptedData = cipher.doFinal(plainText.getBytes(ENCODING));
            encrypted = Base64.encodeToString(encryptedData, Base64.DEFAULT);
        } catch (Exception ignored) {
            return "";
        }

        return encrypted;
    }

    /**
     *
     * @param encrypted
     * @return
     */
    public String decrypt(String encrypted) {
        Cipher cipher;
        IvParameterSpec ivParameterSpec;
        String decrypted;

        try {
            byte[] data = Base64.decode(encrypted, Base64.DEFAULT);

            cipher = Cipher.getInstance(ENCRYPT_ALGORITHM);
            ivParameterSpec = new IvParameterSpec(keyIV.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, fetchSignature(), ivParameterSpec);

            decrypted = new String(cipher.doFinal(data), ENCODING);
        } catch (Exception ignored) {
            return "";
        }

        return decrypted;
    }
}