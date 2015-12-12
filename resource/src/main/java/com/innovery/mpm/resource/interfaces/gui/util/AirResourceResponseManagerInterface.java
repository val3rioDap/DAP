package com.innovery.mpm.resource.interfaces.gui.util;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;
import com.innovery.mpm.resource.implementations.gui.util.bean.GuiResourcePanelInterface;

public interface AirResourceResponseManagerInterface {

	void setGuiResourcePanel(GuiResourcePanelInterface guiResourcePanel);

	void setResponseGridManager(AirResourceResponseGridManagerInterface responseGridManager);

	void setTextArea(JTextArea textArea);

	void setResourceSelectedComboBox(JComboBox resourceSelectedComboBox);

	void setBtnExecute(JButton btnExecute);

	void setMarketButtonGroup(ButtonGroup marketButtonGroup);

	void setActionButtonGroup(ButtonGroup actionButtonGroup);

	void setMsisdnField(JTextField msisdnField);

	void setRESOURCE_TYPE(String rESOURCE_TYPE);

	void setComponents(DAPBeanInterface components);

	void getResponse();

}