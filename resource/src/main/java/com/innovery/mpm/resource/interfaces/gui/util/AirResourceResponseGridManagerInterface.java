package com.innovery.mpm.resource.interfaces.gui.util;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;

public interface AirResourceResponseGridManagerInterface {

	void setResponseGridAssurance(String response_mpm);

	void resetResponseGrid();

//	void setReference(AirResourceInterface reference);

	public void setComponents(DAPBeanInterface components);

	void setFrame(JFrame frame);

	void setActionButtonGroup(ButtonGroup actionButtonGroup);

	void setResourceComboboxSelection(JComboBox resourceComboboxSelection);

	void setResponseFieldsetGrid(JPanel responseFieldsetGrid);

	void setResponseFieldset(JPanel responseFieldset);
}