package com.innovery.mpm.provisioning.implementations.xml;

/**
 * READ THE LIST OF XML AND SHOWS IT IN COMBO BOX. THIS ALLOWS TO SELECT THE ROPZ TO ACTIVATE
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;
import com.innovery.mpm.provisioning.interfaces.xml.XmlListReaderInterface;

public class XmlListReader implements XmlListReaderInterface{
	
	private DAPBeanInterface components;
	private SAXBuilder builder;
	private Document doc;
	
	public List<String> getList(String market, String ctype) {
		
		this.builder = new SAXBuilder();
		this.doc = null;
		if(market.equals(DAPBeanInterface.CONSUMER)){
				try {
					if(ctype.equals(DAPBeanInterface.PREPAID)){
						doc = builder.build(getClass().getResourceAsStream(components.XML_LIST_CONSUMER_PREPAID_PATH()));
					}
					
					else if(ctype.equals(DAPBeanInterface.POSTPAID)){
						doc = builder.build(getClass().getResourceAsStream(components.XML_LIST_CONSUMER_POSTPAID_PATH()));
					}
					
				} catch (JDOMException e) {
					components.getLogger().error(e.toString());
				} catch (IOException e) {
					components.getLogger().error(e.toString());
				}
		}
		else if(market.equals(DAPBeanInterface.CORPORATE)){
				try {
					if(ctype.equals(DAPBeanInterface.PREPAID)){
						doc = builder.build(getClass().getResourceAsStream(components.XML_LIST_CORPORATE_PREPAID_PATH()));
					}
					
					else if(ctype.equals(DAPBeanInterface.POSTPAID)){
						doc = builder.build(getClass().getResourceAsStream(components.XML_LIST_CORPORATE_POSTPAID_PATH()));
					}
					
				} catch (JDOMException e) {
					components.getLogger().error(e.toString());
				} catch (IOException e) {
					components.getLogger().error(e.toString());
				}
			}
		
		Element root = doc.getRootElement();
		List<String> ropz_code_list = new ArrayList<String>();
		List<Element> ropz_list = root.getChildren();
		for(int i=0; i<ropz_list.size(); i++){
			Element ropz = ropz_list.get(i);
			String ropz_code = ropz.getAttributeValue("code");
			components.getLogger().info("OPTION: "+ropz_code);
			ropz_code_list.add(ropz_code);
		}
		return ropz_code_list;
	}

	public void setComponents(DAPBeanInterface components) {
		this.components = components;
	}
}