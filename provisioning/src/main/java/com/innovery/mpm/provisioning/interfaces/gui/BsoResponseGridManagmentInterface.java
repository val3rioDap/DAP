package com.innovery.mpm.provisioning.interfaces.gui;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;

public interface BsoResponseGridManagmentInterface {

	public void setResponseGridStatusMessage(String message);

	public void setResponseGridAssurance(String response_mpm);

	public void resetResponseGrid();
	
	public void setReference(BsoResourceInterface reference);
	
	public void setComponents(DAPBeanInterface components);
}