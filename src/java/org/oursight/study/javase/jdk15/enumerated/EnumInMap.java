package org.oursight.study.javase.jdk15.enumerated;

import java.util.EnumMap;

public class EnumInMap {
	
	public EnumMap<EnumInClass.ComunicationType, String> comuTypes = new EnumMap<EnumInClass.ComunicationType, String>(EnumInClass.ComunicationType.class);
	
	public EnumInMap() {
		comuTypes.put(EnumInClass.ComunicationType.HTTP, "Http comunication ");
		comuTypes.put(EnumInClass.ComunicationType.SOCKET, "Socket comunication ");
	}

	/**
	 * @param args
	 * @author yaonengjun,2011-3-22 下午08:15:10
	 */
	public static void main(String[] args) {
		EnumInMap enumMap = new EnumInMap();
		// 打印Map里的所有值
		System.out.println(enumMap.comuTypes.values());
		// 打印Map里一个不存在的key的value
		System.out.println(enumMap.comuTypes.get("HTTP"));
		// 打印Key为枚举的Value
		System.out.println(enumMap.comuTypes.get(EnumInClass.ComunicationType.HTTP));
		
	}

}
