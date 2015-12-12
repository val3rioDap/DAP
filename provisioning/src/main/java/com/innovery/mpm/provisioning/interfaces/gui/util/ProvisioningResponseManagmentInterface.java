package com.innovery.mpm.provisioning.interfaces.gui.util;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;
import com.innovery.mpm.provisioning.interfaces.action.ActionCommandBSOInterface;
import com.innovery.mpm.provisioning.interfaces.gui.BsoResourceInterface;

public interface ProvisioningResponseManagmentInterface {

	public void getResponse();
	
	public void setReference(BsoResourceInterface reference);
	
	public void setComponents(DAPBeanInterface components);
	
	public void setAction_bso(ActionCommandBSOInterface action_bso);
}
