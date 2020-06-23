package in.birdvision.equibiz.userInfo.encryption;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {

    private static byte[] KEY = "123456$#@$^@1ERF".getBytes();
    private static SecretKey ENCRYPT_DECRYPT_KEY = new SecretKeySpec(KEY, "AES");


    public static byte[] encrypt(byte[] plaintext) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            SecretKeySpec keySpec = new SecretKeySpec(ENCRYPT_DECRYPT_KEY.getEncoded(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(KEY);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            return cipher.doFinal(plaintext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encoderFunction(byte[] value) {
        return Base64.encodeToString(value, Base64.DEFAULT);
    }

    public static String decrypt(byte[] cipherText) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            SecretKeySpec keySpec = new SecretKeySpec(ENCRYPT_DECRYPT_KEY.getEncoded(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(KEY);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] decryptedText = cipher.doFinal(cipherText);
            return new String(decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] decoderFunction(String enval) {
        return Base64.decode(enval, Base64.DEFAULT);

    }

}
