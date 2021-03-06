package com.innovery.mpm.provisioning.interfaces.xml;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;

public interface XmlScannerInterface {

	public String getActionSelectedOption();
	
	public String getParameters(String action);
	
	public String getRegex(String parameter);
	
	public void loadSelectedOption(JComboBox optionComboboxSelection, ButtonGroup marketButtonGroup, ButtonGroup ctypeButtonGroup);
	
	public void setComponents(DAPBeanInterface components);
}
