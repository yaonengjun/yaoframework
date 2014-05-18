package org.oursight.study.javase.jdk15.enumerated;

/**
 * 整个Class就是一个枚举
 * @author yaonengjun,2011-3-22 下午07:49:00
 *
 */
public enum EnumAsClass {
	WAIT, BUSY, ERROR,
	AABBCC;
	
	public String getDescription() {
		switch (this) {
		case WAIT:
			return "系统处于空闲状态";
		case BUSY:
			return "系统处于繁忙状态";
		case ERROR:
			return "系统处于未知状态";
		default:
			return "未知状态: "+ this.toString();
		}
	}
}
