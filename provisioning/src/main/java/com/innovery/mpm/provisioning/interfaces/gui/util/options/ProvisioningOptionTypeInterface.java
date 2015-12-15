package com.innovery.mpm.provisioning.interfaces.gui.util.options;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;

public interface ProvisioningOptionTypeInterface {

	void setOptionComboboxSelection(JComboBox optionComboboxSelection);

	void setRdbtnGet(JRadioButton rdbtnGet);

	void setRdbtnChange(JRadioButton rdbtnChange);

	void setRdbtnBookOut(JRadioButton rdbtnBookOut);

	void setRdbtnOut(JRadioButton rdbtnOut);

	void setRdbtnIn(JRadioButton rdbtnIn);

	void setComponents(DAPBeanInterface components);

	void enableTariffPlan();

	void enablePostPone();

	void enableDiscount();

	void enableMaster(String actions);

}
