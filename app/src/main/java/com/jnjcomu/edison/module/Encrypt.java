package com.jnjcomu.edison.module;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-04-12
 */

public class Encrypt {

    private static String IV;

    private static SecretKey getKey(Context context) {
        SecretKey key = null;

        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);

            Signature signature = info.signatures[0];
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(signature.toByteArray());

            IV = signature.toCharsString().substring(0,16);
            key = new SecretKeySpec(md.digest(), "AES");
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return key;
    }

    public static String encrypt(Context context, byte[] plain) {
        Cipher c;
        String enctypted = null;
        byte[] data;

        try {
            c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, getKey(context), new IvParameterSpec(IV.getBytes()));

            data = c.doFinal(plain);	// Value to Encrypt.

            enctypted = Base64.encodeToString(data, Base64.DEFAULT);
        } catch (InvalidKeyException | NoSuchAlgorithmException
                | NoSuchPaddingException | IllegalBlockSizeException
                | BadPaddingException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return enctypted;
    }

    public static String decrypt(Context context, String encrypted) {
        Cipher c;
        String decrypted = null;

        try {
            byte[] data = Base64.decode(encrypted, Base64.DEFAULT);

            c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, getKey(context), new IvParameterSpec(IV.getBytes()));

            decrypted = new String(c.doFinal(data), "UTF-8");

        } catch (InvalidKeyException | NoSuchAlgorithmException
                | NoSuchPaddingException | IllegalBlockSizeException
                | BadPaddingException | InvalidAlgorithmParameterException
                | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decrypted;
    }

}
