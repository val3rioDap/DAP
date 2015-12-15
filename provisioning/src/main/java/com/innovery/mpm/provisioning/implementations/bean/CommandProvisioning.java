package com.innovery.mpm.provisioning.implementations.bean;

import java.util.ArrayList;
import java.util.List;

import com.innovery.mpm.provisioning.implementations.util.PROVISIONINGCommands;
import com.innovery.mpm.provisioning.interfaces.bean.CommandBSOInterface;

public class CommandProvisioning implements CommandBSOInterface{

	private String msisdn;
	private String trans_id;
	private String service;
	private String market;
	private String ctype;
	private String action;

	private final String account_B_parameter = PROVISIONINGCommands.PROVISIONING_COMMAND_GENERIC_ACCOUNTB;
	private String account_B_msisdn;
	private String account_B_type;
	private String account_B;

	private final String account_C_parameter = PROVISIONINGCommands.PROVISIONING_COMMAND_GENERIC_ACCOUNTC;
	private String account_C_msisdn;
	private String account_C_type;
	private String account_C;
	
	private String cug_action;
	
	private String discount_name;
	private String discount_value;

	private String parameter_1_name;
	private String parameter_1_value;

	private String parameter_2_name;
	private String parameter_2_value;

	private String parameter_3_name;
	private String parameter_3_value;

	private String parameter_4_name;
	private String parameter_4_value;

	private String parameter_5_name;
	private String parameter_5_value;
	
	private String parameter_6_name;
	private String parameter_6_value;
	
	private List<String> parametersNameList;
	private List<String> parametersValueList;
	
	public CommandProvisioning() {

		this.account_B_msisdn = "";
		this.account_B_type = "";
		this.account_B = "";
		this.account_C_msisdn = "";
		this.account_C_type = "";
		this.account_C = "";
		
		this.cug_action = "";
		
		this.discount_name = "";
		this.discount_value = "";
		this.parametersNameList = new ArrayList<String>();
		this.parametersValueList = new ArrayList<String>();
		
		verifyParametersForAccountB();
	}

	public String getMsisdn() {
		return msisdn;
	}

	public String getTrans_id() {
		return trans_id;
	}
	
	@Override
	public void setTrans_id(String trans_id) {
		this.trans_id = trans_id;
	}

	public String getService() {
		return service;
	}

	public String getMarket() {
		return market;
	}

	public String getCtype() {
		return ctype;
	}

	public String getAction() {
		return action;
	}
	
	public String getCug_action() {
		return cug_action;
	}

	public void setCug_action(String cug_action) {
		this.cug_action = cug_action;
	}

	public String getDiscount_name() {
		return discount_name.replace("*", "");
	}

	public void setDiscount_name(String discount_name) {
		this.discount_name = discount_name;
	}

	public String getDiscount_value() {
		return discount_value;
	}

	public void setDiscount_value(String discount_value) {
		this.discount_value = discount_value;
	}
	
	public String getParameter_1_name() {
		return parameter_1_name.replace("*", "");
	}
	
	public String getParameter_1_value() {
		return parameter_1_value;
	}
	
	public String getParameter_2_name() {
		return parameter_2_name.replace("*", "");
	}
	
	public String getParameter_2_value() {
		return parameter_2_value;
	}
	
	public String getParameter_3_name() {
		return parameter_3_name.replace("*", "");
	}
	
	public String getParameter_3_value() {
		return parameter_3_value;
	}
	
	public String getParameter_4_name() {
		return parameter_4_name.replace("*", "");
	}
	
	public String getParameter_4_value() {
		return parameter_4_value;
	}
	
	public String getParameter_5_name() {
		return parameter_5_name.replace("*", "");
	}
	
	public String getParameter_5_value() {
		return parameter_5_value;
	}
	
	public String getParameter_6_name() {
		return parameter_6_name.replace("*", "");
	}
	
	public String getParameter_6_value() {
		return parameter_6_value;
	}
	
	private void verifyParametersForAccountB(){
		for(int i=0; i<parametersNameList.size(); i++){
			if(parametersNameList.get(i).contains(PROVISIONINGCommands.PROVISIONING_COMMAND_ACCOUNTB)){
				account_B_msisdn = parametersValueList.get(i);
				resetFieldAccountB(i);
			}
			
			if(parametersNameList.get(i).contains(PROVISIONINGCommands.PROVISIONING_COMMAND_ACCOUNTC)){
				account_C_msisdn = parametersValueList.get(i);
				resetFieldAccountB(i);
			}
			
			if(parametersNameList.get(i).contains(PROVISIONINGCommands.PROVISIONING_COMMAND_CTYPEB)){
				account_B_type = parametersValueList.get(i);
				resetFieldAccountB(i);
			}
			
			if(parametersNameList.get(i).contains(PROVISIONINGCommands.PROVISIONING_COMMAND_CTYPEC)){
				account_C_type = parametersValueList.get(i);
				resetFieldAccountB(i);
			}
		}
	}

	private void resetFieldAccountB(int field){
		switch (field) {
		case 0:
			parameter_1_name = "";
			parameter_1_value = "";
		case 1:
			parameter_2_name = "";
			parameter_2_value = "";
			break;
		case 2:
			parameter_3_name = "";
			parameter_3_value = "";
			break;
		case 3:
			parameter_4_name = "";
			parameter_4_value = "";
			break;
		case 4:
			parameter_5_name = "";
			parameter_5_value = "";
			break;
		case 5:
			parameter_6_name = "";
			parameter_6_value = "";
		break;
		}
	}
	
	public String getAccount_B() {
		if(account_B_type.equals("")){
			account_B = account_B_parameter.replace("<msisdn>", account_B_msisdn).replace(",CTYPE,<type>", "");
		}
		
		else{
			account_B = account_B_parameter.replace("<msisdn>", account_B_msisdn).replace("<type>", account_B_type);
		}
		return account_B;
	}
	public String getAccount_B_msisdn() {
		return account_B_msisdn;
	}

	public String getAccount_C() {
		if(account_C_type.equals("")){
			account_C = account_C_parameter.replace("<msisdn>", account_C_msisdn).replace(",CTYPE,<type>", account_C_type);
		}
		
		else{
			account_C = account_C_parameter.replace("<msisdn>", account_C_msisdn).replace("<type>", account_C_type);
		}
		return account_C;
	}
	public String getAccount_C_msisdn() {
		return account_C_msisdn;
	}

	@Override
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	@Override
	public void setService(String service) {
		this.service = service;
	}

	@Override
	public void setMarket(String market) {
		this.market = market;
	}

	@Override
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	@Override
	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public void setParameter_1_name(String parameter_1_name) {
		this.parameter_1_name = parameter_1_name;
		this.parameter_1_name = parameter_1_name.replace("*", "");
		this.parametersNameList.add(parameter_1_name);
	}

	@Override
	public void setParameter_1_value(String parameter_1_value) {
		this.parameter_1_value = parameter_1_value;
		this.parametersValueList.add(parameter_1_value);
	}

	@Override
	public void setParameter_2_name(String parameter_2_name) {
		this.parameter_2_name = parameter_2_name;
		this.parameter_2_name = parameter_2_name.replace("*", "");
		this.parametersNameList.add(parameter_2_name);
	}

	@Override
	public void setParameter_2_value(String parameter_2_value) {
		this.parameter_2_value = parameter_2_value;
		this.parametersValueList.add(parameter_2_value);
	}

	@Override
	public void setParameter_3_name(String parameter_3_name) {
		this.parameter_3_name = parameter_3_name;
		this.parameter_3_name = parameter_3_name.replace("*", "");
		this.parametersNameList.add(parameter_3_name);
	}

	@Override
	public void setParameter_3_value(String parameter_3_value) {
		this.parameter_3_value = parameter_3_value;
		this.parametersValueList.add(parameter_3_value);
	}

	@Override
	public void setParameter_4_name(String parameter_4_name) {
		this.parameter_4_name = parameter_4_name;
		this.parameter_4_name = parameter_4_name.replace("*", "");
		this.parametersNameList.add(parameter_4_name);
	}

	@Override
	public void setParameter_4_value(String parameter_4_value) {
		this.parameter_4_value = parameter_4_value;
		this.parametersValueList.add(parameter_4_value);
	}

	@Override
	public void setParameter_5_name(String parameter_5_name) {
		this.parameter_5_name = parameter_5_name;
		this.parameter_5_name = parameter_5_name.replace("*", "");
		this.parametersNameList.add(parameter_5_name);
	}

	@Override
	public void setParameter_5_value(String parameter_5_value) {
		this.parameter_5_value = parameter_5_value;
		this.parametersValueList.add(parameter_5_value);
	}

	@Override
	public void setParameter_6_name(String parameter_6_name) {
		this.parameter_6_name = parameter_6_name;
		this.parameter_6_name = parameter_6_name.replace("*", "");
		this.parametersNameList.add(parameter_6_name);
	}

	@Override
	public void setParameter_6_value(String parameter_6_value) {
		this.parameter_6_value = parameter_6_value;
		this.parametersValueList.add(parameter_6_value);
	}
}
