package com.innovery.mpm.af.interfaces.gui;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.apache.log4j.Logger;

import com.innovery.mpm.af.interfaces.gui.util.AccountFinderResponseGridManagerInterface;
import com.innovery.mpm.af.interfaces.gui.util.AccountFinderResponseManagerInterface;
import com.innovery.mpm.af.interfaces.gui.util.AccountFinderUtilInterface;
import com.innovery.mpm.af.interfaces.gui.verification.AccountFinderVerifyInterface;
import com.innovery.mpm.connection.implementations.util.gui.GUICommonInterface;
import com.innovery.mpm.connection.interfaces.standard.session.SessionStandardInterface;
import com.innovery.mpm.connection.interfaces.util.AutoCompleteMsisdnInterface;

public interface AccountFinderInterface {
	
	public void initialize();
	
	public JFrame getFrame();

	public JButton getBtnExecute();

	public void resetResponseGrid();
	
	public void setVerification(AccountFinderVerifyInterface verification);

	public void setUtil(AccountFinderUtilInterface util);

	public void setResponseManager(AccountFinderResponseManagerInterface responseManager);

	public void setResponseGridManager(AccountFinderResponseGridManagerInterface responseGridManager);
	
	public void setCommonGUI(GUICommonInterface commonGUI);
	
	public void setSessionStandard(SessionStandardInterface sessionStandard);
	
	public void setLogger(Logger logger);

	public void setMsisdnCompleter(AutoCompleteMsisdnInterface msisdnCompleter);
}
