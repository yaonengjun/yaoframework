package org.oursight.framework.yao.util;

public class HttpConst {
    
    // HTTP��׼ͷ
    public static final String HEADER_USER_AGENT = "user-agent";
    
    public static final String HEADER_REFER = "referer";
    
    public static final String HEADER_ENCODING = "encoding";
    public static final String HEADER_NEW_SSOURL = "newSSOUrl";
    public static final String HEADER_SSO_ID = "ssoSessionId";

    // IDS�Զ���ͷ��ȡֵ
    public static final String HEADER_IDS_CMD = "trs-ids-cmd";
    public static final String IDSCMD_ADDUSER = "addUser";
    public static final String IDSCMD_DELUSER = "delUser";
    public static final String IDSCMD_LOGOUT = "logout";
    
    public static final String IDSCMD_UPDUSER = "updUser";
    public static final String IDSCMD_PING = "ping";
    public static final String IDSCMD_UPDSSOURL = "updSSOUrl";
    
    //lzp 2007-07-01 ���enableUser��disableUserָ��
    public static final String IDSCMD_ENABLEUSER = "enableUser";
    public static final String IDSCMD_DISABLEUSER = "disableUser";

    /**
     * IDS�Զ���ͷ({@link #HEADER_IDS_CMD})��ȡֵ: ��¼����.
     * @since 06.10.31 ls
     */
    public static final String IDSCMD_LOGIN = "login";
    
    public static final String X509CERTS_ATTRNAME = "javax.servlet.request.X509Certificate";

    /**
     * ��ʾIDS�õ��Ŀͻ���IP��ֵ.
     * @since 06.11.02 ls
     */
	public static final String HEADER_CLIENTIP = "clientip-idsknown";

    private HttpConst() {
    }
    
}