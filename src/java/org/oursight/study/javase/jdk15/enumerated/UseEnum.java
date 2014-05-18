package org.oursight.study.javase.jdk15.enumerated;

public class UseEnum {

	public static void main(String[] args) {

		// ======整个Class就是一个枚举======
		// 遍历
		System.out.println();
		System.out.println("-------------" + " Enum as Class " + "-------------");
		
		for (EnumAsClass enumValue : EnumAsClass.values()) {
			System.out.println("enumValue: " + enumValue);
		}
		// 显示枚举的说明
		System.out.println(EnumAsClass.BUSY.getDescription());
		System.out.println(EnumAsClass.AABBCC.getDescription());

		System.out.println("-------------" + " Enum as Class " + "-------------");
		System.out.println();

		// ======== 枚举作为Class的一个普通成员 ==========
		System.out.println();
		System.out.println("-------------" + " Enum as Class, as simple type " + "-------------");
		
		// 遍历
		for (EnumInClass.State enumValue : EnumInClass.State.values()) {
			System.out.println("enumValue: " + enumValue);
		}
		// 显示枚举的说明
		System.out.println(EnumInClass.State.STARTED.getDesc());
		// 声明枚举
		EnumInClass.State state = EnumInClass.State.STARTED;
		System.out.println("state: " + state);
		state = EnumInClass.State.STOPPED;
		System.out.println("state: " + state);
		// valueOf一个不存在的值会抛出运行期的异常。
		System.out.println("EnumInClass.State.valueOf(\"NotExistValue\"): " + EnumInClass.State.valueOf("NotExistValue"));
		System.out.println("EnumInClass.State.valueOf(\"STOPPED\"): " + EnumInClass.State.valueOf("STOPPED"));
		
		System.out.println("-------------" + " Enum as Class, as simple type " + "-------------");
		System.out.println();

		// ======== 枚举作为Class的一个常量成员 ==========
		System.out.println();
		System.out.println("-------------" + " Enum as Class, as static type " + "-------------");
		
		for (EnumInClass.ComunicationType enumValue : EnumInClass.ComunicationType.values()) {
			System.out.println("enumValue: " + enumValue);
		}
		// 声明
		EnumInClass.ComunicationType comuType = EnumInClass.ComunicationType.HTTP;
		System.out.println("comuType after declared: " + comuType);
		comuType = EnumInClass.ComunicationType.SOCKET;
		System.out.println("comuType after declared: " + comuType);

		System.out.println("-------------" + " Enum as Class, as static type " + "-------------");
		System.out.println();
		

	}

}
