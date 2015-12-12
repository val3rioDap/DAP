package com.innovery.mpm.provisioning.implementations.gui.util.options;

import com.innovery.mpm.provisioning.interfaces.gui.BsoResourceInterface;
import com.innovery.mpm.provisioning.interfaces.gui.util.options.ProvisioningTariffPlanInterface;

public class ProvisioningTariffPlan implements ProvisioningTariffPlanInterface {
	
	private BsoResourceInterface reference;

	public void enable(){
		reference.getRdbtnIn().setEnabled(false);
		reference.getRdbtnOut().setEnabled(false);
		reference.getRdbtnBookOut().setEnabled(false);
		reference.getRdbtnChange().setEnabled(false);
		reference.getRdbtnGet().setEnabled(true);
	}

	public void setReference(BsoResourceInterface reference) {
		this.reference = reference;
	}
}
