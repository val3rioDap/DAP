package com.innovery.mpm.af.implementations.gui.verification;

import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.innovery.mpm.af.implementations.util.AFActions;
import com.innovery.mpm.af.implementations.util.AFMessages;
import com.innovery.mpm.af.interfaces.gui.verification.AccountFinderVerifyInterface;
import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;

public class AccountFinderVerify implements AccountFinderVerifyInterface{
	
	private DAPBeanInterface components;
	
	private JTextField msisdnField;
	private ButtonGroup actionButtonGroup;
	private ButtonGroup marketButtonGroup;
	private JComboBox sdpComboboxSelection;
	
	public boolean verify(){
		
		String msisdn_pattern = "^[0-9]{1,28}$";
		
		if(actionButtonGroup.getSelection()==null){
			components.getNotifications().error(AFMessages.AF_ACTION_WARNING);
			return false;
		}
		if(marketButtonGroup.getSelection()==null){
			components.getNotifications().error(AFMessages.AF_MARKET_WARNING);
			return false;
		}
		else if(!Pattern.matches(msisdn_pattern, msisdnField.getText())){
			components.getNotifications().error(AFMessages.AF_MSISDN_WARNING);
			return false;
		}
		
		else if(actionButtonGroup.getSelection().getActionCommand().equals(AFActions.AF_ACTION_CREATE)){
			if(sdpComboboxSelection.getSelectedIndex()==0){
				components.getNotifications().error(AFMessages.AF_SDP_WARNING);
				return false;
			}
		}
		else if(actionButtonGroup.getSelection().getActionCommand().equals(AFActions.AF_ACTION_DELETE) || 
				actionButtonGroup.getSelection().getActionCommand().equals(AFActions.AF_ACTION_GET)){
			return true;
		}
		return true;
	}

	public void setComponents(DAPBeanInterface components) {
		this.components = components;
	}

	public void setMsisdnField(JTextField msisdnField) {
		this.msisdnField = msisdnField;
	}

	public void setActionButtonGroup(ButtonGroup actionButtonGroup) {
		this.actionButtonGroup = actionButtonGroup;
	}

	public void setMarketButtonGroup(ButtonGroup marketButtonGroup) {
		this.marketButtonGroup = marketButtonGroup;
	}

	public void setSdpComboboxSelection(JComboBox sdpComboboxSelection) {
		this.sdpComboboxSelection = sdpComboboxSelection;
	}
}
