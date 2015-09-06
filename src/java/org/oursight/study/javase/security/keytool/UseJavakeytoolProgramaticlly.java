package org.oursight.study.javase.security.keytool;

import java.io.FileOutputStream;
import java.security.KeyStore;

public class UseJavakeytoolProgramaticlly {
	
	/**
	 * 创建一个KeyStore，生成在项目的根目录中
	 * @throws Exception
	 * @author neyao,2015年9月6日 下午4:49:26
	 */
	public static void createKeyStore() throws Exception {
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());

		char[] password = "111111".toCharArray();
		ks.load(null, password);

		// Store away the keystore.
		FileOutputStream fos = new FileOutputStream("MyTestKeyStoreFile.jks");
		ks.store(fos, password);
		fos.close();
		
		System.out.println("Create a KeyStore success");
	}
	
	public static void main(String[] args) {
		try {
			createKeyStore();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
