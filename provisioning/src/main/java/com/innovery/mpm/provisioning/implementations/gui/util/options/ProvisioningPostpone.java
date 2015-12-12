package com.innovery.mpm.provisioning.implementations.gui.util.options;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;
import com.innovery.mpm.provisioning.interfaces.gui.BsoResourceInterface;
import com.innovery.mpm.provisioning.interfaces.gui.util.options.ProvisioningPostponeInterface;

public class ProvisioningPostpone implements ProvisioningPostponeInterface {
	
	private BsoResourceInterface reference;
	private DAPBeanInterface components;

	public void enable(){
		if(components.getConnectionManagerStandard().getSession().getUser_level()!=reference.getUser_levels().getGuest()){
			showCompleteActions();
		}
		else if(components.getConnectionManagerStandard().getSession().getUser_level()==components.getUserLevels().getGuest()){
			showRestrictedActions();
		}
	}
	
	private void showCompleteActions(){
		reference.getRdbtnIn().setEnabled(true);
		reference.getRdbtnOut().setEnabled(false);
		reference.getRdbtnBookOut().setEnabled(false);
		reference.getRdbtnChange().setEnabled(false);
		reference.getRdbtnGet().setEnabled(false);
	}
	
	private void showRestrictedActions(){
		reference.getRdbtnOut().setEnabled(false);
		reference.getRdbtnBookOut().setEnabled(false);
		reference.getRdbtnChange().setEnabled(false);
		reference.getRdbtnGet().setEnabled(false);
	}

	public void setReference(BsoResourceInterface reference) {
		this.reference = reference;
	}

	public void setComponents(DAPBeanInterface components) {
		this.components = components;
	}
}
