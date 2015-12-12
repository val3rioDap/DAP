package com.innovery.mpm.resource.implementations.gui.util;

import java.awt.GridBagConstraints;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;
import com.innovery.mpm.resource.implementations.gui.util.bean.GuiAccountGroupPanel;
import com.innovery.mpm.resource.implementations.gui.util.bean.GuiAccumulatorPanel;
import com.innovery.mpm.resource.implementations.gui.util.bean.GuiCommunityIdPanel;
import com.innovery.mpm.resource.implementations.gui.util.bean.GuiDedicatedAccountPanel;
import com.innovery.mpm.resource.implementations.gui.util.bean.GuiFafPanel;
import com.innovery.mpm.resource.implementations.gui.util.bean.GuiLanguagePanel;
import com.innovery.mpm.resource.implementations.gui.util.bean.GuiMainBalancePanel;
import com.innovery.mpm.resource.implementations.gui.util.bean.GuiOfferIdPanel;
import com.innovery.mpm.resource.implementations.gui.util.bean.GuiPamPanel;
import com.innovery.mpm.resource.implementations.gui.util.bean.GuiPromotionPlanPanel;
import com.innovery.mpm.resource.implementations.gui.util.bean.GuiResourcePanelInterface;
import com.innovery.mpm.resource.implementations.gui.util.bean.GuiSIMExpPanel;
import com.innovery.mpm.resource.implementations.gui.util.bean.GuiSUPExpPanel;
import com.innovery.mpm.resource.implementations.gui.util.bean.GuiServiceClassPanel;
import com.innovery.mpm.resource.implementations.gui.util.bean.GuiServiceOfferingsPanel;
import com.innovery.mpm.resource.implementations.gui.util.bean.GuiShareAccountPanel;
import com.innovery.mpm.resource.implementations.util.AIRActions;
import com.innovery.mpm.resource.interfaces.gui.util.AirResourceUtilInterface;
import com.innovery.mpm.resource.interfaces.gui.util.AirResourceResponseGridManagerInterface;

public class AirResourceUtil implements AirResourceUtilInterface {

	private DAPBeanInterface components;
	
	private JPanel caiCommandField;
	
	private GuiResourcePanelInterface resource_panel;
	private JPanel voidResourcePanel;
	
	private ButtonGroup actionButtonGroup;
	
	private JRadioButton rdbtnCreate;
	private JRadioButton rdbtnDelete;
	private JRadioButton rdbtnGet;
	private JRadioButton rdbtnUpdate;
	private JRadioButton rdbtnRun;
	
	private JButton btnExecute;
	private JTextArea textArea;
	private JFrame frame;
	
	private AirResourceResponseGridManagerInterface gridManger;
	
	public void hideOne(String resourceToHide){
		if(resourceToHide.equals(AIRActions.DEFAULT)){
			caiCommandField.remove(voidResourcePanel);
		}
		
		else {
			caiCommandField.remove(resource_panel);
		}
		
		caiCommandField.revalidate();
		caiCommandField.repaint();
		
		actionButtonGroup.clearSelection();
		
		rdbtnCreate.setEnabled(false);
		rdbtnDelete.setEnabled(false);
		rdbtnGet.setEnabled(false);
		rdbtnUpdate.setEnabled(false);
	}
	
	public void showSelectedResource(String resource){
		
		rdbtnRun.setVisible(false);
		
		if(resource.equals(AIRActions.ACCOUNT_GRUOP_ID)){
			resource_panel = new GuiAccountGroupPanel(components, rdbtnGet, rdbtnUpdate);
		}
		
		else if(resource.equals(AIRActions.ACCUMULATOR)){
			resource_panel = new GuiAccumulatorPanel(components, rdbtnCreate, rdbtnDelete, rdbtnGet, rdbtnUpdate);
		}
		
		else if(resource.equals(AIRActions.COMMUNITY)){
			resource_panel = new GuiCommunityIdPanel(components, rdbtnDelete, rdbtnGet, rdbtnUpdate);
		}
		
		else if(resource.equals(AIRActions.DEDICATED_ACCOUNT)){
			resource_panel = new GuiDedicatedAccountPanel(components, rdbtnCreate, rdbtnDelete, rdbtnGet, rdbtnUpdate);
		}
		
		else if(resource.equals(AIRActions.FAF)){
			resource_panel = new GuiFafPanel(components, rdbtnCreate, rdbtnDelete, rdbtnGet);
		}
		
		else if(resource.equals(AIRActions.LANGUAGE)){
			resource_panel = new GuiLanguagePanel(components, rdbtnGet, rdbtnUpdate);
		}
		
		else if(resource.equals(AIRActions.BALANCE)){
			resource_panel = new GuiMainBalancePanel(components, rdbtnGet, rdbtnUpdate);
		}
		
		else if(resource.equals(AIRActions.OFFER)){
			resource_panel = new GuiOfferIdPanel(components, rdbtnCreate, rdbtnDelete, rdbtnGet);
		}
		
		else if(resource.equals(AIRActions.PAM)){
			resource_panel = new GuiPamPanel(components, rdbtnCreate, rdbtnDelete, rdbtnGet, rdbtnUpdate, rdbtnRun);
			rdbtnRun.setVisible(true);
		}
		
		else if(resource.equals(AIRActions.PROMOTION_PLAN)){
			resource_panel = new GuiPromotionPlanPanel(components, rdbtnCreate, rdbtnDelete, rdbtnGet);
		}
		
		else if(resource.equals(AIRActions.SERVICE_CLASS)){
			resource_panel = new GuiServiceClassPanel(components, rdbtnGet, rdbtnUpdate);
		}
		
		else if(resource.equals(AIRActions.SERVICE_OFFERING)){
			resource_panel = new GuiServiceOfferingsPanel(components, rdbtnCreate, rdbtnDelete, rdbtnGet);
		}
		
		else if(resource.equals(AIRActions.SHARE_ACCOUNT)){
			resource_panel = new GuiShareAccountPanel(components, rdbtnCreate, rdbtnDelete, rdbtnGet);
		}
		
		else if(resource.equals(AIRActions.SIM)){
			resource_panel = new GuiSIMExpPanel(components, rdbtnGet, rdbtnUpdate);
		}
		
		else if(resource.equals(AIRActions.SUP)){
			resource_panel = new GuiSUPExpPanel(components, rdbtnGet, rdbtnUpdate);
		}
		
		caiCommandField.add(resource_panel, components.getCommonGUI().setContraintsGridWidth(GridBagConstraints.BOTH, 2, 0, 0, 0, 0, 0, 4));
		caiCommandField.revalidate();
		caiCommandField.repaint();

		userLevel();
	}
	
	public void actionResourceCall(String action){
		resource_panel.setPanelAction(action);
	}
	
	public void backButtonActionPerformed(){
		if (btnExecute.getActionCommand().equals(AIRActions.AIR_ACTION_RECONNECT)) {
			components.getLogger().info(AIRActions.AIR_ACTION_RECONNECT);
			String redirect;
			if(components.getConnectionManagerCai().getSession().isSessionAvailable()){
				redirect = components.getConnectionManagerCai().reconnectionCai(btnExecute.getActionCommand());
			}
			else{
				redirect = components.getConnectionManagerStandard().reconnectionStandard(btnExecute.getActionCommand());
			}
			textArea.setText(redirect);
			gridManger.setResponseGridAssurance(redirect);
			if (redirect.equals(components.getErrorMessages().get_RELOGGED())) {
				btnExecute.setText(AIRActions.AIR_ACTION_EXECUTE);
				btnExecute.setActionCommand(AIRActions.AIR_ACTION_EXECUTE);
			}else if (redirect.equals(components.getErrorMessages().get_NOT_LOGGED_ERROR()) || 
					  redirect.equals(components.getErrorMessages().get_MPM_CONNECTION_ERROR()) || 
					  redirect.equals(components.getErrorMessages().get_MPM_ERROR())) {
				btnExecute.setText(AIRActions.AIR_ACTION_HOME);
				btnExecute.setActionCommand(AIRActions.AIR_ACTION_HOME);
			}

		} else if (btnExecute.getActionCommand().equals(AIRActions.AIR_ACTION_HOME)){
			components.getLogger().info(AIRActions.AIR_ACTION_HOME);
			frame.dispose();
		}
	}
	
	private void userLevel(){
		if(components.getConnectionManagerStandard().getSession().getUser_level()!=components.getUserLevels().getRoot()){
			rdbtnCreate.setEnabled(false);
			rdbtnDelete.setEnabled(false);
			rdbtnUpdate.setEnabled(false);
			rdbtnRun.setEnabled(false);
		}
	}

	public void setComponents(DAPBeanInterface components) {
		this.components = components;
	}
	
	public GuiResourcePanelInterface getPanelGui(){
		return resource_panel;
	}

	@Override
	public void setCaiCommandField(JPanel caiCommandField) {
		this.caiCommandField = caiCommandField;
	}

	@Override
	public void setVoidResourcePanel(JPanel voidResourcePanel) {
		this.voidResourcePanel = voidResourcePanel;
	}

	@Override
	public void setActionButtonGroup(ButtonGroup actionButtonGroup) {
		this.actionButtonGroup = actionButtonGroup;
	}

	@Override
	public void setRdbtnCreate(JRadioButton rdbtnCreate) {
		this.rdbtnCreate = rdbtnCreate;
	}

	@Override
	public void setRdbtnDelete(JRadioButton rdbtnDelete) {
		this.rdbtnDelete = rdbtnDelete;
	}

	@Override
	public void setRdbtnGet(JRadioButton rdbtnGet) {
		this.rdbtnGet = rdbtnGet;
	}

	@Override
	public void setRdbtnUpdate(JRadioButton rdbtnUpdate) {
		this.rdbtnUpdate = rdbtnUpdate;
	}

	@Override
	public void setRdbtnRun(JRadioButton rdbtnRun) {
		this.rdbtnRun = rdbtnRun;
	}

	@Override
	public void setBtnExecute(JButton btnExecute) {
		this.btnExecute = btnExecute;
	}

	@Override
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	@Override
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void setGridManger(AirResourceResponseGridManagerInterface gridManger) {
		this.gridManger = gridManger;
	}
}
