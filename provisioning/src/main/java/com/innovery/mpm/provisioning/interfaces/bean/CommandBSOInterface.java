package com.innovery.mpm.provisioning.interfaces.bean;

public interface CommandBSOInterface {

	public String getMsisdn();

	public String getTrans_id();

	public String getService();

	public String getMarket();

	public String getCtype();

	public String getAction();

	public String getDiscount_name();
	
	public String getDiscount_value();
	
	public void setDiscount_name(String discount_name);

	public void setDiscount_value(String discount_value);
	
	public String getParameter_1_name();
	
	public String getParameter_1_value();
	
	public String getParameter_2_name();
	
	public String getParameter_2_value();
	
	public String getParameter_3_name();
	
	public String getParameter_3_value();
	
	public String getParameter_4_name();
	
	public String getParameter_4_value();
	
	public String getParameter_5_name();
	
	public String getParameter_5_value();
	
	public String getParameter_6_name();
	
	public String getParameter_6_value();
	
	public String getAccount_B();
	
	public String getAccount_B_msisdn();

	public String getAccount_C();
	
	public String getAccount_C_msisdn();
	
	public String getCug_action();

	public void setCug_action(String cug_action);
}
