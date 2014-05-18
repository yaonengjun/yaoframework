package org.oursight.study.javase.serializable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serialization {
	
	public static void main(String[] args) throws IOException {
		// 构造序列化类
		SerializableClass clazz = new SerializableClass();
		clazz.setName("MyFisrtSeriaClass");
		clazz.setUsage("Just for test!");
		System.out.println("Before Serialization: ");
		System.out.println(clazz);
		
		// 获得临时文件保存的位置
		String curDir = System.getProperty("user.dir");
		File file = new File(curDir +  "\\src\\java\\org\\oursight\\study\\javase\\serializable\\serialization.result.out");
		// 执行序列化
		FileOutputStream fos = new FileOutputStream(file);  
		ObjectOutputStream oos = new ObjectOutputStream(fos);  
		oos.writeObject(clazz);  
		oos.flush();  
		oos.close();  
		
	}

}
