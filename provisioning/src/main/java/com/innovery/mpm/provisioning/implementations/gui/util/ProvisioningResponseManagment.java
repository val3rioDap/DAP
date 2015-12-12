package com.innovery.mpm.provisioning.implementations.gui.util;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;
import com.innovery.mpm.provisioning.implementations.bean.CommandBSO;
import com.innovery.mpm.provisioning.interfaces.action.ActionCommandBSOInterface;
import com.innovery.mpm.provisioning.interfaces.bean.CommandBSOInterface;
import com.innovery.mpm.provisioning.interfaces.gui.BsoResourceInterface;
import com.innovery.mpm.provisioning.interfaces.gui.util.ProvisioningResponseManagmentInterface;

public class ProvisioningResponseManagment implements ProvisioningResponseManagmentInterface{
	
	private DAPBeanInterface components;
	private BsoResourceInterface reference;
	private ActionCommandBSOInterface action_bso;
	
	public void getResponse() {	
		
		components.getLogger().info("SAVING SESSION");
		saveSessionInfo();
		components.getLogger().info("SESSION SAVED");
		
		String response_mpm = action_bso.executeBsoCommand(createBean());
	    
	    if(response_mpm.equals(components.getErrorMessages().get_NOT_LOGGED_WARN()) || response_mpm.equals(components.getErrorMessages().get_MPM_CONNECTION_WARN())){
			reference.getGrid_managment().resetResponseGrid();
			reference.getGrid_managment().setResponseGridStatusMessage(response_mpm);
	    
			if(response_mpm.equals(components.getErrorMessages().get_NOT_LOGGED_WARN())){
				reference.getBtnExecute().setText("Reconnect");
				reference.getBtnExecute().setActionCommand("reconnect");
			}
			else if(response_mpm.equals(components.getErrorMessages().get_MPM_CONNECTION_WARN())){
				reference.getBtnExecute().setText("Home");
				reference.getBtnExecute().setActionCommand("home");
			}
		}

		else{
			reference.getTextArea().setText(response_mpm);
			reference.getGrid_managment().setResponseGridAssurance(response_mpm);
		    reference.getBtnExecute().setEnabled(true);
		}
		reference.getBtnExecute().setEnabled(true);
	}
	
	private CommandBSOInterface createBean(){
		components.getLogger().info("SETTING LOCAL VARIABLES");
		String tmp_msisdn = reference.getMsisdn_field().getText();
		String tmp_service = technicalOptionManagment((String) reference.getSelectOptionCombo().getSelectedItem());
		String tmp_market = reference.getRadioGroupMarket().getSelection().getActionCommand();
		String tmp_ctype = reference.getRadioGroupCtype().getSelection().getActionCommand();
		String tmp_action =reference. getRadioGroupAction().getSelection().getActionCommand();
		String tmp_trans_id = components.getTRANSID().generateTransID();
		String tmp_Discount_value = (String) reference.getSelectDiscountCombo().getSelectedItem();
		String tmp_parameter_1_name = reference.getParameter_1_label().getText(); String tmp_parameter_1_value = reference.getParameter_1_field().getText();
		String tmp_parameter_2_name = reference.getParameter_2_label().getText(); String tmp_parameter_2_value = reference.getParameter_2_field().getText();
		String tmp_parameter_3_name = reference.getParameter_3_label().getText(); String tmp_parameter_3_value = reference.getParameter_3_field().getText();
		String tmp_parameter_4_name = reference.getParameter_4_label().getText(); String tmp_parameter_4_value = reference.getParameter_4_field().getText();
		String tmp_parameter_5_name = reference.getParameter_5_label().getText(); String tmp_parameter_5_value = reference.getParameter_5_field().getText();
		String tmp_parameter_6_name =reference. getParameter_6_label().getText(); String tmp_parameter_6_value = reference.getParameter_6_field().getText();
		
		components.getLogger().info("CREATING NEW CommandBSO");
		CommandBSOInterface bso = new CommandBSO(tmp_msisdn, 
				tmp_service, 
				tmp_market, 
				tmp_ctype,
				tmp_action,
				tmp_trans_id,
				tmp_parameter_1_name,
				tmp_parameter_1_value,
				tmp_parameter_2_name,
				tmp_parameter_2_value,
				tmp_parameter_3_name,
				tmp_parameter_3_value,
				tmp_parameter_4_name,
				tmp_parameter_4_value,
				tmp_parameter_5_name,
				tmp_parameter_5_value,
				tmp_parameter_6_name,
				tmp_parameter_6_value);
		if(reference.getDiscount_label().isVisible() && !reference.getDiscount_label().getText().equals("") && !reference.getSelectDiscountCombo().getSelectedItem().equals(reference.getDEFAULT())){
			bso.setDiscount_name(reference.getDiscount_label().getText());
			bso.setDiscount_value(tmp_Discount_value);
		}
		
		if(reference.getRadioGroupChangeCug().getSelection()!=null){
			bso.setCug_action(reference.getRadioGroupChangeCug().getSelection().getActionCommand());
		}
		return bso;
	}
	
	private String technicalOptionManagment(String service){
		if(service.startsWith("SET")){
			return service.replaceFirst("SET-", "");
		}
		else if(service.startsWith("GET")){
			return service.replaceFirst("GET-", "");
		}
		else{
			return service;
		}
	}
	
	private void saveSessionInfo(){
		if (!components.getConnectionManagerStandard().getSession().getCurrent_msisdn().equals(reference.getMsisdn_field().getText())) {
			components.getConnectionManagerStandard().getSession().setCurrent_msisdn(reference.getMsisdn_field().getText());
			components.getMsisdnCompleter().store(reference.getMsisdn_field().getText());
		}
		if(!components.getConnectionManagerStandard().getSession().getCurrent_market().equals(reference.getRadioGroupMarket().getSelection().getActionCommand())){
			components.getConnectionManagerStandard().getSession().setCurrent_market(reference.getRadioGroupMarket().getSelection().getActionCommand());
		}
		if(!components.getConnectionManagerStandard().getSession().getCurrent_ctype().equals(reference.getRadioGroupCtype().getSelection().getActionCommand())){
			components.getConnectionManagerStandard().getSession().setCurrent_ctype(reference.getRadioGroupCtype().getSelection().getActionCommand());
		}
	}
	
	public void setReference(BsoResourceInterface reference) {
		this.reference = reference;
	}

	public void setComponents(DAPBeanInterface components) {
		this.components = components;
	}

	public void setAction_bso(ActionCommandBSOInterface action_bso) {
		this.action_bso = action_bso;
	}
}
