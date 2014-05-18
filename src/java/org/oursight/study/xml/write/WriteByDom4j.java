package org.oursight.study.xml.write;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

public class WriteByDom4j {

	public static void main(String[] args) {
		WriteByDom4j dom4jSample = new WriteByDom4j();
		Document document = dom4jSample.createDocument();
		try {
			dom4jSample.FileWrite(document);

			Document documentStr = dom4jSample
					.StringToXML("<China>I Love!</China>");
			dom4jSample.XMLWrite(documentStr);

			Element legend = dom4jSample.FindElement(document);
			System.out.println(legend.getText());
		} catch (Exception e) {

		}
	}

	/*
	 * Create a XML Document
	 */
	public Document createDocument() {
		Document document = DocumentHelper.createDocument();

		Element root = document.addElement("root");

		Element author1 = root.addElement("Lynch");
		author1.addAttribute("Age", "25");
		author1.addAttribute("Country", "China");
		author1.addText("I am great!");

		Element author2 = root.addElement("Legend");
		author2.addAttribute("Age", "25");
		author2.addAttribute("Country", "China");
		author2.addText("I am great!too!");

		return document;
	}

	/*
	 * Create a XML document through String
	 */
	public Document StringToXML(String str) throws DocumentException {
		Document document = DocumentHelper.parseText(str);
		return document;
	}

	public Element FindElement(Document document) {
		Element root = document.getRootElement();
		Element legend = null;
		for (Iterator i = root.elementIterator("legend"); i.hasNext();) {
			legend = (Element) i.next();
		}
		return legend;
	}

	/*
	 * Write a XML file
	 */
	public void FileWrite(Document document) throws IOException {
		FileWriter out = new FileWriter("C:/Dom2jSample.xml");
		document.write(out);
		out.close();
	}

	/*
	 * Write a XML format file
	 */
	public void XMLWrite(Document document) throws IOException {
		XMLWriter writer = new XMLWriter(
				new FileWriter("C:/Dom2jSampleStr.xml"));
		writer.write(document);
		writer.close();
	}
}
