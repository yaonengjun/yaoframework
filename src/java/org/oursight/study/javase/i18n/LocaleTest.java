package org.oursight.study.javase.i18n;

import java.util.Locale;

import org.apache.commons.lang.LocaleUtils;

import net.sf.cglib.core.Local;

public class LocaleTest {
	
	public static void printAllLocales() {
		
		Locale[] locales = Locale.getAvailableLocales();
		for (int i = 0; i < locales.length; i++) {
			Locale locale = locales[i];
			System.out.println("chn.toString(): " + locale.toString());
			System.out.println("chn: " + locale);
			System.out.println("chn.getDisplayLanguage(): " + locale.getDisplayLanguage());
			System.out.println("chn.getCountry(): " + locale.getCountry());
			System.out.println("chn.getDisplayCountry(): " + locale.getDisplayCountry());
			System.out.println("(chn.getDisplayName()  " + locale.getDisplayName());
			System.out.println("chn.getDisplayName(chn): " + locale.getDisplayName(locale));
			
			System.out.println();
			System.out.println();
			System.out.println("-------");
		}
	}
	
	public static void printLocale() {
		java.util.Locale chn = java.util.Locale.SIMPLIFIED_CHINESE;
		System.out.println("chn.toString(): " + chn.toString());
		System.out.println("chn: " + chn);
		System.out.println("chn.getDisplayLanguage(): " + chn.getDisplayLanguage());
		System.out.println("chn.getCountry(): " + chn.getCountry());
		System.out.println("chn.getDisplayCountry(): " + chn.getDisplayCountry());
		System.out.println("(chn.getDisplayName()  " + chn.getDisplayName());
		System.out.println("chn.getDisplayName(chn): " + chn.getDisplayName(chn));
		
		System.out.println();
		System.out.println();
		System.out.println("-------");
		
		
		chn = java.util.Locale.TRADITIONAL_CHINESE;
		System.out.println("chn.toString(): " + chn.toString());
		System.out.println("chn: " + chn);
		System.out.println("chn.getDisplayLanguage(): " + chn.getDisplayLanguage());
		System.out.println("chn.getDisplayCountry(): " + chn.getDisplayCountry());
		System.out.println("(chn.getDisplayName()" + chn.getDisplayName());
		System.out.println("chn.getDisplayName(chn): " + chn.getDisplayName(chn));
		
		System.out.println();
		System.out.println();
		System.out.println("-------");
		
		chn = java.util.Locale.CANADA_FRENCH;
		System.out.println("chn.toString(): " + chn.toString());
		System.out.println("chn: " + chn);
		System.out.println("chn.getDisplayLanguage(): " + chn.getDisplayLanguage());
		System.out.println("chn.getDisplayCountry(): " + chn.getDisplayCountry());
		System.out.println("(chn.getDisplayName()" + chn.getDisplayName());
		System.out.println("chn.getDisplayName(chn): " + chn.getDisplayName(chn));
		
		System.out.println();
		System.out.println();
		System.out.println("-------");
		
		chn = java.util.Locale.FRENCH;
		System.out.println("chn.toString(): " + chn.toString());
		System.out.println("chn: " + chn);
		System.out.println("chn.getDisplayLanguage(): " + chn.getDisplayLanguage());
		System.out.println("chn.getDisplayCountry(): " + chn.getDisplayCountry());
		System.out.println("(chn.getDisplayName()" + chn.getDisplayName());
		System.out.println("chn.getDisplayName(chn): " + chn.getDisplayName(chn));
	}
	
	public static void createLocale() {
		Locale chn = new Locale("zh_CN");
		System.out.println("chn.toString(): " + chn.toString());
		System.out.println("chn: " + chn);
		System.out.println("chn.getDisplayLanguage(): " + chn.getDisplayLanguage());
		System.out.println("chn.getCountry(): " + chn.getCountry());
		System.out.println("chn.getDisplayCountry(): " + chn.getDisplayCountry());
		System.out.println("(chn.getDisplayName(): " + chn.getDisplayName());
		System.out.println("chn.getDisplayName(chn): " + chn.getDisplayName(chn));

		System.out.println();
		System.out.println();
		System.out.println("-------");
		
		chn = LocaleUtils.toLocale("zh_CN");
		System.out.println("chn.toString(): " + chn.toString());
		System.out.println("chn: " + chn);
		System.out.println("chn.getDisplayLanguage(): " + chn.getDisplayLanguage());
		System.out.println("chn.getCountry(): " + chn.getCountry());
		System.out.println("chn.getDisplayCountry(): " + chn.getDisplayCountry());
		System.out.println("(chn.getDisplayName(): " + chn.getDisplayName());
		System.out.println("chn.getDisplayName(chn): " + chn.getDisplayName(chn));
		System.out.println("chn.getDisplayName(chn): " + chn.getDisplayName(Locale.ENGLISH));
		
	}
	
	public static void main(String[] args) {
		
		//printLocale();
		
//		createLocale();
		
		printAllLocales();
	}

}
