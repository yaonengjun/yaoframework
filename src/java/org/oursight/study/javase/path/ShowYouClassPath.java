package org.oursight.study.javase.path;

public class ShowYouClassPath {
	
	public static void main(String[] args) {
		System.out.println(ShowYouClassPath.class.getResource("").toString());
		System.out.println(ShowYouClassPath.class.getResource("").getProtocol());
		System.out.println(ShowYouClassPath.class.getResource("").getPath());
		
		System.out.println();
		
		System.out.println(ShowYouClassPath.class.getResource("/"));
	}

}
