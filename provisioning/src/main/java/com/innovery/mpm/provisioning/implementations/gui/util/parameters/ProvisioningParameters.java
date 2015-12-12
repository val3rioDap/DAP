package com.innovery.mpm.provisioning.implementations.gui.util.parameters;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;
import com.innovery.mpm.provisioning.implementations.util.OPTION_FAMILY;
import com.innovery.mpm.provisioning.implementations.util.PROVISIONINGActions;
import com.innovery.mpm.provisioning.interfaces.gui.BsoResourceInterface;
import com.innovery.mpm.provisioning.interfaces.gui.util.parameters.ProvisioningParametersInterface;

public class ProvisioningParameters implements ProvisioningParametersInterface {

	private BsoResourceInterface reference;
	
	public void enableParameterDiscount(int i){
		reference.getDiscount_label().setVisible(true);
		reference.getSelectDiscountCombo().setVisible(true);
		
		reference.getDiscount_label().setText(reference.getLabelList().get(i).getText());
		
		String discount_list[] = reference.getInputParametersRegex().get(i).split("\\|");
		reference.getSelectDiscountCombo().removeAllItems();
		
		if(((String) reference.getSelectOptionCombo().getSelectedItem()).equals(discount_list[0])){
			reference.getSelectDiscountCombo().addItem(discount_list[0]);
		} else {
			reference.getSelectDiscountCombo().addItem(reference.getDEFAULT());
			for(int k=0; k<discount_list.length; k++){
				reference.getSelectDiscountCombo().addItem(discount_list[k]);
			}
		}
	}
	
	public void enableOtherParameters(int i){
		if(reference.getLabelList().get(i).getText().contains("FEEDELAY")){
			reference.getFieldList().get(i).setText("0");
			if(reference.getRadioGroupCtype().getSelection().getActionCommand().equals(DAPBeanInterface.POSTPAID)){
				reference.getFieldList().get(i).setEditable(false);
			}
			else if(reference.getRadioGroupCtype().getSelection().getActionCommand().equals(DAPBeanInterface.PREPAID)){
				reference.getFieldList().get(i).setEditable(true);
			}
		}
		
		reference.getLabelList().get(i).setVisible(true);
		reference.getFieldList().get(i).setVisible(true);
	}
	
	public void enableTechnicalOptionParameters(){
		if(reference.getSelectOptionCombo().getSelectedItem().equals(OPTION_FAMILY.CUG)){
			if(reference.getRadioGroupAction().getSelection().getActionCommand().equals(PROVISIONINGActions.PROVISIONING_ACTION_CHG)){
				reference.getCug_action_label().setVisible(true); 
				reference.getRadioGroupChangeCug_panel().setVisible(true);
				reference.getParameter_1_field().setVisible(true);
				reference.getParameter_1_label().setVisible(true);
				reference.getParameter_1_label().setText("ACCOUNTS");
			}
		}
	}

	public void setReference(BsoResourceInterface reference) {
		this.reference = reference;
	}
}
