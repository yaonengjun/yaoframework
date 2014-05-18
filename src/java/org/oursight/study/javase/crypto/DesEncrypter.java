package org.oursight.study.javase.crypto;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.trs.idm.util.Base64Util;

public class DesEncrypter {
	
	public static void main(String[] args) {
		try {
			// 1、使用随机生成的key
			
			// Generate a temporary key. In practice, you would save this key.
		    // See also <a href="/egs/javax.crypto/PassKey.html">Encrypting with DES Using a Pass Phrase</a>.
		    SecretKey key1 = KeyGenerator.getInstance("DES").generateKey();
		    // Create encrypter/decrypter class
		    DesEncrypter encrypter1 = new DesEncrypter(key1);
		    // Encrypt
		    String encrypted1 = encrypter1.encrypt("111111");
		    // Decrypt
		    String decrypted1 = encrypter1.decrypt(encrypted1);
		    
		    System.out.println("SecretKey1.getAlgorithm: " + key1.getAlgorithm());
		    System.out.println("SecretKey1.getFormat: " + key1.getFormat());
		    System.out.println("SecretKey1.toString: " + key1.toString());
		    System.out.println("encrypted1: " + encrypted1);
		    System.out.println("decrypted1: " + decrypted1);
		    System.out.println("Can decrypt root in IDS: " + encrypter1.decrypt("YHFFVyfm4Jk="));
		    
		    System.out.println();
		    
		    // 2、使用随机生成的key 
		    SecretKey key2 = KeyGenerator.getInstance("DES").generateKey();
		    DesEncrypter encrypter2 = new DesEncrypter(key2);
		    String decrypted2 = encrypter2.decrypt(encrypted1);
		    System.out.println("decrypted2: " + decrypted2);
		    System.out.println("Can decrypt root in IDS: " + encrypter2.decrypt("YHFFVyfm4Jk="));
		    
		    System.out.println();
		    
		    // 3、使用指定的字符串生成key
		    DESKeySpec dks = new DESKeySpec( Base64Util.decodeBytes("T3+GJY9SI8Q=") );
		    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance( "DES" );
			SecretKey key3 = keyFactory.generateSecret( dks ); 	
			DesEncrypter encrypter3 = new DesEncrypter(key3);
			System.out.println("Can decrypt root in IDS: " + encrypter3.decrypt("YHFFVyfm4Jk="));
		    
		} catch (Exception e) {
		}

	}
	
    Cipher ecipher;
    Cipher dcipher;

    DesEncrypter(SecretKey key) {
        try {
            ecipher = Cipher.getInstance("DES");
            dcipher = Cipher.getInstance("DES");
            ecipher.init(Cipher.ENCRYPT_MODE, key);
            dcipher.init(Cipher.DECRYPT_MODE, key);

        } catch (javax.crypto.NoSuchPaddingException e) {
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch (java.security.InvalidKeyException e) {
        }
    }

    public String encrypt(String str) {
        try {
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("UTF8");

            // Encrypt
            byte[] enc = ecipher.doFinal(utf8);

            // Encode bytes to base64 to get a string
            return new sun.misc.BASE64Encoder().encode(enc);
        } catch (javax.crypto.BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (java.io.IOException e) {
        }
        return null;
    }

    public String decrypt(String str) {
        try {
            // Decode base64 to get bytes
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

            // Decrypt
            byte[] utf8 = dcipher.doFinal(dec);

            // Decode using utf-8
            return new String(utf8, "UTF8");
        } catch (javax.crypto.BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (java.io.IOException e) {
        }
        return null;
    }


}
