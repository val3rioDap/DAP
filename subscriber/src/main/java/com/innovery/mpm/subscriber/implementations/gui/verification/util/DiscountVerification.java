package com.innovery.mpm.subscriber.implementations.gui.verification.util;

import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;

public class DiscountVerification{

	private DAPBeanInterface components;

	private JComboBox actionComboboxSelection;
	private ButtonGroup marketButtonGroup;
	
	private JTextField feediscountField;
	private JTextField feedelayField;

	public boolean execute() {
		String cons_patt = "^(ROPZ)[0-9]{3,7}[A-Za-z]{0,1}$";
		String corp_patt = "^(OPTBE|OPT)[0-9]{2,7}$";
		
		if (actionComboboxSelection.getSelectedIndex() == 0) {
			components.getNotifications().error("Select an action for discount change.");
			return false;
		}

		else if ((marketButtonGroup.getSelection().getActionCommand()).equals(DAPBeanInterface.CONSUMER) && !Pattern.matches(cons_patt, feediscountField.getText())) {
			components.getNotifications().error("Feediscount not valid for \"consumer\".It must be ROPZxxxx.");
			return false;
		} 
		
		else if ((marketButtonGroup.getSelection().getActionCommand()).equals(DAPBeanInterface.CORPORATE) && !Pattern.matches(corp_patt, feediscountField.getText())) {
			components.getNotifications().error("Feediscount not valid for \"corporate\".It must be OPTxxxx.");
			return false;
		} 
		
		else if (!feedelayField.getText().equals("") && !isNumber(feedelayField.getText())) {
			components.getNotifications().error("Feedelay not valid. It must be positive number. or 0.");
			return false;
		}
		return true;
	}

	private boolean isNumber(String number) {

		try {
			Integer.parseInt(number);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void setComponents(DAPBeanInterface components) {
		this.components = components;
	}

	public void setActionComboboxSelection(JComboBox actionComboboxSelection) {
		this.actionComboboxSelection = actionComboboxSelection;
	}

	public void setMarketButtonGroup(ButtonGroup marketButtonGroup) {
		this.marketButtonGroup = marketButtonGroup;
	}

	public void setFeediscountField(JTextField feediscountField) {
		this.feediscountField = feediscountField;
	}

	public void setFeedelayField(JTextField feedelayField) {
		this.feedelayField = feedelayField;
	}
}
