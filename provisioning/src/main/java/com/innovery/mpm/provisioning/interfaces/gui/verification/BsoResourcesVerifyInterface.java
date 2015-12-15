package com.innovery.mpm.provisioning.interfaces.gui.verification;

import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;

public interface BsoResourcesVerifyInterface {

	public boolean verifyOptionalParameters();
	
	public void setComponents(DAPBeanInterface components);

	void setInputParametersRegex(List<String> inputParametersRegex);

	void setLabelList(List<JLabel> labelList);

	void setFieldList(List<JTextField> fieldList);

	void setParametersList(String[] parametersList);

	void setDiscountComboboxSelection(JComboBox discountComboboxSelection);

	void setDiscountLabel(JLabel discountLabel);

	void setOptionComboboxSelection(JComboBox optionComboboxSelection);

	void setCugButtonGroup(ButtonGroup cugButtonGroup);

	void setActionButtonGroup(ButtonGroup actionButtonGroup);

	void setCtypeButtonGroup(ButtonGroup ctypeButtonGroup);

	void setMarketButtonGroup(ButtonGroup marketButtonGroup);

	void setParameterFieldCug(JTextField parameterFieldCug);

	void setMsisdnField(JTextField msisdnField);
}
