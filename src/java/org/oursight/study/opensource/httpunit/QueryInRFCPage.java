package org.oursight.study.opensource.httpunit;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class QueryInRFCPage {
	
	public static void main(String[] args) {
		query();
	}
	
	
	
	private static void query() {
		String url = "http://www.ietf.org/rfc.html";
			WebConversation wc = new WebConversation();
			WebRequest request = new GetMethodWebRequest(url);
			try {
				WebResponse response = wc.getResponse(request);
		        if(response == null || response.getResponseCode() != 200) {
		        	System.out.println("Can not get proper response, response is: " + response +", finish!");
		        	return;
		        }
		        
		        WebForm form = response.getFormWithID("form1");
		        if(form == null) {
		        	System.out.println("Can not find form in response by id: form1, finish!" );
		        	return;
		        }
		        	
		        form.setParameter("number", "2034");
		        WebResponse submitResponse = form.submit();
		        System.out.println(submitResponse.getResponseCode());
		        System.out.println(submitResponse.getText());
		        
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
