这是一个检查Filter执行顺序的代码。

一共有2个Filter，Filte1和Filter2，其主要逻辑代码如下：
		System.out.println("Filter1.doFilter() before fc.doFilter");
		fc.doFilter(req, resp);
		System.out.println("Filter1.doFilter() after fc.doFilter");
		
		
在WEB-INF中找到 web.FilterOrder.xml,将其中的内容拷贝到web.xml中
web.xml中主要内容如下：
	<filter>
		<filter-name>Filter1</filter-name>
		<filter-class>org.oursight.study.javaee.filter.filterOrder.Filter1</filter-class>
	</filter>

	<filter>
		<filter-name>Filter2</filter-name>
		<filter-class>org.oursight.study.javaee.filter.filterOrder.Filter2</filter-class>
	</filter>


	<filter-mapping>
		<filter-name>Filter1</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
	<filter-mapping>
		<filter-name>Filter2</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
	
	

随便访问一个页面执行结果如下：
Filter1.doFilter() before fc.doFilter
Filter2.doFilter() before fc.doFilter
Filter2.doFilter() after fc.doFilter
Filter1.doFilter() after fc.doFilter
