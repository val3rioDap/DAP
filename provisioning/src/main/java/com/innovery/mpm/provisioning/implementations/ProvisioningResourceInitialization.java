package com.innovery.mpm.provisioning.implementations;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;
import com.innovery.mpm.provisioning.implementations.gui.ProvisioningResource;
import com.innovery.mpm.provisioning.implementations.gui.util.ProvisioningResponseGridManager;
import com.innovery.mpm.provisioning.implementations.gui.util.ProvisioningResponseManager;
import com.innovery.mpm.provisioning.implementations.gui.util.ProvisioningUtil;
import com.innovery.mpm.provisioning.implementations.gui.verification.BsoResourcesVerify;
import com.innovery.mpm.provisioning.interfaces.BsoResourceInitializationInterface;
import com.innovery.mpm.provisioning.interfaces.gui.BsoResourceInterface;
import com.innovery.mpm.provisioning.interfaces.gui.util.BsoResponseGridManagmentInterface;
import com.innovery.mpm.provisioning.interfaces.gui.util.ProvisioningResponseManagmentInterface;
import com.innovery.mpm.provisioning.interfaces.gui.util.ProvisioningUtilInterface;
import com.innovery.mpm.provisioning.interfaces.gui.verification.BsoResourcesVerifyInterface;

public class ProvisioningResourceInitialization implements BsoResourceInitializationInterface {
	
	BsoResourceInterface bso_resources;

	public ProvisioningResourceInitialization(DAPBeanInterface components){
		
		ProvisioningResponseManagmentInterface responseManagment = new ProvisioningResponseManager();
		BsoResourcesVerifyInterface inputVerifier = new BsoResourcesVerify();
		ProvisioningUtilInterface util = new ProvisioningUtil();
		BsoResponseGridManagmentInterface grid_managment = new ProvisioningResponseGridManager();
		
		responseManagment.setComponents(components);
		inputVerifier.setComponents(components);
		util.setComponents(components);
		grid_managment.setComponents(components);
		
		bso_resources = new ProvisioningResource();
		
		bso_resources.setAutocompleteMsisdn(components.getMsisdnCompleter());
		bso_resources.setCommonGUI(components.getCommonGUI());
		bso_resources.setLogger(components.getLogger());
		bso_resources.setSessionStandard(components.getConnectionManagerStandard().getSession());
		
		bso_resources.setResponseManager(responseManagment);
		bso_resources.setInputVerifier(inputVerifier);
		bso_resources.setUtil(util);
		bso_resources.setResponseGridManager(grid_managment);
	}
	
	public void initialize(){
		bso_resources.initialize();
	}
	
	public JFrame getFrame(){
		return bso_resources.getFrame();
	}
	
	public JButton getBtnExecute(){
		return bso_resources.getBtnExecute();
	}
}
