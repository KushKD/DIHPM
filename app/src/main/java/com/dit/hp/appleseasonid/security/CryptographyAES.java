package com.dit.hp.appleseasonid.security;





import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import org.apache.commons.codec.binary.Base64;
//import java.util.Base64;


public class CryptographyAES {

    public CryptographyAES() {
    }

    public String decryptFile(String s, String secretKey) {
        String decdata = null;
        byte key[] = (byte[]) null;
        key = secretKey.getBytes();
        try {
          //  Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte keyBytes[] = new byte[16];
            int len = key.length;
            if (len > keyBytes.length)
                len = keyBytes.length;
            System.arraycopy(key, 0, keyBytes, 0, len);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
            cipher.init(2, keySpec, ivSpec);
            //BASE64Decoder decoder = new BASE64Decoder();
          //  byte results[] = Base64.getDecoder().decode(s);//decoder.decodeBuffer(s);
            byte results[] = Base64.decodeBase64(s.getBytes());
            byte cipherText[] = cipher.doFinal(results);
            decdata = new String(cipherText, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return decdata;
    }

    public String encryptFile(String s, String secretKey) {
        byte key[] = (byte[]) null;
        key = secretKey.getBytes();
        String encData = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
            byte keyBytes[] = new byte[16];
            int len = key.length;
            if (len > keyBytes.length)
                len = keyBytes.length;
            System.arraycopy(key, 0, keyBytes, 0, len);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
            cipher.init(1, keySpec, ivSpec);
            byte results[] = cipher.doFinal(s.getBytes("UTF-8"));
           // BASE64Encoder encoder = new BASE64Encoder();
           // encData = Base64.getEncoder().encodeToString(results)  ; //encoder.encode(results);
            encData = Base64.encodeBase64(results).toString();
        } catch (Exception ex) {
        }
        return encData;
    }

//    static {
//        Security.addProvider(new com.sun.crypto.provider.SunJCE());
//        Security.insertProviderAt(new com.sun.crypto.provider.SunJCE(), 1);
//    }

}
