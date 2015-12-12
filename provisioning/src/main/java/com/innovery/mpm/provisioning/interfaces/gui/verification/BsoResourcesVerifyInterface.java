package com.innovery.mpm.provisioning.interfaces.gui.verification;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;
import com.innovery.mpm.provisioning.interfaces.gui.BsoResourceInterface;

public interface BsoResourcesVerifyInterface {

	public boolean verifyOptionalParameters();
	
	public void setReference(BsoResourceInterface reference);
	
	public void setComponents(DAPBeanInterface components);
}
