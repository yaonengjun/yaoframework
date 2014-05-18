package org.oursight.study.javase.classloader;

public class ShowYourClassLoader {

	public static void main(String[] args) {
		Class c;
		ClassLoader cl;

		cl = ClassLoader.getSystemClassLoader();
		System.out.println("System classLoader is: " + cl);
		while (cl != null) {
			cl = cl.getParent();
			System.out.println("System classLoader's parent is: " + cl);
		}
		System.out.println();
		
		 try {
			c = Class.forName("java.lang.Object");
			cl = c.getClassLoader();
			System.out.println("java.lang.Object's classLoader is: " + cl);
			while (cl != null) {
				cl = cl.getParent();
				System.out.println("java.lang.Object classLoader's parent is: " + cl);
			}
			System.out.println();
			
			
			c = Class.forName("org.oursight.study.javase.classloader.ShowYourClassLoader");
			cl = c.getClassLoader();
			System.out.println("ShowYourClassLoader classLoader is: " + cl);
			while (cl != null) {
				cl = cl.getParent();
				System.out.println("ShowYourClassLoader classLoader's parent is: " + cl);
			}
			System.out.println();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		 
		 
		

	}
	
	
	
	
}
