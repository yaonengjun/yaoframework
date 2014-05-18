package org.oursight.framework.yao.base.service;

import org.springframework.context.ApplicationContext;

/**
 * ����spring��ApplicationContext
 */
public final class SpringManager {
	private static ApplicationContext a;

	public SpringManager() {
	}

	public static void setApplicationContext(
			ApplicationContext applicationcontext) {
		if (a == null) {
			a = applicationcontext;
		}
	}

	public static ApplicationContext getApplicationContext() {
		return a;
	}
}
