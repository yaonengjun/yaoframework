package org.oursight.framework.yao.util;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class RequestUtil {

    private static final Logger LOG = Logger.getLogger(RequestUtil.class);

    /**
     * ��ȡָ��request��ָ������������ֵ.
     * @param request ָ��request
     * @param param ָ������
     * @return ����ֵ��������ʽ. ����ò��������ڻ��߽�������ʱ�������쳣, �򷵻�0.
     */
    public static int getParameterAsInt(HttpServletRequest request, String param) {
        return getParameterAsInt(request, param, 0);
    }

    /**
     * ��ȡָ��request��ָ������������ֵ. ����ò��������ڻ��߽�������ʱ�������쳣, �򷵻ظ�����Ĭ��ֵ.
     * @param request ָ��request
     * @param param ָ������
     * @param defaultValue ������Ĭ��ֵ.
     * @return ����ֵ��������ʽ. ����ò��������ڻ��߽�������ʱ�������쳣, �򷵻ظ�����Ĭ��ֵ.
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
     * �Ի�������booleanֵ���ظ�����Request�����е�ָ��������ȡֵ. ���Request�������Ҳ�����ָ������, �򷵻�false.
     * @param request ������Request����
     * @param param ָ������
     * @return ������Request�����е�ָ��������booleanȡֵ
     * @see RequestUtil#getParameterAsBool(HttpServletRequest, String, boolean)
     */
    public static boolean getParameterAsBool(HttpServletRequest request, String param) {
        return getParameterAsBool(request, param, false);
    }

    /**
     * �Ի�������booleanֵ���ظ�����Request�����е�ָ��������ȡֵ. ���Request�������Ҳ�����ָ������, �򷵻ظ�����Ĭ��ֵ.
     * <BR>
     * ��ʾ����ֵ���ַ�����Сд�޹�. ���ҽ�����ʾ����ֵ���ַ���Ϊ"true"ʱ(���Դ�Сд), ����true. ����:
     * <tt>"True"</tt> ���� <tt>true</tt>.<br>
     * ����: <tt>"yes"</tt> ���� <tt>false</tt>.
     * @param request ������Request����
     * @param param ָ������
     * @param defaultValue Ĭ��ȡֵ
     * @return ������Request�����е�ָ��������booleanȡֵ
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
     * ��ȡָ��request��ָ��������ֵ. ��������ڸò���, ���ؿմ�������null.
     * ������ת�봦��.
     * @param req ָ��request
     * @param param ָ������
     * @return ��������ڸò���, ���ؿմ�������null.
     * @deprecated ls@07-0521 ������Filter��ǰ�ô����˱���, �����б�Ҫʹ�ø÷���. ��{@link #getParameterAndTrim(HttpServletRequest, String)}����.
     */
    public static String getParameterAsGBK(HttpServletRequest req, String param) {
    	String result = req.getParameter(param);
    	return (result == null) ? "" : result.trim();
//        return getParamByEncoding(req, param, "ISO-8859-1", "GBK");
    }
    
    /**
     * ��ȡָ��request��ָ��������ֵ����trim����. ��������ڸò���, ����"".
     * @return ָ��������ֵ����trim����
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
     * �����Ƿ����ָ���Ĳ�����.
     * @param req ָ����request(Http����)
     * @param param ָ���Ĳ�����
     * @return ���ڷ���true, ���򷵻�false
     * @deprecated ls@07-0529 û�б�Ҫ�ṩ�����ķ���.
     */
    public static boolean existParameter(HttpServletRequest req, String param) {
        if (param == null) {
            return false;
        }
        return req.getParameter(param) != null;
    }
    
    /**
     * ��ȡ��ʾ����HTTP���������URL�ַ���(����QueryString). <BR>
     */
    public static String getFullGetStr(HttpServletRequest req) {
        final String qryStr = req.getQueryString();
        if (qryStr == null) {
            return req.getRequestURL().toString();
        }
        return req.getRequestURL().append('?').append(qryStr).toString();
    }
    
    /**
     * ����ҳ���ļ���.������ʵ���http://xxx.com/app1/path1/path2/page3.jsp, ��÷�������page3.jsp.
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
     * ��ָ����URL��Ӳ���
     * @param qryStr ԭ����URL
     * @param param �������ʽ  param1=values&param2=value2.....
     * @return ���ذ���param������URL

     */
    public static String addQryParam(String qryStr, String param) {
        if (qryStr == null || param == null) {
            return qryStr;
        }
        if(qryStr.indexOf('?') == -1 && qryStr.indexOf('=') == -1) {
        	return qryStr + '?' + param;
        }
        
        //TODO ���ӹ��߷��������������URL�ĸ�ʽ��ͬʱ�ѷǷ���URLת��Ϊ�Ϸ���URL
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
     * ��ȡ������request��ȫ����Header��Ϣ.
     * @param req ������request
     * @return ȫ��Header��Ϣ���ɵ��ַ���.
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
     * ��ȡ����request��Header�е�user-agentͷ��Cookie��Ϣ. <BR>
     * ��Ҫ���ڵ��Եȳ���.
     * @param req ������request
     * @return user-agentͷ��Cookie��Ϣ���ɵ��ַ���.
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
     * ��ȡ������Http�����Referer URL, ����һ��ҳ��. <BR>
     * @param req ������Http����
     * @return ����Http�����refererͷ��ֵ. ���������, ����""������null.
     */
    public static String getReferUrl(HttpServletRequest req) {
        String referUrl = req.getHeader(HttpConst.HEADER_REFER);
        return (referUrl == null) ? "" : referUrl;
    }

    /**
     * ��ȡ�����ַ����ڸ��������URL(����ڸ�Ӧ��)�е�λ��. <BR>
     * �Զ�̬ҳ��,�ȼ���<code>req.getServletPath().indexOf(someUri)</code>
     * ����: requestURI: /app/login.htm; ctx: /app; uri: /login.htm; return: 0 
     * @param req ��������
     * @param someUri �����ַ���
     * @return �����ַ���������URL�е�λ��. ��������ַ���(someUri)Ϊnull��"", ����-2.
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