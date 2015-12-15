package com.innovery.mpm.provisioning.implementations.gui.util.options;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;
import com.innovery.mpm.provisioning.implementations.util.PROVISIONINGActions;
import com.innovery.mpm.provisioning.interfaces.gui.util.options.ProvisioningOptionTypeInterface;

public class ProvisioningOptionType implements ProvisioningOptionTypeInterface {
	
	private DAPBeanInterface components;
	
	private JRadioButton rdbtnIn;
	private JRadioButton rdbtnOut;
	private JRadioButton rdbtnBookOut;
	private JRadioButton rdbtnChange;
	private JRadioButton rdbtnGet;
	
	private JComboBox optionComboboxSelection;

	@Override
	public void enableMaster(String actions){
		if(components.getConnectionManagerStandard().getSession().getUser_level()!=components.getUserLevels().getGuest()){
			showCompleteActionsMaster(actions);
		}
		else if(components.getConnectionManagerStandard().getSession().getUser_level()==components.getUserLevels().getGuest()){
			showRestrictedActionsMaster();
		}
		showCommonActionsMaster(actions);
	}
	
	@Override
	public void enableDiscount(){
		if(components.getConnectionManagerStandard().getSession().getUser_level()!=components.getUserLevels().getGuest()){
			showCompleteActionsDiscount();
		}
		else if(components.getConnectionManagerStandard().getSession().getUser_level()==components.getUserLevels().getGuest()){
			showRestrictedActionsDiscount();
		}
	}
	
	@Override
	public void enablePostPone(){
		if(components.getConnectionManagerStandard().getSession().getUser_level()!=components.getUserLevels().getGuest()){
			showCompleteActionsPostPone();
		}
		else if(components.getConnectionManagerStandard().getSession().getUser_level()==components.getUserLevels().getGuest()){
			showRestrictedActionsPostPone();
		}
	}
	
	@Override
	public void enableTariffPlan(){
		rdbtnIn.setEnabled(false);
		rdbtnOut.setEnabled(false);
		rdbtnBookOut.setEnabled(false);
		rdbtnChange.setEnabled(false);
		rdbtnGet.setEnabled(true);
	}
	
	private void showCommonActionsMaster(String actions){
		
		if(((String) optionComboboxSelection.getSelectedItem()).startsWith("SET")){
			rdbtnIn.setEnabled(false);
			rdbtnOut.setEnabled(false);
			rdbtnBookOut.setEnabled(false);
			rdbtnChange.setEnabled(false);
			rdbtnGet.setEnabled(false);
			
			if(actions.contains(PROVISIONINGActions.PROVISIONING_ACTION_IN)){
				rdbtnIn.setEnabled(true);
			}
			
			if(actions.contains(PROVISIONINGActions.PROVISIONING_ACTION_OUT)){
				rdbtnOut.setEnabled(true);
			}
			
			if(actions.contains(PROVISIONINGActions.PROVISIONING_ACTION_BOOK)){
				rdbtnBookOut.setEnabled(true);
			}
			
			if(actions.contains(PROVISIONINGActions.PROVISIONING_ACTION_CHG)){
				rdbtnChange.setEnabled(true);
			}
		}
		else{
			rdbtnGet.setEnabled(true);
		}
	}
	
	private void showCompleteActionsMaster(String actions){
		rdbtnIn.setEnabled(true);
		rdbtnOut.setEnabled(true);
	
		if (actions.contains(PROVISIONINGActions.PROVISIONING_ACTION_BOOK)){
			rdbtnBookOut.setEnabled(true);
		}
		
		if (actions.contains(PROVISIONINGActions.PROVISIONING_ACTION_CHG)){
			rdbtnChange.setEnabled(true);
		}
	}
	
	private void showCompleteActionsDiscount(){
		rdbtnIn.setEnabled(true);
		rdbtnOut.setEnabled(true);
		rdbtnGet.setEnabled(true);
	}
	
	private void showCompleteActionsPostPone(){
		rdbtnIn.setEnabled(true);
		rdbtnOut.setEnabled(false);
		rdbtnBookOut.setEnabled(false);
		rdbtnChange.setEnabled(false);
		rdbtnGet.setEnabled(false);
	}
	
	private void showRestrictedActionsMaster(){
		rdbtnIn.setEnabled(false);
		rdbtnOut.setEnabled(false);
		rdbtnBookOut.setEnabled(false);
		rdbtnChange.setEnabled(false);
	}
	
	private void showRestrictedActionsPostPone(){
		rdbtnOut.setEnabled(false);
		rdbtnBookOut.setEnabled(false);
		rdbtnChange.setEnabled(false);
		rdbtnGet.setEnabled(false);
	}
	
	private void showRestrictedActionsDiscount(){
		rdbtnIn.setEnabled(false);
		rdbtnOut.setEnabled(false);
		rdbtnGet.setEnabled(true);
	}
	
	@Override
	public void setComponents(DAPBeanInterface components) {
		this.components = components;
	}

	@Override
	public void setRdbtnIn(JRadioButton rdbtnIn) {
		this.rdbtnIn = rdbtnIn;
	}

	@Override
	public void setRdbtnOut(JRadioButton rdbtnOut) {
		this.rdbtnOut = rdbtnOut;
	}

	@Override
	public void setRdbtnBookOut(JRadioButton rdbtnBookOut) {
		this.rdbtnBookOut = rdbtnBookOut;
	}

	@Override
	public void setRdbtnChange(JRadioButton rdbtnChange) {
		this.rdbtnChange = rdbtnChange;
	}

	@Override
	public void setRdbtnGet(JRadioButton rdbtnGet) {
		this.rdbtnGet = rdbtnGet;
	}

	@Override
	public void setOptionComboboxSelection(JComboBox optionComboboxSelection) {
		this.optionComboboxSelection = optionComboboxSelection;
	}
}