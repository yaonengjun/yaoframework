package org.oursight.study.database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Jndi {

	/**
	 * 从环境（JVM或者容器）中定义的JNDI数据源中获取Connection。
	 * 
	 * @return
	 * @author yaonengjun,2011-3-24 下午05:47:03
	 */
	public static Connection getConnectionFromJndiDataSource() {
		Connection con = null;
		try {
			Context ic = new InitialContext();
			DataSource source = (DataSource) ic.lookup("java:comp/env/jdbc/test");
			// ynj,20110324：在Web容器中配置JNDI数据源时，似乎需要用这种写法。不太确定。
			// DataSource source = (DataSource)ic.lookup("jdbc/test");
			con = source.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;
	}

}
