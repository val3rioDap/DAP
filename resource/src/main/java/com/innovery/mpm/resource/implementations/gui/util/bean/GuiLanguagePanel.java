package com.innovery.mpm.resource.implementations.gui.util.bean;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;
import com.innovery.mpm.resource.implementations.util.AIRActions;

public class GuiLanguagePanel extends GuiResourcePanelInterface{

	private DAPBeanInterface components;
	
	private JRadioButton rdbtnGet;
	private JRadioButton rdbtnUpdate;
	
	private JLabel lang_label;
	private JComboBox lang_combo;
	
	public final static String DEFAULT = "Select language";
	public final static String IT = "Italiano";
	public final static String EN = "English";
	
	private String[] lang_list = {DEFAULT, IT, EN};
	
	public GuiLanguagePanel(DAPBeanInterface components, JRadioButton rdbtnGet, JRadioButton rdbtnUpdate){
		this.components = components;
		this.rdbtnGet = rdbtnGet;
		this.rdbtnUpdate = rdbtnUpdate;
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
		setLanguageLabel();
		setLanguageCombo();
		setAction();
	}

	private void setAction(){
		rdbtnGet.setEnabled(true);
		rdbtnUpdate.setEnabled(true);
	}
	
	private void setLanguageLabel(){
		lang_label = new JLabel("Language new");
		lang_label.setHorizontalAlignment(SwingConstants.RIGHT);
		lang_label.setVisible(false);
		add(lang_label, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 5, 0, 5, 0, 0));
	}
	
	private void setLanguageCombo(){
		lang_combo = new JComboBox(lang_list);
		lang_combo.setMaximumRowCount(5);
		lang_combo.setVisible(false);
		add(lang_combo, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 0, 0, 0, 1, 0, 16));
	}
	
	public JComboBox getLang_combo() {
		return lang_combo;
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
		
		else if(action.equals(AIRActions.AIR_ACTION_SET)){
			this.actionUpdate();
		}
	}
	
	private void actionCreate(){
		/* No action */
	}
	
	private void actionDelete(){
		/* No action */
	}
	
	private void actionGet(){
		lang_label.setText("Language new");
		lang_label.setVisible(false);
		this.reset();
		lang_combo.setVisible(false);
	}
	
	private void actionUpdate(){
		lang_label.setText("Language new*");
		lang_label.setVisible(true);
		this.reset();
		lang_combo.setVisible(true);
	}
	
	private void reset(){
		lang_combo.setSelectedIndex(0);
	}
}
