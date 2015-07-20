package org.oursight.framework.yao.crypto;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.trs.dev4.jdk16.utils.Base64Util;

public class AES {
	
    /**解密
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static byte[] decrypt(byte[] content, String password) {
            try {
                     KeyGenerator kgen = KeyGenerator.getInstance("AES");
                     kgen.init(128, new SecureRandom(password.getBytes()));
                     SecretKey secretKey = kgen.generateKey();
                     byte[] enCodeFormat = secretKey.getEncoded();
                     SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");            
                     Cipher cipher = Cipher.getInstance("AES");// 创建密码器
                    cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
                    byte[] result = cipher.doFinal(content);
                    return result; // 加密
            } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
            } catch (InvalidKeyException e) {
                    e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
            } catch (BadPaddingException e) {
                    e.printStackTrace();
            }
            return null;
    }
    
    public static void main(String[] args) {
		String encryptedText = "3VQizHVSmOfCMxaWVD8U-r4nUVIOshaOJUPBOMqa6wkEH-QfpldYlVrGYuCiiwC2BfZ4nyF0GYIKcwn2OkPuX0no5uCoLaS2-9YRegqlTvtfN5zeUVWCOLTe19jM9QjP-WJhbTHu0jz0xJu-cc4GHP1KiG72nwvhsvEbvvVJ1PxQMwo1KVfVMTgVB1LbEmQAUQviqChznxA0357zI3tqTfBH3hXP4mYmldQ6dlbwx8GbCTWv4XoQ4UT9X7THSxAAELqgy_Z8flFSPcZfugqkXUFGok6tgPLHOUdKPXhTz_-OwdVlyavefooTBwHziG6GVkTo7uUNwxA8okBYkwctxdBpAZt_GmbuNjXlPO0v2zt16kfW39VB5oSi-TQPfcGXwVol8wPioqMV5mNILsxtpJgOqPIPsNnvZGcK6Y4JyNdHACnRXxvYsBHLxW0bOEjqmAoNvb9aI3il84ISOjrzxz1fF3XuJit4cnw9Ie0L3KWgrXWRpxqwskrdVq6lHop-yuBhac2qMcpt2S3unrl9EP5zga-bRsE-CjJxJT8T90joTuVXO7NYpV07TgpH5jIF1nMjezz1SP95Jj6MYfFZtoh9AG2936O6C0oOxAnTAutEWT7WkPbZU6bXxgVoGC-AtkmIJViIzCRqQD-zmmHNCGXQ2xkIRhDo_HZEGioGuKuEYzbB_oKO4b1VE8J9g1qI-ObFUQBfEmxqPSaPBjjUg7SM3fsxqS_HWrecQ_ZhrAxXwX-1agW6Sb_MnS8gcmxbG4bOJX4oLc5T8xIl9LuOcsb1eBV9mWZft_kjw6s6tFoxo4fUorwGqA_p0SlRFIzeE_x06ou9kHMEU81INsJ9j3sU2e-CqxzDmrYiimpVINOsu2d__6wcSzIk0Utk-tEREi6EcpyQKnBF41Mg8WNpR-Xr8yjkBXmfIpYkCRgmBd98m-3TeFYuYkzUShEG5RUvdzgd9W_T3DdJxpQo1qNmQLHSfKcXAvyggHCMNpstKx6WMHLKyS_yx1TQoh7WBXIX8Ucxcs513ae6RebLLG6g0mmH1G-wZ9tHlrLK6miwr6muOKJuakWgXPEijnsGg5BSBoSaKjbeWQA1PAj1VjdDyl0aPCIgU4TVc4UhxtTWyWkz7xe0qZNR0UGUZa4ZvtuCk4TKSKpveaIxN7evIiYxGnkUnHSHttgbCeWrQ1aJd9abFUbpmiY4R9Cw7ufDIcvrpJ0c7E3WwT3-tO7s1Bi7mZ0h0kP-UztC7Cj1sNhk3qOubmNreepeQ3w2xP1YpJV_BBr8RDDY5DdHz0DQiYrVxb1EpCllJcs_mgxC22347Vsjxg8Yya1WHs9g04deJlGXv7_pThIEGHr-k-4OdjuHewor8BJIWexg8YeH1oIaqI6EoTD-RpzcJk7c00Z4vfFOSOCK4yHOC3ZGcRMPi1DWtot0goy9lCnBm9iz-waOLzEqmrukxIM0ywTIimEt6sSn-GxxVzZdpLbL2fgJJOkP7LVUdaCnaVgrKs12yBISn2eyngvSKZHiLVctCmXSRGbLXv0Dq8GwUbt4vy6Oju3zm8IGM_EhjN65lSdgPV4tcJUV0819dJniW4L5rMfZ0LklARiHa-Px7PcPwTsZyCn_0vP1ZsorW9ykPvTUzFA6orrD-K4whiWB9SclFQ7sngHJVmXqgAkzbRe53bDkh2dZ8Gims3tIi2GMqdt-tXwSPqomUBY5ibbhD_lhnc1Kj36wupJ0D7VxNzUu-wii-sPahQB0v_v9AFTqbe546-69Se-nInS83lDnvMPx7yDWMTioHkK7GsWCvSP57giC1nLKP7z2AMzzYPnbBiNf2MyaN_4aFvkPdhv2E6i8VNmpulAWEjFR2W90CqgLDXYoVAQdq8qSlvv4vobYeKKVjj4HwvG3tJladrREg117WJ2d1Rnt_dAmq56WK_91OZW2Uw1ddY6BZiJy6rtssqbDwt3BIpgJrcFcuyIA6J20Va950kNUcJeH2pIf5-0xBVvmwfIlZciaem11PulXj944t7nZlmBxRQdttkRNDVB5R2InB5O7XfXyVwUnsQGHoP9t3PzUsitWMGquj4OX4X1uiVbGFxcMhw-eFkiNBlk72HzUHkzKVmQp6E-WYJ11rYp7jdHwpRobLn9xu2t1RSU4aE2gE3NGZa0RegADXZn7z6QAq0_qVFBZnx0AySim98k2cmdpdfyZlciwgIuPEX1Sx5E5Hph4M0-ZJV9MshIZsIreWoaR65Y1If-fassLm2HtAXE7nLXJGakIuqzz-C_89SKkpefsxZq_AO9UyNQuvJLmXbonyGFT4y0DNRnL5kz6vjZPcbAkb8zLPon9ZBI71kYNBB1pUe_LgS9DxZ9QTHD0WaothDiwovIIujfcyjzPzD8LlT8armikU9GPknHGI6oPT_Kp66YG34Hsn9jo5P5dmQMSBI5iCgaoLzAyBmsuaIz_u99bGWxYVePvAyS120bNPPJdGs6vfAwgmF-tv1cV0mU4MDvX8kctMCUpCqFuCEmbioRQFecnz62tQjjFDyncJ0XLRNxSw7G62e7ki0GZllEIWB7fBdffZJgG4a3FNrZVmdByFf77Wgg6RyTk_c7E6DLPCWCl_gjKrDrkuvE-Mzlacf8VgGQ2LgBKByZUSoazzE6wgwylwF3NK17v57riw2z6XkcJSv2xcv0IRyXVO9CX9WmEvqiXhJ3nXrSSxWLroTibI3RU4bs0DychNIaoPZi_N9MrOJnqjKdKO8RR2r0N2c6Q9oFQ9Xvpvmv6WTVk6m9x3znEQF98BsA43R0dpSHPpTBRCGx_rsMMDGQY_Jz1klW87FYtcuvPHXQq_IUYuhEscoetj8IqMSQcENbz-xU";
		byte[] decryptedTextBytes = decrypt(Base64Util.decodeBytes(encryptedText),"1");
		System.out.println(decryptedTextBytes);
		
		String decryptedText = new String(decryptedTextBytes);
		System.out.println(decryptedText);
		
	}

}
