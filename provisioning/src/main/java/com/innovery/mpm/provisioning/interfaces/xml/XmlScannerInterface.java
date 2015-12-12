package com.innovery.mpm.provisioning.interfaces.xml;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;
import com.innovery.mpm.provisioning.interfaces.gui.BsoResourceInterface;

public interface XmlScannerInterface {

	public String getAction();
	
	public String getParameters(String action);
	
	public String getRegex(String parameter);
	
	public void setReference(BsoResourceInterface reference);
	
	public void setComponents(DAPBeanInterface components);
}
