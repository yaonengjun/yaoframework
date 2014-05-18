package org.oursight.study.opensource.httpunit;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class QueryInCtripByHttpUnit {
	
	public static void main(String[] args) {
		queryFlight();
	}
	
	
	
	private static void queryFlight() {
		String url = "http://flights.ctrip.com/Domestic/SearchFlights.aspx";
			WebConversation wc = new WebConversation();
			WebRequest request = new GetMethodWebRequest(url);
			try {
				WebResponse response = wc.getResponse(request);
//				WebForm form = response.getForms()[0];
		        System.out.println(response);
//		        System.out.println(form);
				
//				form.setParameter("userName", "zhangsan");
//		        form.setParameter("password", "zhangsan");
//		        
//		        WebResponse submitResponse = form.submit();
//		        String[] elementNames = submitResponse.getElementNames();
//		        for (int i = 0; i < elementNames.length; i++) {
//					String name = elementNames[i];
//					System.out.println(name);
//				}
//		        
//		        HTMLElement element = submitResponse.getElementWithID("divUsr");
//		        System.out.println(element.getText());
		        
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
