package com.innovery.mpm.ssh.interfaces.gui;


import javax.swing.JButton;
import javax.swing.JFrame;

import org.apache.log4j.Logger;

import com.innovery.mpm.connection.implementations.util.gui.GUICommonInterface;
import com.innovery.mpm.connection.interfaces.standard.session.SessionStandardInterface;
import com.innovery.mpm.connection.interfaces.util.AutoCompleteMsisdnInterface;
import com.innovery.mpm.ssh.interfaces.gui.util.SshResourceUtilInterface;
import com.innovery.mpm.ssh.interfaces.gui.util.SshResourceResponseGridManagerInterface;
import com.innovery.mpm.ssh.interfaces.gui.util.SshResourceResponseManagerInterface;
import com.innovery.mpm.ssh.interfaces.gui.verification.SshResourceVerifyInterface;

public interface SshResourceInterface {
	
	public void initialize();

	public JFrame getFrame();
	
	public JButton getBtnExecute();

	public void setResponseManagment(SshResourceResponseManagerInterface responseManagment);

	public void setUtil(SshResourceUtilInterface util);

	public void setResponse_verify(SshResourceVerifyInterface response_verify);

	public void setGrid_managment(SshResourceResponseGridManagerInterface grid_managment);

	public void setAutocompleteMsisdn(AutoCompleteMsisdnInterface autocompleteMsisdn);

	public void setCommonGUI(GUICommonInterface commonGUI);

	public void setSessionStandard(SessionStandardInterface sessionStandard);

	public void setLog(Logger log);
}
