package org.oursight.framework.yao.base.service;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class SpringContextLoaderListener extends ContextLoaderListener {

	public SpringContextLoaderListener() {
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.context.ContextLoaderListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		super.contextInitialized(arg0);
		org.springframework.web.context.WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(arg0.getServletContext());
		SpringManager.setApplicationContext(context);	
	}
	
}
