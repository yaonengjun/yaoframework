package org.oursight.study.javase.jdk15.enumerated;

/**
 * 枚举的一些高级用法
 * @author yaonengjun,2011-3-22 下午08:01:08
 *
 */
public class EnumAdvance {
	
	/**
	 * 自定义枚举的构造函数，注意，此处将枚举类型定义为private以后，外界将无法直接调用
	 * @author yaonengjun,2011-3-22 下午08:56:26
	 *
	 */
	public static enum Type {
		APPLE("苹果"), ORANGE("橘子");
		
		private String desc;
		
		//public Type(String typeDesc) { //这样代码会编译错误，enum类型的构造函数不能声明为public
		Type(String typeDesc) {
			this.desc = typeDesc;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(EnumAdvance.Type.APPLE.desc);
		System.out.println(EnumAdvance.Type.ORANGE.desc);
		
		System.out.println("EnumAdvance.Type.valueOf(\"APPLE\"): " + EnumAdvance.Type.valueOf("APPLE"));
		System.out.println("EnumAdvance.Type.valueOf(\"APPLE\").desc: " + EnumAdvance.Type.valueOf("APPLE").desc);
	}

}
