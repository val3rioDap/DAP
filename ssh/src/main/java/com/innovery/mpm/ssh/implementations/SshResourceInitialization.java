package com.innovery.mpm.ssh.implementations;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;
import com.innovery.mpm.ssh.implementations.gui.SshResource;
import com.innovery.mpm.ssh.implementations.gui.util.SshResourceUtil;
import com.innovery.mpm.ssh.implementations.gui.util.SshResourceResponseGridManager;
import com.innovery.mpm.ssh.implementations.gui.util.SshResourceResponseManager;
import com.innovery.mpm.ssh.implementations.gui.verification.SshResourceVerify;
import com.innovery.mpm.ssh.interfaces.SshResourceInitializationInterface;
import com.innovery.mpm.ssh.interfaces.gui.SshResourceInterface;
import com.innovery.mpm.ssh.interfaces.gui.util.SshResourceUtilInterface;
import com.innovery.mpm.ssh.interfaces.gui.util.SshResourceResponseGridManagerInterface;
import com.innovery.mpm.ssh.interfaces.gui.util.SshResourceResponseManagerInterface;
import com.innovery.mpm.ssh.interfaces.gui.verification.SshResourceVerifyInterface;

public class SshResourceInitialization implements SshResourceInitializationInterface {
	
	private SshResourceInterface ssh_resource;

	public SshResourceInitialization(DAPBeanInterface components){
		
		SshResourceResponseManagerInterface responseManagment = new SshResourceResponseManager();
		SshResourceUtilInterface util = new SshResourceUtil();
		SshResourceResponseGridManagerInterface grid_managment = new SshResourceResponseGridManager();
		SshResourceVerifyInterface response_verify = new SshResourceVerify();
		
		responseManagment.setComponents(components);
		util.setComponents(components);
		grid_managment.setComponents(components);
		response_verify.setComponents(components);
		
		ssh_resource = new SshResource();
		
		ssh_resource.setSessionStandard(components.getConnectionManagerStandard().getSession());
		ssh_resource.setCommonGUI(components.getCommonGUI());
		ssh_resource.setAutocompleteMsisdn(components.getMsisdnCompleter());
		ssh_resource.setLog(components.getLogger());
		
		ssh_resource.setGrid_managment(grid_managment);
		ssh_resource.setUtil(util);
		ssh_resource.setResponseManagment(responseManagment);
		ssh_resource.setResponse_verify(response_verify);
	}
	
	public void initialize(){
		ssh_resource.initialize();
	}
	
	public JFrame getFrame(){
		return ssh_resource.getFrame();
	}
	
	public JButton getBtnExecute(){
		return ssh_resource.getBtnExecute();
	}
}
