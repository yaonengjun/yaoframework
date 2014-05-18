package org.oursight.study.javaee.web;

import com.trs.idm.util.UrlUtil;

public class URIEncoding {
	
	public static void main(String[] args) {
		System.out.println(UrlUtil.decode("%22%3e%3cscript%3ealert(68347014)%3c/script%3e"));
	}

}
