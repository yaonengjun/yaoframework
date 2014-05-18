package org.oursight.opensource.velocity;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.apache.velocity.tools.generic.DateTool;

/**
 * 测试提交.
 * 
 * @author yaonengjun,Apr 25, 2012 3:28:47 PM
 *
 */
public class GenerateVelocityTempateFromString {

	public static void main(String[] args) throws Exception {
		/* first, get and initialize an engine */
		VelocityEngine ve = new VelocityEngine();
		ve.init();

		RuntimeServices runtimeServices = RuntimeSingleton.getRuntimeServices();
		StringReader toReplaceString = new StringReader("Hello $name %name% %name $NAME  $map.name $USER.userName ${user.userName} $user.userName $user.username $User.userName ${User.userName} $!userObject.userName $today $dateTool.format('yyyy-MM-dd HH:mm:ss',$today) to velocity");
		SimpleNode node = runtimeServices.parse(toReplaceString, "helloworld.vm");
		Template template = new Template();
		template.setRuntimeServices(runtimeServices);
		template.setData(node);
		template.initDocument();

		/* create a context and add data */
		VelocityContext context = new VelocityContext();
		context.put("name", "World");

		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "yaonengjun");
		map.put("gender", "male");
		context.put("map", map);
		

		User user = new User();
		System.out.println(user.getClass().getSimpleName());
		user.setUserName("userName(in User)");
		user.setPassword("password(in User");
		context.put(user.getClass().getSimpleName(), net.sf.cglib.beans.BeanMap.create(user));
		context.put("userObject", user);
		
		String aaa = "aaabbb";
		System.out.println( net.sf.cglib.beans.BeanMap.create("aaa"));
		context.put(aaa.getClass().getSimpleName(), net.sf.cglib.beans.BeanMap.create("aaa"));
		context.put("today", new Date());
		context.put("dateTool", new DateTool());
		
		/* now render the template into a StringWriter */
		StringWriter writer = new StringWriter();
		template.merge(context, writer);

		/* show the World */
		System.out.println(writer.toString());
	}

}

class User {

	public User() {

	}

	private String userName;

	private String password;

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

}