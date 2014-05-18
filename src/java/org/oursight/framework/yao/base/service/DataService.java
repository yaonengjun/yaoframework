package org.oursight.framework.yao.base.service;

import org.oursight.framework.yao.base.data.dao.ICommonDao;

public class DataService {

	public DataService() {
	}

	public static Object getBean(String beanID) {
		return SpringManager.getApplicationContext().getBean(beanID);
	}

	public static ICommonDao getCommonDao() {
		return (ICommonDao) SpringManager.getApplicationContext().getBean("commonDao");
	}

}
