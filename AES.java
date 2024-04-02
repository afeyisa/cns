import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.util.Base64;

public class AES {

    private static int key_size = 128;
    private static int tag_lan =128;
    private static Cipher cipherencripter;
    private static String key ;




    public static  void setKey(String k){
        key=k;
    }

    public static String getKey(){
        return key;
    }

    public static String aesEncrypt(){
        try {

            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(key_size);
            SecretKey myAESkey = kg.generateKey();
            byte[] keyBytes = myAESkey.getEncoded();
            String encodedKey = Base64.getEncoder().encodeToString(keyBytes);
            setKey(encodedKey);

            byte[] text = Main.getMessage().getBytes();

            Cipher cipher = Cipher.getInstance("AES/GCM/noPadding");
            cipherencripter=cipher;
            cipher.init(Cipher.ENCRYPT_MODE, myAESkey);
            byte[] textEncrypted = cipher.doFinal(text);
            return Base64.getEncoder().encodeToString(textEncrypted);
        }
        catch (Exception e){
            return "";
        }
    }
    public static String aesdecryption(byte[] textincypted,SecretKey myAESkey){
        try{

            Cipher cipher = Cipher.getInstance("AES/GCM/noPadding");

            GCMParameterSpec spec= new GCMParameterSpec(tag_lan,cipherencripter.getIV());
            cipher.init(Cipher.DECRYPT_MODE,myAESkey,spec);
            byte[] textdecrpted = cipher.doFinal(textincypted);
            String d= new String(textdecrpted);
            System.out.println(d);
            return new String(textdecrpted);
        }
        catch (Exception e){
            System.out.println("some thing wrong"+e);
            return "";
        }
    }
}
