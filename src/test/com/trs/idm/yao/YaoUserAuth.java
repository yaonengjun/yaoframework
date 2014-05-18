package com.trs.idm.yao;

import com.trs.idm.data.bo.User;
import com.trs.idm.model.auth.AbstractUserPwdAuth;

public class YaoUserAuth extends AbstractUserPwdAuth {

	public boolean canPass(User user, String passwd) {
		
		System.out.println();
		System.out.println("============================");
		System.out.println("user.getReversiblePasswd(): " + user.getReversiblePasswd());
		System.out.println("password to verify: " + passwd);
		System.out.println("User: " + user);
		System.out.println("============================");
		System.out.println();
		
		return false;
		
	}
	
	

}
