/*
 * Title: TRS 身份服务器 Copyright: Copyright (c) 2004-2012, TRS信息技术股份有限公司. All rights reserved. License: see the license
 * file. Company: TRS信息技术股份有限公司(www.trs.com.cn)
 * 
 * Created: yaonengjun@Apr 25, 2012 3:18:00 PM
 */
package org.oursight.opensource.velocity;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;

import net.sf.cglib.beans.BeanMap;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.ParseException;
import org.apache.velocity.runtime.parser.node.SimpleNode;

import com.trs.idm.data.bo.Template;
import com.trs.idm.exception.IdMException;
import com.trs.idm.util.ClassUtil;
import com.trs.idm.util.StringHelper;

/**
 * 使用Velocity进行模版替换的Formatter，支持 $IDSURL，$User.userName这种获取方式。<br>
 * 例如Map中如下：<br>
 * Map map = new HashMap();<br>
 * map.put("stringKey", "123");<br>
 * map.put("idsuser", new User());<br>
 * 那么在模版中，可以这么获取这些变量的值：$stringKey, $User.userName, $User.trueName
 * 
 * @author TRS信息技术有限公司
 * @since yaonengjun@Apr 25, 2012
 */
public class VelocityTemplateFormatter  {

	private final static Logger logger = Logger.getLogger(VelocityTemplateFormatter.class);

	/**
	 * @see com.trs.idm.model.notification.templateformatter.ITemplateFormatter#format(com.trs.idm.data.bo.Template,
	 *      java.util.Map)
	 */
	public Template format(Template template, Map args) throws IdMException {
		if (template == null) {
			return null;
		}

		VelocityContext context = transferAsVelocityContext(args);

		String formattedTitle = formatWithVelocity(template.getNoticeTitle(), context);
		String formattedContent = formatWithVelocity(template.getTemplateContent(), context);

		if (!StringHelper.isEmpty(formattedTitle)) {
			template.setNoticeTitle(formattedTitle);
		}

		if (!StringHelper.isEmpty(formattedContent)) {
			template.setTemplateContent(formattedContent);
		}

		template.setFormated(true);

		return template;

	}

	private VelocityContext transferAsVelocityContext(Map args) {
		VelocityContext context = new VelocityContext();
		if (args == null) {
			return context;
		}
		Iterator it = args.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String key = (String) entry.getKey();
			Object value = entry.getValue();
			if (value == null) {
				continue;
			}

			if ((value instanceof String) || ClassUtil.isPrimitiveWrapClass(value.getClass())) {
				context.put(key, value);
			} else {
				context.put(value.getClass().getSimpleName(), BeanMap.create(value));
			}

		}
		return context;
	}

	private String formatWithVelocity(String str, VelocityContext context) {
		if (StringHelper.isEmpty(str)) {
			return null;
		}

		RuntimeServices runtimeServices = RuntimeSingleton.getRuntimeServices();
		StringReader title = new StringReader(str);
		SimpleNode node;
		try {
			node = runtimeServices.parse(title, "ids.vm");
		} catch (ParseException e) {
			logger.error("Format " + str + " with Velocity error  ", e);
			return null;
		}

		org.apache.velocity.Template vTemplate = new org.apache.velocity.Template();
		vTemplate.setRuntimeServices(runtimeServices);
		vTemplate.setData(node);
		vTemplate.initDocument();

		StringWriter writer = new StringWriter();
		vTemplate.merge(context, writer);

		return writer.toString();
	}
}
