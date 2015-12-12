package com.innovery.mpm.provisioning.implementations.gui.verification;

import java.util.regex.Pattern;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;
import com.innovery.mpm.provisioning.implementations.util.OPTION_FAMILY;
import com.innovery.mpm.provisioning.implementations.util.PROVISIONINGActions;
import com.innovery.mpm.provisioning.interfaces.gui.BsoResourceInterface;
import com.innovery.mpm.provisioning.interfaces.gui.verification.BsoResourcesVerifyInterface;

/**
 * 
 * @author Valerio Boschi, Daniela R. Auricchio
 * 
 */
public class BsoResourcesVerify implements BsoResourcesVerifyInterface {
	
	private BsoResourceInterface reference;
	private DAPBeanInterface components;
	
	public boolean verifyOptionalParameters() {
		
		if (!isMsisdn(reference.getMsisdn_field().getText())) {
			components.getNotifications().error("MSISDN is mandatory. Must be numeric, from 1 to 28 digits.");
			return false;
		}
		
		else if (reference.getRadioGroupMarket().getSelection() == null) {
			components.getNotifications().error("Select an MARKET for the promotion");
			return false;
		}
		
		else if(reference.getRadioGroupCtype().getSelection() == null){
			components.getNotifications().error("Select an CTYPE for the promotion");
			return false;
		}
		
		else if(reference.getSelectOptionCombo().getSelectedIndex()==0){
			components.getNotifications().error("Select an option from SERVICE list");
			return false;
		}

		else if (reference.getRadioGroupAction().getSelection() == null) {
			components.getNotifications().error("Select an ACTION for the promotion");
			return false;
		}
		
		else if(!((String) reference.getSelectOptionCombo().getSelectedItem()).contains("ROPZ") && !((String) reference.getSelectOptionCombo().getSelectedItem()).contains("OPT")){
			return verifyTechnicalOption();
		}

		else if(reference.getDiscount_label().isVisible() && reference.getSelectDiscountCombo().isVisible() && reference.getSelectDiscountCombo().getSelectedItem().equals(reference.getDEFAULT())){
			components.getNotifications().error("FEEDISCOUNT is mandatory. Select discount option");
			return false;
		}
		
		else if(reference.getParametersList()!=null){
			for(int i=0; i<reference.getParametersList().length; i++){
				
				if(reference.getFieldList().get(i).isVisible()){
					if(reference.getCommonGUI().isMandatory(reference.getLabelList().get(i).getText())){
						if(reference.getLabelList().get(i).getText().contains("ACCOUNTB") && !reference.getFieldList().get(i).getText().equals("")){
							if(reference.getLabelList().get(i+1).getText().contains("CTYPEB") && reference.getFieldList().get(i+1).getText().equals("")){
								components.getNotifications().error("ACCOUNTB and CTYPEB must both provided");
								return false;
							}
						}
						
						else if(reference.getLabelList().get(i).getText().contains("ACCOUNTC") && !reference.getFieldList().get(i).getText().equals("")){
							if(reference.getLabelList().get(i+1).getText().contains("CTYPEC") && reference.getFieldList().get(i+1).getText().equals("")){
								components.getNotifications().error("ACCOUNTC and CTYPEC must both provided");
								return false;
							}
						}
						
						else if(reference.getLabelList().get(i).getText().contains("CTYPEB") && !reference.getFieldList().get(i).getText().equals("")){
							if(reference.getLabelList().get(i-1).getText().contains("ACCOUNTB") && reference.getFieldList().get(i-1).getText().equals("")){
								components.getNotifications().error("ACCOUNTB and CTYPEB must both provided");
								return false;
							}
						}
						
						else if(reference.getLabelList().get(i).getText().contains("CTYPEC") && !reference.getFieldList().get(i).getText().equals("")){
							if(reference.getLabelList().get(i-1).getText().contains("ACCOUNTC") && reference.getFieldList().get(i-1).getText().equals("")){
								components.getNotifications().error("ACCOUNTC and CTYPEC must both provided");
								return false;
							}
						}
					}
				}
			
					
				if(reference.getFieldList().get(i).isVisible() && reference.getFieldList().get(i).getText().equals("") && reference.getCommonGUI().isMandatory(reference.getLabelList().get(i).getText())){
					components.getNotifications().error(composeErrorMessage(reference.getLabelList().get(i).getText(), reference.getInputParametersRegex().get(i)));
					return false;
				}
				else if(reference.getFieldList().get(i).isVisible()
						&& !reference.getFieldList().get(i).getText().equals("") && !regularExpression(reference.getInputParametersRegex().get(i), reference.getFieldList().get(i).getText())){
					components.getNotifications().error(composeErrorMessage(reference.getLabelList().get(i).getText(), reference.getInputParametersRegex().get(i)));
					return false;
				}
			
			}
		}
		return true;
	}

	private boolean regularExpression(String extrated_reg_ex, String input_parameter) {	
		if(extrated_reg_ex.contains("|")){
			
			String[] regex_list = extrated_reg_ex.split("\\|");
			for(int i=0; i<regex_list.length; i++){
				if (Pattern.matches(regex_list[i], input_parameter)) {
					return true;
				}
			}
		}
		
		String reg_ex = "^" + extrated_reg_ex + "$";
		if (Pattern.matches(reg_ex, input_parameter)) {
			return true;
		}
		return false;
	}

	private String composeErrorMessage(String parameters, String regex) {
		String msg = parameters.replace("*", "") + " not valid.";
		return msg + " " +regexManagment(parameters, regex);

	}
	
	private String regexManagment(String parameters, String regex){
		String numeric = "^[0-9]*";
		String alfa_numeric = "^[A-Z0-9]*";
		
		if(parameters.contains("DATE") && regex.equals("[0-9]{8}")){
			return "Format date must be YYYYMMDD.";
		}
		
		else if(parameters.contains("FEEDISCOUNT")){
			return "Select FEEDISCOUNT for the option";
		}
		
		else if(!regex.contains("[") && !regex.contains("{")){
			return "\n"+regex.replace("|", "\n");
		}
		
		else if (!Pattern.matches(numeric, regex) && !regex.contains("|")) {
			return " It must be positive number.";
		} 
		
		else if (!Pattern.matches(alfa_numeric, regex) && !regex.contains("|")) {
			return " It must be a string.";
		}
		else if(regex.contains("|")){
			String regex_new = "\n"+regex.replace("|", "\n");
			return regex_new;
		}
		else{
			return regex;
		}
	}
	
	private boolean verifyTechnicalOption(){
		
		if(((String) reference.getSelectOptionCombo().getSelectedItem()).contains(OPTION_FAMILY.CUG) && reference.getRadioGroupAction().getSelection().getActionCommand().equals(PROVISIONINGActions.PROVISIONING_ACTION_CHG)){
			if(reference.getRadioGroupChangeCug().getSelection()==null){
				components.getNotifications().error("Select an action for CUG");
				return  false;
			}
			
			else if(reference.getParameter_1_field().getText().equals("")){
				components.getNotifications().error("\"ACCOUNTS\" is mandatory.");
				return false;
			}
			else if(!isAccounts(reference.getParameter_1_field().getText())){
				components.getNotifications().error("\"ACCOUNTS\" must be one or more MSISDN comma separated.");
				 return false;
			 }
		}
		return true;
	}
	
	private boolean isMsisdn(String msisdn){
		return Pattern.matches("^[0-9]{1,28}$", msisdn);
	}
	
	private boolean isAccounts(String accounts){
		return Pattern.matches("^[0-9]{1,28}(,[0-9]{1,28})*$", accounts);
	}

	public void setReference(BsoResourceInterface reference) {
		this.reference = reference;
	}

	public void setComponents(DAPBeanInterface components) {
		this.components = components;
	}
}
