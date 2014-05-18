package org.oursight.study.javase.gui.font;

import java.awt.GraphicsEnvironment;

public class ListAllFont {
	
	public static void main(String[] args) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    String fontNames[] = ge.getAvailableFontFamilyNames();

	    for (int i = 0; i < fontNames.length; i++) {
	      System.out.println(fontNames[i]);
	    }
	}

}
