package com.innovery.mpm.provisioning.interfaces.gui;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.apache.log4j.Logger;

import com.innovery.mpm.provisioning.interfaces.gui.util.ProvisioningUtilInterface;
import com.innovery.mpm.provisioning.interfaces.gui.verification.BsoResourcesVerifyInterface;
import com.innovery.mpm.connection.implementations.util.gui.GUICommonInterface;
import com.innovery.mpm.connection.interfaces.standard.session.SessionStandardInterface;
import com.innovery.mpm.connection.interfaces.util.AutoCompleteMsisdnInterface;
import com.innovery.mpm.provisioning.interfaces.gui.util.BsoResponseGridManagmentInterface;
import com.innovery.mpm.provisioning.interfaces.gui.util.ProvisioningResponseManagmentInterface;

public interface BsoResourceInterface {
	
	public void initialize();

	public JFrame getFrame();

	public JButton getBtnExecute();

	public void setResponseManager(ProvisioningResponseManagmentInterface responseManagment);

	public void setInputVerifier(BsoResourcesVerifyInterface inputVerifier);

	public void setUtil(ProvisioningUtilInterface util);

	public void setResponseGridManager(BsoResponseGridManagmentInterface grid_managment);

	void setLogger(Logger logger);

	void setAutocompleteMsisdn(AutoCompleteMsisdnInterface autocompleteMsisdn);

	void setCommonGUI(GUICommonInterface commonGUI);

	void setSessionStandard(SessionStandardInterface sessionStandard);
}
