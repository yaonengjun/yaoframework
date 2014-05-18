package org.oursight.study.javase.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Deserialization {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// 获得临时文件保存的位置
		String curDir = System.getProperty("user.dir");
		File file = new File(
				curDir
						+ "\\src\\java\\org\\oursight\\study\\javase\\serializable\\serialization.result.out");

		// 
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream oin = new ObjectInputStream(fis);
		SerializableClass clazz = (SerializableClass) oin.readObject();

		System.out.println("After Deserialization: ");
		System.out.println(clazz);

	}

}
