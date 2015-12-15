package com.innovery.mpm.provisioning.implementations.util;

public class PROVISIONINGCommands {

	public static final String PROVISIONING_COMMAND_SET = "SET:SRVSUB";
	public static final String PROVISIONING_COMMAND_GET = "GET:SRVSUB";
	public static final String PROVISIONING_COMMAND_TRANSID = ":TRANSID,";
	public static final String PROVISIONING_COMMAND_MSISDN = ":MSISDN,";
	public static final String PROVISIONING_COMMAND_SERVICE = ":SERVICE,";
	public static final String PROVISIONING_COMMAND_MARKET = ":MARKET,";
	public static final String PROVISIONING_COMMAND_CTYPE = ":CTYPE,";
	
	public static final String PROVISIONING_COMMAND_ACTION_IN = ":ACTION,in";
	public static final String PROVISIONING_COMMAND_ACTION_OUT = ":ACTION,out";
	public static final String PROVISIONING_COMMAND_ACTION_BOOKOUT = ":ACTION,book_out";
	public static final String PROVISIONING_COMMAND_ACTION_CHG = ":ACTION,chg";
	
	public static final String PROVISIONING_COMMAND_PARAMETER = ":";
	public static final String PROVISIONING_COMMAND_VALUE = ",";
	
	public static final String PROVISIONING_COMMAND_GENERIC_ACCOUNTB = "ACCOUNTB,MSISDN,<msisdn>,CTYPE,<type>";
	public static final String PROVISIONING_COMMAND_GENERIC_ACCOUNTC = "ACCOUNTC,MSISDN,<msisdn>,CTYPE,<type>";
	
	public static final String PROVISIONING_COMMAND_ACCOUNTB = "ACCOUNTB";
	public static final String PROVISIONING_COMMAND_CTYPEB = "CTYPEB";
	
	public static final String PROVISIONING_COMMAND_ACCOUNTC = "ACCOUNTC";
	public static final String PROVISIONING_COMMAND_CTYPEC = "CTYPEC";
	
	
	public static final String PROVISIONING_COMMAND_ACCOUNTS = ":ACCOUNTS,";
	
	public static final String PROVISIONING_COMMAND_END = ";";
}
