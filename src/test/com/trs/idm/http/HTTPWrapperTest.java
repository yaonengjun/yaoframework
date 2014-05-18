package com.trs.idm.http;

import com.trs.idm.util.HttpConst;

import junit.framework.TestCase;


/**
 * @author yaonengjun,Apr 10, 2012 1:08:34 PM
 *
 */
public class HTTPWrapperTest extends TestCase {
	
	public void testMockPost() {
		String url = "http://localhost:8080/ids";
		Response resp = IDSWrapper.httpPost(url, HttpConst.IDSCMD_PING);
		System.out.println("resp: " +resp);
		System.out.println("isFine: " +resp.statusCodeIsFine());
		System.out.println("getMainInfo: " +resp.getMainInfo());
		System.out.println("getExceptionInfo: " +resp.getExceptionInfo());
		System.out.println("getError: " +resp.getError());
		System.out.println("getResponseBody: " +resp.getResponseBody());
	}

}
