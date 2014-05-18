package org.oursight.framework.yao.util;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class RequestUtil {

    private static final Logger LOG = Logger.getLogger(RequestUtil.class);

    /**
     * 获取指定request的指定参数的整数值.
     * @param request 指定request
     * @param param 指定参数
     * @return 参数值的整数形式. 如果该参数不存在或者解析整数时发生了异常, 则返回0.
     */
    public static int getParameterAsInt(HttpServletRequest request, String param) {
        return getParameterAsInt(request, param, 0);
    }

    /**
     * 获取指定request的指定参数的整数值. 如果该参数不存在或者解析整数时发生了异常, 则返回给定的默认值.
     * @param request 指定request
     * @param param 指定参数
     * @param defaultValue 给定的默认值.
     * @return 参数值的整数形式. 如果该参数不存在或者解析整数时发生了异常, 则返回给定的默认值.
     */
    public static int getParameterAsInt(HttpServletRequest request,
            String param, int defaultValue) {
        final String value = request.getParameter(param);
        try {
            return (value == null || value.trim().length() == 0) ? defaultValue : Integer.parseInt(value);
        } catch (Exception e) {
            LOG.error(e + ", param=" + param + ", value=" + value + ". skip the exception and return " + defaultValue);
        }
        return defaultValue;
    }
    
    /**
     * 以基本类型boolean值返回给定的Request对象中的指定参数的取值. 如果Request对象中找不到该指定参数, 则返回false.
     * @param request 给定的Request对象
     * @param param 指定参数
     * @return 给定的Request对象中的指定参数的boolean取值
     * @see RequestUtil#getParameterAsBool(HttpServletRequest, String, boolean)
     */
    public static boolean getParameterAsBool(HttpServletRequest request, String param) {
        return getParameterAsBool(request, param, false);
    }

    /**
     * 以基本类型boolean值返回给定的Request对象中的指定参数的取值. 如果Request对象中找不到该指定参数, 则返回给定的默认值.
     * <BR>
     * 表示布尔值的字符串大小写无关. 当且仅当表示布尔值的字符串为"true"时(忽略大小写), 返回true. 例如:
     * <tt>"True"</tt> 返回 <tt>true</tt>.<br>
     * 再如: <tt>"yes"</tt> 返回 <tt>false</tt>.
     * @param request 给定的Request对象
     * @param param 指定参数
     * @param defaultValue 默认取值
     * @return 给定的Request对象中的指定参数的boolean取值
     */
    public static boolean getParameterAsBool(HttpServletRequest request,
            String param, boolean defaultValue) {
        boolean result = defaultValue;
        String paramValue = request.getParameter(param);
        if (paramValue != null) {
            result = "true".equalsIgnoreCase(paramValue);
        }
        return result;
    }

    /**
     * 获取指定request的指定参数的值. 如果不存在该参数, 返回空串而不是null.
     * 不再做转码处理.
     * @param req 指定request
     * @param param 指定参数
     * @return 如果不存在该参数, 返回空串而不是null.
     * @deprecated ls@07-0521 由于在Filter中前置处理了编码, 不再有必要使用该方法. 用{@link #getParameterAndTrim(HttpServletRequest, String)}代替.
     */
    public static String getParameterAsGBK(HttpServletRequest req, String param) {
    	String result = req.getParameter(param);
    	return (result == null) ? "" : result.trim();
//        return getParamByEncoding(req, param, "ISO-8859-1", "GBK");
    }
    
    /**
     * 获取指定request的指定参数的值并作trim处理. 如果不存在该参数, 返回"".
     * @return 指定参数的值并作trim处理
     * @since ls@08.0107
     */
    public static String getParameterAndTrim(HttpServletRequest req, String param) {
    	String result = req.getParameter(param);
    	return (result == null) ? "" : result.trim();
    }
    
    public static String getAttributeAsTrimStr(HttpServletRequest req, String attrName) {
        return getAttributeAsTrimStr(req, attrName, "");
    }

    public static String getAttributeAsTrimStr(HttpServletRequest req, String attrName, String defValue) {
        Object obj = req.getAttribute(attrName);
        return (obj instanceof String) ? ((String) obj).trim() : defValue;
    }

    /**
     * 检验是否存在指定的参数项.
     * @param req 指定的request(Http请求)
     * @param param 指定的参数项
     * @return 存在返回true, 否则返回false
     * @deprecated ls@07-0529 没有必要提供这样的方法.
     */
    public static boolean existParameter(HttpServletRequest req, String param) {
        if (param == null) {
            return false;
        }
        return req.getParameter(param) != null;
    }
    
    /**
     * 获取表示给定HTTP请求的完整URL字符串(包括QueryString). <BR>
     */
    public static String getFullGetStr(HttpServletRequest req) {
        final String qryStr = req.getQueryString();
        if (qryStr == null) {
            return req.getRequestURL().toString();
        }
        return req.getRequestURL().append('?').append(qryStr).toString();
    }
    
    /**
     * 返回页面文件名.比如访问的是http://xxx.com/app1/path1/path2/page3.jsp, 则该方法返回page3.jsp.
     */
    public static String getCurrentPage(HttpServletRequest req) {
        final String requestURI = req.getRequestURI();
        return requestURI.substring(requestURI.lastIndexOf('/') + 1);
    }
    
    public static String getCurPageWithQryStr(HttpServletRequest req) {
        return getCurPageWithQryStr(req, null);
    }

    public static String getCurPageWithQryStr(HttpServletRequest req, String param) {
        String qryStr = removeQryParam(req.getQueryString(), param);
        if (qryStr == null) {
            return getCurrentPage(req);
        }
        return new StringBuffer(getCurrentPage(req)).append('?').append(qryStr).toString();
    }
    
    public static String removeQryParam(String qryStr, String param) {
        if (qryStr == null || param == null) {
            return qryStr;
        }
        String[] params = StringHelper.split(qryStr, "&");
        StringBuffer sb = new StringBuffer(qryStr.length());
        for (int i = 0; i < params.length; i++) {
            if (params[i].startsWith(param + "=")) {
                continue;
            }
            sb.append(params[i]).append('&');
        }
        return (sb.length() > 0) ? sb.deleteCharAt(sb.length() - 1).toString() : null;
    }
    
    /**
     * 给指定的URL添加参数
     * @param qryStr 原请求URL
     * @param param 参数表达式  param1=values&param2=value2.....
     * @return 返回包含param的完整URL

     */
    public static String addQryParam(String qryStr, String param) {
        if (qryStr == null || param == null) {
            return qryStr;
        }
        if(qryStr.indexOf('?') == -1 && qryStr.indexOf('=') == -1) {
        	return qryStr + '?' + param;
        }
        
        //TODO 增加工具方法，检验参数和URL的格式，同时把非法的URL转变为合法的URL
        if(qryStr.endsWith("?"))
        	qryStr = qryStr.substring(0, qryStr.indexOf('?'));
        return qryStr + (qryStr.indexOf('?') >=0 ? '&':'?') + param;
    }
    
    public static String getRequestInfo(HttpServletRequest req) {
        StringBuffer sb = new StringBuffer(320);
        sb.append("[Req]");
        sb.append(req.getClass().getName());
        sb.append(": (").append(req.getScheme()).append(')').append(req.getServerName()).append(':').append(req.getServerPort());
        sb.append(", ").append(req.getMethod()).append(' ').append(req.getProtocol());
        sb.append(", uri=").append(req.getRequestURI());
        sb.append(", ctx=").append(req.getContextPath());
        sb.append(", servlet=").append(req.getServletPath());
        sb.append(", qryStr=").append(req.getQueryString());
        sb.append(", refer=").append(req.getHeader(HttpConst.HEADER_REFER));
        sb.append(", useragt=").append(req.getHeader(HttpConst.HEADER_USER_AGENT));
        sb.append(", ip=").append(req.getRemoteAddr());
        sb.append(", encoding=").append(req.getCharacterEncoding());
        return sb.toString();
    }

    /**
     * 获取给定的request中全部的Header信息.
     * @param req 给定的request
     * @return 全部Header信息构成的字符串.
     */
    public static String getAllHeadersStr(HttpServletRequest req) {
        StringBuffer sb = new StringBuffer(256);
        String header = null;
        for (Enumeration headers = req.getHeaderNames(); headers.hasMoreElements();) {
            header = (String) headers.nextElement();
            sb.append(header);
            sb.append("=");
            sb.append(req.getHeader(header));
            sb.append("\r\n");
        }
        return sb.toString();
    }

    /**
     * 获取给定request的Header中的user-agent头和Cookie信息. <BR>
     * 主要用于调试等场合.
     * @param req 给定的request
     * @return user-agent头和Cookie信息构成的字符串.
     */
    public static String getUserAgentAndCookies(HttpServletRequest req) {
        StringBuffer sb = new StringBuffer(256);
        sb.append(HttpConst.HEADER_USER_AGENT).append('=').append(req.getHeader(HttpConst.HEADER_USER_AGENT))
                .append(';');
        sb.append(HttpConst.HEADER_IDS_CMD).append('=').append(req.getHeader(HttpConst.HEADER_IDS_CMD)).append('\n');

        sb.append("cookies: ");
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                sb.append(cookies[i].getName()).append('=').append(
                        cookies[i].getValue());
                sb.append(';');
            }
        }
        return sb.toString();
    }
    
    /**
     * 获取给定的Http请求的Referer URL, 即上一个页面. <BR>
     * @param req 给定的Http请求
     * @return 给定Http请求的referer头的值. 如果不存在, 返回""而不是null.
     */
    public static String getReferUrl(HttpServletRequest req) {
        String referUrl = req.getHeader(HttpConst.HEADER_REFER);
        return (referUrl == null) ? "" : referUrl;
    }

    /**
     * 获取给定字符串在给定请求的URL(相对于该应用)中的位置. <BR>
     * 对动态页面,等价于<code>req.getServletPath().indexOf(someUri)</code>
     * 例子: requestURI: /app/login.htm; ctx: /app; uri: /login.htm; return: 0 
     * @param req 给定请求
     * @param someUri 给定字符串
     * @return 给定字符串在请求URL中的位置. 如果给定字符串(someUri)为null或"", 返回-2.
     */
    public static int getPageUriPosInRequest(HttpServletRequest req, String someUri) {
        if (someUri == null || someUri.trim().length() == 0) {
            return -2;
        }
        return getRelativePath(req).indexOf(someUri);
//        return req.getServletPath().indexOf(someUri);
    }

    /**
     * Return the webapp root path.<br>
     * Example:<br>
     * if request "http://localhost:8080/app1/dir1/page1.jsp", the method return
     * "http://localhost:8080/app1".
     */
    public static String getContextRoot(HttpServletRequest request) {
        String sysUrl = request.getRequestURL().toString();
        if(sysUrl.indexOf(request.getServletPath()) == -1) {
        	return sysUrl;
        }
        return sysUrl.substring(0, sysUrl.indexOf(request.getServletPath()));
    }

    /**
     * for Dynamic Pages, this method as same as <code>req.getServletPath()</code>, 
     * but the method also valid for Static Content, such as html, gif, css etc.<br>
     * Example:<br>
     * if request "http://localhost:8080/app1/dir1/page1.jsp", the method return
     * "/dir1/page1.jsp".
     * @param req the spec request
     * @return the relative url
     */
    public static String getRelativePath(HttpServletRequest req) {
        // ls@2005-11-02 req.getRequestURI().substring(req.getContextPath().length()) == req.getServletPath() ? NO! i.e.WebLogic!
        return req.getRequestURI().substring(req.getContextPath().length());
    }

    /**
     * @param application
     * @return ServletContainerInfo
     */
    public static String getServletContainerInfo(final ServletContext application) {
        StringBuffer sb = new StringBuffer(64);
        sb.append(application.getServerInfo());
        sb.append(" (Servlet ").append(application.getMajorVersion())
                .append('.').append(application.getMinorVersion()).append(')');
        return sb.toString();
    }
    
    /**
     * simple log method for jsp page.
     * @param obj
     * @param req
     */
    public static void log(Object obj, HttpServletRequest req) {
        StringBuffer sb = new StringBuffer(256);
        sb.append(new java.sql.Timestamp(System.currentTimeMillis()));
        if (req != null) {
            sb.append('\t').append(req.getRequestURI());
        }
        sb.append('\t').append(obj);
        System.out.println(sb);
    }
    
    /**
     * simple log method for jsp page.
     * @param req
     */
    public static void log(HttpServletRequest req) {
        log(getRequestInfo(req), null);
    }
    
}