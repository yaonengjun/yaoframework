package org.oursight.opensource.velocity;

import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

public class HelloWorld {



	public static void main(String[] args) throws Exception {
		/* first, get and initialize an engine */
		 VelocityEngine ve = new VelocityEngine();
//		 ve.init();
		 ve.init("/Users/yaonengjun/Desktop/Dev/Java/eclipse_workspace/TRS/YaoFramework/WebRoot/WEB-INF/classes/org/oursight/opensource/velocity/velocity.properties");
		
		/* next, get the Template */
		Template t = ve.getTemplate("src/java/org/oursight/opensource/velocity/helloworld.vm");

		/* create a context and add data */
		VelocityContext context = new VelocityContext();
		context.put("name", "World");

		/* now render the template into a StringWriter */
		StringWriter writer = new StringWriter();
		t.merge(context, writer);

		/* show the World */
		System.out.println(writer.toString());
	}
}