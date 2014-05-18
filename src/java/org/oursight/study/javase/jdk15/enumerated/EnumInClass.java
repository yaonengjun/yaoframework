package org.oursight.study.javase.jdk15.enumerated;

/**
 * 枚举变量作为类中的一个成员
 * @author yaonengjun,2011-3-22 下午08:00:42
 *
 */
public class EnumInClass {

	public enum State {
		STARTING, STARTED, STOPPING, STOPPED;
		
		public String getDesc() {
			switch (this) {
			case STARTING:
				return "启动中";
			case STARTED:
				return "已启动";
			case STOPPING:
				return "停止中";
			case STOPPED:
				return "已停止";
			default:
				return "未知状态: " + this.toString();
			}
		}
	}
	
	public static enum ComunicationType {
		SOCKET ,HTTP,WEBSERVICE;
	}
	

}
