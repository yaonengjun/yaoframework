package org.oursight.study.opensource.jasypt;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.PasswordEncryptor;

public class JasyptUsageSample {

	public static void main(String[] args) {
		PasswordEncryptor encrypt = new BasicPasswordEncryptor();
		String enPass =  encrypt.encryptPassword("111111");
		System.out.println(enPass);
		
		
	}
}
