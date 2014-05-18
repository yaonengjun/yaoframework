package org.oursight.study.xml.xpath;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XpathBasic {
	
	public static void main(String[] args) {
		Document document = null;
		try {
			document = new SAXReader().read(XpathBasic.class.getResourceAsStream("/AnalysisKnowledge.xml"));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		List knowledgeList = document.selectNodes("/analysisKnowledges/analysisKnowledge[responseCode=40002]");
//		List knowledgeList = document.selectNodes("//analysisKnowledge", "/analysisKnowledges/analysisKnowledge[responseCode=40002]", false);
		Iterator knowledgeIter = knowledgeList.iterator();
		while(knowledgeIter.hasNext()){
			Element knowledgeEle = (Element)knowledgeIter.next();
			Element responseCodeEle = knowledgeEle.element("responseCode");
			int responseCode = Integer.parseInt(responseCodeEle.getText());
			String respnoseDesc = responseCodeEle.attribute("desc").getText();
			System.out.println("responseCode: " + responseCode);
			System.out.println("respnoseDesc: " + respnoseDesc);
			System.out.println();
			
			Element adviceCodesEle = knowledgeEle.element("adviceCodes"); 
			Iterator adviceCodeIter = adviceCodesEle.elementIterator("adviceCode");
			while(adviceCodeIter.hasNext()){
				Element adviceCodeEle = (Element) adviceCodeIter.next();
				int adviceCode = Integer.parseInt(adviceCodeEle.getText());
				String adviceDesc = adviceCodeEle.attribute("desc").getText();
				System.out.println("adviceCode: " + adviceCode);
				System.out.println("adviceDesc: " + adviceDesc);
				System.out.println();
			}
		}
		
		System.out.println("-------------------");
		System.out.println();
	}

}
