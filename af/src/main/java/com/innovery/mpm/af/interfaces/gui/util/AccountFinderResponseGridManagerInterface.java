package com.innovery.mpm.af.interfaces.gui.util;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;

public interface AccountFinderResponseGridManagerInterface {

	public void setResponseGridAssurance(String response_mpm);

	public void setComponents(DAPBeanInterface components);
	
	public void setResponseFieldset(JPanel responseFieldset);

	public void setResponseFieldsetGrid(JPanel responseFieldsetGrid);

	public void setActionButtonGroup(ButtonGroup actionButtonGroup);
}