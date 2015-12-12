package com.innovery.mpm.resource.implementations.gui.util.bean;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;
import com.innovery.mpm.resource.implementations.util.AIRActions;

public class GuiFafPanel extends GuiResourcePanelInterface{

	private DAPBeanInterface components;
	
	private JRadioButton rdbtnCreate;
	private JRadioButton rdbtnDelete;
	private JRadioButton rdbtnGet;
	
	private JLabel faf_num_label;
	private JTextField faf_num_text;
	
	private JLabel faf_k_label;
	private JTextField faf_k_text;
	
	public GuiFafPanel(DAPBeanInterface components, JRadioButton rdbtnCreate, JRadioButton rdbtnDelete, JRadioButton rdbtnGet){
		this.components = components;
		this.rdbtnCreate = rdbtnCreate;
		this.rdbtnDelete = rdbtnDelete;
		this.rdbtnGet = rdbtnGet;
		initialize();
		
	}
	
	private void setPanelDimension() {
		setSize(711, 450);
		GridBagLayout gbl_resources_panel = new GridBagLayout();
		gbl_resources_panel.columnWidths = new int[] { 260, 278, 0};
		gbl_resources_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_resources_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_resources_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,	0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gbl_resources_panel);
	}
	
	private void initialize() {
		setPanelDimension();
		setFafNumLabel();
		setFafNumText();
		setFafIndicatorLabel();
		setFafIndicatorText();
		setAction();
	}
	
	private void setAction(){
		rdbtnCreate.setEnabled(true);
		rdbtnDelete.setEnabled(true);
		rdbtnGet.setEnabled(true);
	}
	
	private void setFafNumLabel(){
		faf_num_label = new JLabel("Faf number");
		faf_num_label.setHorizontalAlignment(SwingConstants.RIGHT);
		faf_num_label.setVisible(false);
		add(faf_num_label, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 5, 0, 5, 0, 0));
	}
	
	private void setFafNumText(){
		faf_num_text = new JTextField();
		faf_num_text.setColumns(10);
		faf_num_text.setVisible(false);
		add(faf_num_text, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 0, 0, 0, 1, 0));
	}
	
	private void setFafIndicatorLabel(){
		faf_k_label = new JLabel("Faf K-indicator");
		faf_k_label.setHorizontalAlignment(SwingConstants.RIGHT);
		faf_k_label.setVisible(false);
		add(faf_k_label, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 5, 0, 5, 0, 1));
	}
	
	private void setFafIndicatorText(){

		faf_k_text = new JTextField();
		faf_k_text.setColumns(10);
		faf_k_text.setVisible(false);
		add(faf_k_text, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 0, 0, 0, 1, 1));
	}
	
	public JTextField getNumberText(){
		return faf_num_text;
	}
	
	public JTextField getKindText(){
		return faf_k_text;
	}
	
	public void setPanelAction(String action){
		if(action.equals(AIRActions.AIR_ACTION_CREATE)){
			this.actionCreate();
		}
		
		else if(action.equals(AIRActions.AIR_ACTION_DELETE)){
			this.actionDelete();
		}
		
		else if(action.equals(AIRActions.AIR_ACTION_GET)){
			this.actionGet();
		}
	}
	
	private void actionCreate(){
		faf_num_label.setText("Faf number*");
		faf_k_label.setText("Faf K-indicator*");
		
		faf_num_label.setVisible(true);
		faf_k_label.setVisible(true);
		
		this.reset();
		
		faf_num_text.setVisible(true);
		faf_k_text.setVisible(true);
	}
	
	private void actionDelete(){
		faf_num_label.setText("Faf number*");
		faf_k_label.setText("Faf K-indicator");
		
		faf_num_label.setVisible(true);
		faf_k_label.setVisible(true);
		
		this.reset();
		
		faf_num_text.setVisible(true);
		faf_k_text.setVisible(true);
	}
	
	private void actionGet(){
		faf_num_label.setText("Faf number");
		faf_k_label.setText("Faf K-indicator");
		
		faf_num_label.setVisible(false);
		faf_k_label.setVisible(false);
		
		this.reset();
		
		faf_num_text.setVisible(false);
		faf_k_text.setVisible(false);
	}
	
	private void reset(){
		faf_num_text.setText("");
		faf_k_text.setText("");
	}
}
