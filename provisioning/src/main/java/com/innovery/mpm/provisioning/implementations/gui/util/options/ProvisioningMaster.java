package com.innovery.mpm.provisioning.implementations.gui.util.options;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;
import com.innovery.mpm.provisioning.implementations.util.PROVISIONINGActions;
import com.innovery.mpm.provisioning.interfaces.gui.BsoResourceInterface;
import com.innovery.mpm.provisioning.interfaces.gui.util.options.ProvisioningMasterInterface;

public class ProvisioningMaster implements ProvisioningMasterInterface {
	
	private BsoResourceInterface reference;
	private DAPBeanInterface components;

	public void enable(String actions){
		if(components.getConnectionManagerStandard().getSession().getUser_level()!=reference.getUser_levels().getGuest()){
			showCompleteActions(actions);
		}
		else if(components.getConnectionManagerStandard().getSession().getUser_level()==components.getUserLevels().getGuest()){
			showRestrictedActions();
		}
		showCommonActions(actions);
	}
	
	private void showCompleteActions(String actions){
		reference.getRdbtnIn().setEnabled(true);
		reference.getRdbtnOut().setEnabled(true);
	
		if (actions.contains(PROVISIONINGActions.PROVISIONING_ACTION_BOOK)){
			reference.getRdbtnBookOut().setEnabled(true);
		}
		
		if (actions.contains(PROVISIONINGActions.PROVISIONING_ACTION_CHG)){
			reference.getRdbtnChange().setEnabled(true);
		}
	}
	
	private void showRestrictedActions(){
		reference.getRdbtnIn().setEnabled(false);
		reference.getRdbtnOut().setEnabled(false);
		reference.getRdbtnBookOut().setEnabled(false);
		reference.getRdbtnChange().setEnabled(false);
	}
	
	private void showCommonActions(String actions){
		
		if(((String) reference.getSelectOptionCombo().getSelectedItem()).startsWith("SET")){
			reference.getRdbtnIn().setEnabled(false);
			reference.getRdbtnOut().setEnabled(false);
			reference.getRdbtnBookOut().setEnabled(false);
			reference.getRdbtnChange().setEnabled(false);
			reference.getRdbtnGet().setEnabled(false);
			
			if(actions.contains(PROVISIONINGActions.PROVISIONING_ACTION_IN)){
				reference.getRdbtnIn().setEnabled(true);
			}
			
			if(actions.contains(PROVISIONINGActions.PROVISIONING_ACTION_OUT)){
				reference.getRdbtnOut().setEnabled(true);
			}
			
			if(actions.contains(PROVISIONINGActions.PROVISIONING_ACTION_BOOK)){
				reference.getRdbtnBookOut().setEnabled(true);
			}
			
			if(actions.contains(PROVISIONINGActions.PROVISIONING_ACTION_CHG)){
				reference.getRdbtnChange().setEnabled(true);
			}
		}
		else{
			reference.getRdbtnGet().setEnabled(true);
		}
	}

	public void setReference(BsoResourceInterface reference) {
		this.reference = reference;
	}
	
	public void setComponents(DAPBeanInterface components) {
		this.components = components;
	}
}