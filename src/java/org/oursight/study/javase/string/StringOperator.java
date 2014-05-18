package org.oursight.study.javase.string;

public class StringOperator {
	
	public static void main(String[] args) {
//		System.out.println(insert("abcde", "fg", 3));
		test();
		
	}
	
	public static String insert(String originalString, String toInsertString, int position) {
		if(originalString == null || "".equals(originalString)) {
			System.out.println("originalString is empty, so stop insert and return null");
			return null;
		}
		
		if(toInsertString == null || "".equals(toInsertString)) {
			System.out.println("toInsertString is empty, so stop insert and return originalString");
			return originalString;
		}
		
		if(position <= 0) {
			System.out.println("toInsert position is equals or less than 0, so return originalString directly");
			return toInsertString + originalString;
		}
		
		if(position >= originalString.length()) {
			return originalString + toInsertString;
		}
		
		byte [] originalStringBytes =  originalString.getBytes();
		byte [] toInSertStringBytes = toInsertString.getBytes();
		
		byte [] toReturnBytes = new byte[originalStringBytes.length + toInSertStringBytes.length];

		for (int i = 0; i < position; i++) {
			toReturnBytes[i] = originalStringBytes[i];
		}
		
		for (int j = 0; j < (toInsertString.length()) ; j++) {
			if(toReturnBytes[j + position] != 0)
				continue;
			toReturnBytes[j + position] = toInSertStringBytes[j];
		}
		
//		for (int m = 0; m < toReturnBytes.length; m++) {
//			byte [] ca = new byte[1];
//			ca[0] = toReturnBytes[m];
//			System.out.print(m + ": " + new String(ca) +";");
//		}
//		System.out.println();
		
		for (int k = position ; k < originalString.length(); k++) {
			toReturnBytes[k + toInsertString.length()] = originalStringBytes[k];
		}
		
		for (int m = 0; m < toReturnBytes.length; m++) {
			byte [] ca = new byte[1];
			ca[0] = toReturnBytes[m];
			System.out.print(m + ":" + new String(ca) +"; ");
		}
		System.out.println();
		
		return new String(toReturnBytes);
	}
	
	public static void test() {
		System.out.println("MyspaceC".startsWith("MYSPACE"));
	}

}
