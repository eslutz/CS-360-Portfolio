package com.cs360.inventorytracker;

import android.util.Log;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class HashPassword {
    public static byte[] generateSalt() {
        byte[] bytes = new byte[16];
        try {
            // Generate salt for hashing password
            SecureRandom random = SecureRandom.getInstanceStrong();
            random.nextBytes(bytes);
        } catch (NoSuchAlgorithmException e) {
            Log.e("Password Hashing", e.getMessage(), e);
        }
        return bytes;
    }

    public static String generateHash(String password, byte[] salt) {
        String hashedPassword = "";
        try {
            // Hash the password
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2withHmacSHA256");
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(),
                    salt,
                    210000,
                    256);
            SecretKey key = factory.generateSecret(spec);
            spec.clearPassword();
            hashedPassword = convertSecretKeyToString(key);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            Log.e("Password Hashing", e.getMessage(), e);
        }
        return hashedPassword;
    }

    private static String convertSecretKeyToString(SecretKey secretKey) throws NoSuchAlgorithmException {
        byte[] rawData = secretKey.getEncoded();
        return Base64.getEncoder().encodeToString(rawData);
    }
}
