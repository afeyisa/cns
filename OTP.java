import java.security.SecureRandom;
import java.util.Base64;

public class OTP{
    private static String key;

    public static void setKey(String k){

        key=k;
    }

    public static String getKey(){

        return key;
    }

    // Generate a random key of specified length
    public  static byte[] generateKey(int length) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[length];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        setKey(encodedKey);
        return key;
    }

    // Encrypt plaintext with the given key
    public static byte[] encrypt(byte[] plaintext, byte[] key) {
        if (plaintext.length != key.length) {
            throw new IllegalArgumentException("Plaintext and key must be of equal length");
        }
        byte[] ciphertext = new byte[plaintext.length];
        for (int i = 0; i < plaintext.length; i++) {
            ciphertext[i] = (byte) (plaintext[i] ^ key[i]);
        }
        return ciphertext;
    }

    // Decrypt ciphertext with the given key
    public static byte[] decrypt(byte[] ciphertext, byte[] key) {
        return encrypt(ciphertext, key); //reEncryption to decrypt
    }
}
