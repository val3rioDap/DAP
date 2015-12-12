package com.innovery.mpm.resource.implementations;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;
import com.innovery.mpm.resource.implementations.gui.AirResource;
import com.innovery.mpm.resource.implementations.gui.util.AirResourceResponseGridManager;
import com.innovery.mpm.resource.implementations.gui.util.AirResourceResponseManager;
import com.innovery.mpm.resource.implementations.gui.util.AirResourceUtil;
import com.innovery.mpm.resource.implementations.gui.verification.AirResourceVerify;
import com.innovery.mpm.resource.interfaces.AirResourceInitializationInterface;
import com.innovery.mpm.resource.interfaces.gui.AirResourceInterface;
import com.innovery.mpm.resource.interfaces.gui.util.AirResourceResponseManagerInterface;
import com.innovery.mpm.resource.interfaces.gui.util.AirResourceUtilInterface;
import com.innovery.mpm.resource.interfaces.gui.util.AirResourceResponseGridManagerInterface;
import com.innovery.mpm.resource.interfaces.gui.verification.AirResourceVerifyInterface;

public class AirResourceInitialization implements AirResourceInitializationInterface {
	
	AirResourceInterface air_resource;

	public AirResourceInitialization(DAPBeanInterface components){
		
		AirResourceUtilInterface util = new AirResourceUtil();
		AirResourceResponseManagerInterface responseManager = new AirResourceResponseManager();
		AirResourceResponseGridManagerInterface grid_managment = new AirResourceResponseGridManager();
		AirResourceVerifyInterface resourceVerifier = new AirResourceVerify();
		
		util.setComponents(components);
		responseManager.setComponents(components);
		grid_managment.setComponents(components);
		resourceVerifier.setComponents(components);
		
		air_resource = new AirResource();
		
		air_resource.setCommonGUI(components.getCommonGUI());
		air_resource.setSessionStandard(components.getConnectionManagerStandard().getSession());
		air_resource.setAutocompleteMsisdn(components.getMsisdnCompleter());
		
		air_resource.setUtil(util);
		air_resource.setResponseManager(responseManager);
		air_resource.setResponseGridManager(grid_managment);
		air_resource.setResourceVerifier(resourceVerifier);
	}
	
	public void initialize(){
		air_resource.initialize();
	}

	public JFrame getFrame(){
		return air_resource.getFrame();
	}
	
	public JButton getBtnExecute(){
		return air_resource.getBtnExecute();
	}
}
