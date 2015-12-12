package com.innovery.mpm.ssh.interfaces.gui.util;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;

public interface SshResourceResponseManagerInterface {

	public void getResponse();
	
	public void setComponents(DAPBeanInterface components);
	
	public void setGridManager(SshResourceResponseGridManagerInterface gridManager);

	public void setTextArea(JTextArea textArea);

	public void setBtnExecute(JButton btnExecute) ;

	public void setMsisdnField(JTextField msisdnField);

	public void setServiceCodeField(JTextField serviceCodeField);

	public void setServiceCodeDateField(JTextField serviceCodeDateField);

	public void setSmartDateSelected(JCheckBox smartDateSelected);

	public void setSmartDateField(JTextField smartDateField) ;

	public void setActionButtonGroup(ButtonGroup actionButtonGroup);

	public void setMarketButtonGroup(ButtonGroup marketButtonGroup);
}
