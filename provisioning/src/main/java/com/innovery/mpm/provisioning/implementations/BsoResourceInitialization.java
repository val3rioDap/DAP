package com.innovery.mpm.provisioning.implementations;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;
import com.innovery.mpm.provisioning.implementations.action.ActionCommandBSO;
import com.innovery.mpm.provisioning.implementations.gui.BsoResource;
import com.innovery.mpm.provisioning.implementations.gui.BsoResponseGridManagment;
import com.innovery.mpm.provisioning.implementations.gui.util.ProvisioningResponseManagment;
import com.innovery.mpm.provisioning.implementations.gui.util.ProvisioningUtil;
import com.innovery.mpm.provisioning.implementations.gui.verification.BsoResourcesVerify;
import com.innovery.mpm.provisioning.interfaces.BsoResourceInitializationInterface;
import com.innovery.mpm.provisioning.interfaces.action.ActionCommandBSOInterface;
import com.innovery.mpm.provisioning.interfaces.gui.BsoResourceInterface;
import com.innovery.mpm.provisioning.interfaces.gui.BsoResponseGridManagmentInterface;
import com.innovery.mpm.provisioning.interfaces.gui.util.ProvisioningResponseManagmentInterface;
import com.innovery.mpm.provisioning.interfaces.gui.util.ProvisioningUtilInterface;
import com.innovery.mpm.provisioning.interfaces.gui.verification.BsoResourcesVerifyInterface;

public class BsoResourceInitialization implements BsoResourceInitializationInterface {
	
	BsoResourceInterface bso_resources;

	public BsoResourceInitialization(DAPBeanInterface components){
		
		ActionCommandBSOInterface actionBso = new ActionCommandBSO();
		ProvisioningResponseManagmentInterface responseManagment = new ProvisioningResponseManagment();
		BsoResourcesVerifyInterface inputVerifier = new BsoResourcesVerify();
		ProvisioningUtilInterface util = new ProvisioningUtil();
		BsoResponseGridManagmentInterface grid_managment = new BsoResponseGridManagment();
		bso_resources = new BsoResource(components);
		
		actionBso.setComponents(components);
		
		responseManagment.setReference(bso_resources);
		responseManagment.setComponents(components);
		responseManagment.setAction_bso(actionBso);
		
		inputVerifier.setReference(bso_resources);
		inputVerifier.setComponents(components);
		
		util.setReference(bso_resources);
		util.setComponents(components);
		
		grid_managment.setReference(bso_resources);
		grid_managment.setComponents(components);
		
		bso_resources.setResponseManagment(responseManagment);
		bso_resources.setInputVerifier(inputVerifier);
		bso_resources.setUtil(util);
		bso_resources.setGrid_managment(grid_managment);
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