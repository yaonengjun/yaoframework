package org.oursight.study.database;

import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class Spring {

	/**
	 * 从Spring定义的数据源中获取Connection的方法，也可以参见本工程中的如下页面：WebRoot\study\datasource\spring.jsp
	 * 
	 * 以下是本代码对应的Spring数据源的bean： <bean id="dataSource"
	 * class="org.springframework.jndi.JndiObjectFactoryBean"> <property name="jndiName"
	 * value="jdbc/aaaDS" /> </bean>
	 * 
	 * @param application
	 * @return
	 * @author yaonengjun,2011-3-24 下午05:42:50
	 */
	public static Connection getConnectionFromSpringBean(ServletContext application) {
		ApplicationContext ctx = null;
		Connection con = null;
		try {
			ctx = WebApplicationContextUtils.getWebApplicationContext(application);
			con = DataSourceUtils.getConnection((DataSource) ctx.getBean("dataSource"));
		} catch (Throwable t) {
			t.printStackTrace();
		}

		return con;

	}

}
