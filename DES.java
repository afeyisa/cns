import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class DES{

    private  static String key;
    private static  String encryptedMessage;
    public static void setKey(String k){
        key=k;
    }

    public static String getKey(){
        return key;
    }

    public static String desEncrypt(){

        try{
            KeyGenerator kg= KeyGenerator.getInstance("DES");
            SecretKey myDESkey = kg.generateKey();
            byte[] keyBytes = myDESkey.getEncoded();
            String encodedKey = Base64.getEncoder().encodeToString(keyBytes);
            setKey(encodedKey);
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,myDESkey);
            byte[] text = Main.getMessage().getBytes();
            byte[] textincypt = cipher.doFinal(text);

            return Base64.getEncoder().encodeToString(textincypt);

        }
        catch (Exception e){
            System.out.println("some thing is wrong");
            return "";
        }
    }

    public static String desDycrypt(byte[] m,  SecretKey myDESkey) {
        try{
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE,myDESkey);
        byte[] textdecrypted = cipher.doFinal(m);
        return new String(textdecrypted);
        }
        catch (Exception e ){
            return "";
        }


    }




}
