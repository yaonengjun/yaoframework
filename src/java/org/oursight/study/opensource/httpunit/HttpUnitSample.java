package org.oursight.study.opensource.httpunit;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.HTMLElement;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class HttpUnitSample {
	
	public static void main(String[] args) {
//		testBasic();
		testFormSubmit();
	}
	
	private static void testBasic() {
		String url = "http://localhost:8080/demo";
			WebConversation wc = new WebConversation();
			WebRequest request = new GetMethodWebRequest(url);
			try {
				WebResponse response = wc.getResponse(request);
				System.out.println("response: "+ response);
				System.out.println("-----");
				
				System.out.println("response getTitle: "+ response.getTitle());
				System.out.println("-----");
				
				System.out.println("response getText: "+ response.getText());
				System.out.println("-----");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	private static void testFormSubmit() {
		String url = "http://localhost:8080/demo";
			WebConversation wc = new WebConversation();
			WebRequest request = new GetMethodWebRequest(url);
			try {
				WebResponse response = wc.getResponse(request);
				WebForm form = response.getForms()[0];
				form.setParameter("userName", "zhangsan");
		        form.setParameter("password", "zhangsan");
		        
		        WebResponse submitResponse = form.submit();
//		        System.out.println(submitResponse.getText());
		        String[] elementNames = submitResponse.getElementNames();
		        for (int i = 0; i < elementNames.length; i++) {
					String name = elementNames[i];
					System.out.println(name);
				}
		        
		        HTMLElement element = submitResponse.getElementWithID("divUsr");
		        System.out.println(element.getText());
		        
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
