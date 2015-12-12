package com.innovery.mpm.resource.implementations.gui.util.bean;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;
import com.innovery.mpm.resource.implementations.util.AIRActions;

public class GuiShareAccountPanel extends GuiResourcePanelInterface{

	private static final long serialVersionUID = 1L;
	
	private DAPBeanInterface components;
	
	private JRadioButton rdbtnCreate;
	private JRadioButton rdbtnDelete;
	private JRadioButton rdbtnGet;
	
	private JLabel uc_label;
	private JTextField uc_text;
	
	private JLabel uc_value_label;
	private JTextField uc_value_text;
	
	private JLabel uc_value_adj_label;
	private JTextField uc_value_adj_text;
	
	private JLabel ut_label;
	private JTextField ut_text;
	
	private JLabel ut_value_label;
	private JTextField ut_value_text;
	
	private String action;	
	
	public GuiShareAccountPanel(DAPBeanInterface components, JRadioButton rdbtnCreate, JRadioButton rdbtnDelete, JRadioButton rdbtnGet){
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
		SetUcIdLabel();
		setUcIdText();
		setUcValueAbsLabel();
		setUcValueAbsText();
		setUcValueRelLabel();
		SetUcValueRelText();
		setUtIdLabel();
		setUtIdText();
		setUtValueAbsLabel();
		setUtValueAbsText();
		setAction();
	}
	
	private void setAction(){
		rdbtnCreate.setEnabled(true);
		rdbtnDelete.setEnabled(true);
		rdbtnGet.setEnabled(true);
	}
	
	private void SetUcIdLabel(){
		uc_label = new JLabel("UC id");
		uc_label.setHorizontalAlignment(SwingConstants.RIGHT);
		uc_label.setVisible(false);
		add(uc_label, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 5, 0, 5, 0, 0));
	}
	
	private void setUcIdText(){
		uc_text = new JTextField();
		uc_text.setColumns(10);
		uc_text.setVisible(false);
		add(uc_text, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 0, 0, 0, 1, 0));
	}
	
	private void setUcValueAbsLabel(){
		uc_value_label = new JLabel("UC value");
		uc_value_label.setHorizontalAlignment(SwingConstants.RIGHT);
		uc_value_label.setVisible(false);
		add(uc_value_label, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 5, 0, 5, 0, 1));
	}
	
	private void setUcValueAbsText(){
		uc_value_text = new JTextField();
		uc_value_text.setColumns(10);
		uc_value_text.setVisible(false);
		add(uc_value_text, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 0, 0, 0, 1, 1));
	}
	
	private void setUcValueRelLabel(){
		uc_value_adj_label = new JLabel("UC value adjustment");
		uc_value_adj_label.setHorizontalAlignment(SwingConstants.RIGHT);
		uc_value_adj_label.setVisible(false);
		add(uc_value_adj_label, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 5, 0, 5, 0, 2));
	}
	
	private void SetUcValueRelText(){
		uc_value_adj_text = new JTextField();
		uc_value_adj_text.setColumns(10);
		uc_value_adj_text.setVisible(false);
		add(uc_value_adj_text, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 0, 0, 0, 1, 2));
	}
	
	private void setUtIdLabel(){
		ut_label = new JLabel("UT id");
		ut_label.setHorizontalAlignment(SwingConstants.RIGHT);
		ut_label.setVisible(false);
		add(ut_label, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 5, 0, 5, 0, 3));
	}
	
	private void setUtIdText(){
		ut_text = new JTextField();
		ut_text.setColumns(10);
		ut_text.setVisible(false);
		add(ut_text, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 0, 0, 0, 1, 3));
	}
	
	private void setUtValueAbsLabel(){
		ut_value_label = new JLabel("UT value");
		ut_value_label.setHorizontalAlignment(SwingConstants.RIGHT);
		ut_value_label.setVisible(false);
		add(ut_value_label, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 5, 0, 5, 0, 4));
	}
	
	private void setUtValueAbsText(){
		ut_value_text = new JTextField();
		ut_value_text.setColumns(10);
		ut_value_text.setVisible(false);
		add(ut_value_text, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 0, 0, 0, 1, 4));
	}
	
	public JTextField getUcText(){
		return uc_text;
	}

	public JTextField getUcValueText(){
		return uc_value_text;
	}

	public JTextField getUcValueAdjText(){
		return uc_value_adj_text;
	}

	public JTextField getUtText(){
		return ut_text;
	}

	public JTextField getUtValueText(){
		return ut_value_text;
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
		action = "create";
		uc_label.setText("UC id");
		uc_value_label.setText("UC value");
		uc_value_adj_label.setText("UC value adjustment");
		ut_label.setText("UT id");
		ut_value_label.setText("UT value");
		
		uc_label.setVisible(true);
		uc_value_label.setVisible(false);
		uc_value_adj_label.setVisible(false);
		ut_label.setVisible(true);
		ut_value_label.setVisible(false);
		
		this.reset();
		
		uc_text.setVisible(true);
		uc_value_text.setVisible(false);
		uc_value_adj_text.setVisible(false);
		ut_text.setVisible(true);
		ut_value_text.setVisible(false);
		
		uc_text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				verifyUcFields();
			}
		});
		
		ut_text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				verifyUtFields();
			}
		});
	}
	
	private void actionDelete(){
		action = "delete";
		uc_label.setText("UC id");
		uc_value_label.setText("UC value");
		uc_value_adj_label.setText("UC value adjustment");
		ut_label.setText("UT id*");
		ut_value_label.setText("UT value");
		
		uc_label.setVisible(false);
		uc_value_label.setVisible(false);
		uc_value_adj_label.setVisible(false);
		ut_label.setVisible(true);
		ut_value_label.setVisible(false);
		
		this.reset();
		
		uc_text.setVisible(false);
		uc_value_text.setVisible(false);
		uc_value_adj_text.setVisible(false);
		ut_text.setVisible(true);
		ut_value_text.setVisible(false);
	}
	
	private void actionGet(){
		action = "get";
		uc_label.setText("UC id");
		uc_value_label.setText("UC value");
		uc_value_adj_label.setText("UC value adjustment");
		ut_label.setText("UT id");
		ut_value_label.setText("UT value");
		
		uc_label.setVisible(false);
		uc_value_label.setVisible(false);
		uc_value_adj_label.setVisible(false);
		ut_label.setVisible(false);
		ut_value_label.setVisible(false);
		
		this.reset();
		
		uc_text.setVisible(false);
		uc_value_text.setVisible(false);
		uc_value_adj_text.setVisible(false);
		ut_text.setVisible(false);
		ut_value_text.setVisible(false);
	}

	private void actionUpdate(){
		/*No action*/
	}
	
	private void verifyUcFields(){
		if(uc_text.getText().length()>0){
			uc_label.setText("UC id*");
			uc_value_label.setText("UC value*");
			uc_value_adj_label.setText("UC value adjustment*");
			
			uc_value_label.setVisible(true);
			uc_value_adj_label.setVisible(true);
			
			uc_value_text.setVisible(true);
			uc_value_adj_text.setVisible(true);
			
			uc_value_text.addKeyListener(new KeyAdapter(){
				public void keyReleased(KeyEvent e){
					verifyCreateUcValue();
				}
			});
			
			uc_value_adj_text.addKeyListener(new KeyAdapter(){
				public void keyReleased(KeyEvent e){
					verifyCreateUcValueAdj();
				}
			});
			
		}
		else{
			uc_label.setText("UC id");
			uc_value_label.setText("UC value");
			uc_value_adj_label.setText("UC value adjustment");
			
			uc_value_label.setVisible(false);
			uc_value_adj_label.setVisible(false);
			
			uc_value_text.setText("");
			uc_value_adj_text.setText("");
			
			uc_value_text.setVisible(false);
			uc_value_adj_text.setVisible(false);
		}
	}
	
	private void verifyUtFields(){
		if(ut_text.getText().length()>0 && action.equals("create")){
			ut_label.setText("UT id*");
			ut_value_label.setText("UT value*");
			ut_value_label.setVisible(true);
			ut_value_text.setVisible(true);
		}
		else{
			ut_label.setText("UT id");
			ut_value_label.setText("UT value");
			ut_value_text.setText("");
			ut_value_label.setVisible(false);
			ut_value_text.setVisible(false);
		}
	}
	
	private void verifyCreateUcValue(){
		if(uc_value_text.getText().length()>0){
			uc_value_adj_label.setText("UC value adjustment");
			uc_value_adj_text.setText("");
			uc_value_adj_label.setVisible(false);
			uc_value_adj_text.setVisible(false);
			
		}
		else{
			uc_value_adj_label.setText("UC value adjustment*");
			uc_value_adj_label.setVisible(true);
			uc_value_adj_text.setVisible(true);
		}
	}
	
	private void verifyCreateUcValueAdj(){
		if(uc_value_adj_text.getText().length()>0){
			uc_value_label.setText("UC value");
			uc_value_text.setText("");
			uc_value_label.setVisible(false);
			uc_value_text.setVisible(false);
		}
		else{
			uc_value_label.setText("UC value*");
			uc_value_label.setVisible(true);
			uc_value_text.setVisible(true);
		}
	}
	
	private void reset(){
		uc_text.setText("");
		uc_value_text.setText("");
		uc_value_adj_text.setText("");
		ut_text.setText("");
		ut_value_text.setText("");
	}
}
