package com.innovery.mpm.resource.interfaces.gui.util;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;
import com.innovery.mpm.resource.implementations.gui.util.bean.GuiResourcePanelInterface;

public interface AirResourceUtilInterface {

	void hideOne(String resourceToHide);

	void showSelectedResource(String resource);

	void actionResourceCall(String action);

	void backButtonActionPerformed();

	public void setComponents(DAPBeanInterface components);
	
	public GuiResourcePanelInterface getPanelGui();

	void setGridManger(AirResourceResponseGridManagerInterface gridManger);

	void setFrame(JFrame frame);

	void setTextArea(JTextArea textArea);

	void setBtnExecute(JButton btnExecute);

	void setRdbtnRun(JRadioButton rdbtnRun);

	void setRdbtnUpdate(JRadioButton rdbtnUpdate);

	void setRdbtnGet(JRadioButton rdbtnGet);

	void setRdbtnDelete(JRadioButton rdbtnDelete);

	void setRdbtnCreate(JRadioButton rdbtnCreate);

	void setActionButtonGroup(ButtonGroup actionButtonGroup);

	void setVoidResourcePanel(JPanel voidResourcePanel);

	void setCaiCommandField(JPanel caiCommandField);
}