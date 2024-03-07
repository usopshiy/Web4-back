package usopshiy.web4.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    public static String SHA(String password){
        byte[] data = password.getBytes(StandardCharsets.UTF_8);
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            return new String(sha256.digest(data));
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
