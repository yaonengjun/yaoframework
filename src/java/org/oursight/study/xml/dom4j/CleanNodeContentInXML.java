package org.oursight.study.xml.dom4j;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.dom4j.DocumentException;
import org.dom4j.io.XMLWriter;

public class CleanNodeContentInXML {

	public static void clean() throws Exception {
		String hbmFilePath = "/User.hbm.xml";
		java.net.URL url = CleanNodeContentInXML.class.getResource(hbmFilePath);
		System.out.println("URL: " + url.toString());
		org.dom4j.io.SAXReader reader = new org.dom4j.io.SAXReader();
		org.dom4j.Document dom4jDocument = null;
		try {
			dom4jDocument = reader.read(url);
		} catch (DocumentException e) {
			System.out.println("Load hbmFilePath " + hbmFilePath + " error");
			e.printStackTrace();
		}
		String dynamicComponentXPath = "/hibernate-mapping/class/dynamic-component";
		List nodes = dom4jDocument.selectNodes(dynamicComponentXPath);
		System.out.println("nodes: " + nodes);
		if (nodes == null || nodes.size() == 0) {
			System.out.println("Can not find dynamic-component element in User.hbm.xml which xpath is: "
					+ dynamicComponentXPath + ", so return directly.");
			return;
		}
		org.dom4j.Element dom4jElement = (org.dom4j.Element) nodes.get(0);
		System.out.println("before clean: " + dom4jElement.asXML());
		dom4jElement.clearContent();
		dom4jDocument.remove(dom4jElement);
		
		System.out.println("after clean: " + dom4jElement.asXML());
//		dom4jDocument.

		XMLWriter writer = new XMLWriter(new FileWriter(new File(url.toURI())));
		writer.write(dom4jDocument);
		writer.close();
//		org.dom4j.io.SAXWriter writer = new org.dom4j.io.SAXModifier)_

	}
	
	public static void save() throws Exception {
		String hbmFilePath = "/User.hbm.xml";
		java.net.URL url = CleanNodeContentInXML.class.getResource(hbmFilePath);
		System.out.println("URL: " + url.toString());
		org.dom4j.io.SAXReader reader = new org.dom4j.io.SAXReader();
		org.dom4j.Document dom4jDocument = null;
		try {
			dom4jDocument = reader.read(url);
		} catch (DocumentException e) {
			System.out.println("Load hbmFilePath " + hbmFilePath + " error");
			e.printStackTrace();
		}
		String dynamicComponentXPath = "/hibernate-mapping/class/dynamic-component";
		List nodes = dom4jDocument.selectNodes(dynamicComponentXPath);
		System.out.println("nodes: " + nodes);
		if (nodes == null || nodes.size() == 0) {
			System.out.println("Can not find dynamic-component element in User.hbm.xml which xpath is: "
					+ dynamicComponentXPath + ", so return directly.");
			return;
		}
		org.dom4j.Element dom4jElement = (org.dom4j.Element) nodes.get(0);
		System.out.println("before clean: " + dom4jElement.asXML());
		dom4jElement.setText("aaa");
//		dom4jDocument.add(dom4jElement);
		
		System.out.println("after clean: " + dom4jElement.asXML());
//		dom4jDocument.
		
		XMLWriter writer = new XMLWriter(new FileWriter(new File(url.toURI())));
		writer.write(dom4jDocument);
		writer.close();
//		org.dom4j.io.SAXWriter writer = new org.dom4j.io.SAXModifier)_
		
	}
	
	public static void main(String[] args) {
		try {
			save();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
