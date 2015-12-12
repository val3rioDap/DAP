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

public class GuiOfferIdPanel extends GuiResourcePanelInterface{
	
	private DAPBeanInterface components;
	
	private JRadioButton rdbtnCreate;
	private JRadioButton rdbtnDelete;
	private JRadioButton rdbtnGet;
	
	private JLabel offer_id_label;
	private JTextField offer_id_text;
	
	private JLabel offer_type_label;
	private JTextField offer_type_text;
	
	private JLabel offer_start_label;
	private JTextField offer_start_text;
	
	private JLabel offer_start_adj_label;
	private JTextField offer_start_adj_text;
	
	private JLabel offer_end_label;
	private JTextField offer_end_text;
	
	private JLabel offer_end_adj_label;
	private JTextField offer_end_adj_text;
	
	
	public GuiOfferIdPanel(DAPBeanInterface components, JRadioButton rdbtnCreate, JRadioButton rdbtnDelete, JRadioButton rdbtnGet){
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
		setOfferIdLabel();
		setOfferIdText();
		setOfferTypeLabel();
		setOfferTypeText();
		setOfferStartDateAbsLabel();
		setOfferStartDateAbsText();
		setOfferStartDateRelLabel();
		setOfferStartDateRelText();
		setOfferEndDateAbsLabel();
		setOfferEndDateAbsText();
		setOfferEndDateRelLabel();
		setOfferEndDateRelText();
		setAction();
	}
	
	private void setAction(){
		rdbtnCreate.setEnabled(true);
		rdbtnDelete.setEnabled(true);
		rdbtnGet.setEnabled(true);
	}
	
	private void setOfferIdLabel(){
		offer_id_label = new JLabel("Offer id");
		offer_id_label.setHorizontalAlignment(SwingConstants.RIGHT);
		offer_id_label.setVisible(false);
		add(offer_id_label, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 5, 0, 5, 0, 0));
	}
	
	private void setOfferIdText(){
		offer_id_text = new JTextField();
		offer_id_text.setColumns(10);
		offer_id_text.setVisible(false);
		add(offer_id_text, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 0, 0, 0, 1, 0));
	}
	
	private void setOfferTypeLabel(){
		offer_type_label = new JLabel("Offer type");
		offer_type_label.setHorizontalAlignment(SwingConstants.RIGHT);
		offer_type_label.setVisible(false);
		add(offer_type_label, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 5, 0, 5, 0, 1));
	}
	
	private void setOfferTypeText(){
		offer_type_text = new JTextField();
		offer_type_text.setColumns(10);
		offer_type_text.setVisible(false);
		add(offer_type_text, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 0, 0, 0, 1, 1));
	}
	
	private void setOfferStartDateAbsLabel(){
		offer_start_label = new JLabel("Offer start date (YYYY-MM-DD)");
		offer_start_label.setHorizontalAlignment(SwingConstants.RIGHT);
		offer_start_label.setVisible(false);
		add(offer_start_label, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 5, 0, 5, 0, 2));
	}
	
	private void setOfferStartDateAbsText(){
		offer_start_text = new JTextField();
		offer_start_text.setColumns(10);
		offer_start_text.setVisible(false);
		add(offer_start_text, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 0, 0, 0, 1, 2));
	}
	
	private void setOfferStartDateRelLabel(){
		offer_start_adj_label = new JLabel("Offer start date adjustment");
		offer_start_adj_label.setHorizontalAlignment(SwingConstants.RIGHT);
		offer_start_adj_label.setVisible(false);
		add(offer_start_adj_label, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 5, 0, 5, 0, 3));
	}
	
	private void setOfferStartDateRelText(){
		offer_start_adj_text = new JTextField();
		offer_start_adj_text.setColumns(10);
		offer_start_adj_text.setVisible(false);
		add(offer_start_adj_text, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 0, 0, 0, 1, 3));
	}
	
	private void setOfferEndDateAbsLabel(){
		offer_end_label = new JLabel("Offer end date (YYYY-MM-DD)");
		offer_end_label.setHorizontalAlignment(SwingConstants.RIGHT);
		offer_end_label.setVisible(false);
		add(offer_end_label, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 5, 0, 5, 0, 4));
	}
	
	private void setOfferEndDateAbsText(){
		offer_end_text = new JTextField();
		offer_end_text.setColumns(10);
		offer_end_text.setVisible(false);
		add(offer_end_text, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 0, 0, 0, 1, 4));
	}
	
	private void setOfferEndDateRelLabel(){
		offer_end_adj_label = new JLabel("Offer end date adjustment");
		offer_end_adj_label.setHorizontalAlignment(SwingConstants.RIGHT);
		offer_end_adj_label.setVisible(false);
		add(offer_end_adj_label, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 5, 0, 5, 0, 5));
	}
	
	private void setOfferEndDateRelText(){
		offer_end_adj_text = new JTextField();
		offer_end_adj_text.setColumns(10);
		offer_end_adj_text.setVisible(false);
		add(offer_end_adj_text, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 8, 0, 0, 0, 1, 5));
	}
	
	public JTextField getIdText(){
		return offer_id_text;
	}
	
	public JTextField getTypeText(){
		return offer_type_text;
	}

	public JTextField getStartDateText(){
		return offer_start_text;
	}
	
	public JTextField getStartDateAdjText(){
		return offer_start_adj_text;
	}

	public JTextField getEndDateText(){
		return offer_end_text;
	}

	public JTextField getEndDateAdjText(){
		return offer_end_adj_text;
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
		offer_id_label.setText("Offer id*");
		offer_type_label.setText("Offer type");
		offer_start_label.setText("Offer start date (YYYY-MM-DD)");
		offer_start_adj_label.setText("Offer start date adjustment");
		offer_end_label.setText("Offer end date (YYYY-MM-DD)");
		offer_end_adj_label.setText("Offer end date adjustment");
		
		offer_id_label.setVisible(true);
		offer_type_label.setVisible(true);
		offer_start_label.setVisible(true);
		offer_start_adj_label.setVisible(true);
		offer_end_label.setVisible(true);
		offer_end_adj_label.setVisible(true);
		
		this.reset();
		
		offer_id_text.setVisible(true);
		offer_type_text.setVisible(true);
		offer_start_text.setVisible(true);
		offer_start_adj_text.setVisible(true);
		offer_end_text.setVisible(true);
		offer_end_adj_text.setVisible(true);
		
		offer_start_text.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				offer_start_text.setText(components.getDateCompleter().complete(offer_start_text.getText()));
			}
		});
		
		offer_end_text.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				offer_end_text.setText(components.getDateCompleter().complete(offer_end_text.getText()));
			}
		});
		
		offer_start_text.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				verifyStartDate();
			}
		});
		
		offer_start_adj_text.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				verifyStartDateAdj();
			}
		});
		
		
		offer_end_text.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				verifyEndDate();
			}
		});
		
		
		offer_end_adj_text.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				verifyEndDateAdj();
			}
		});
	}
	
	private void actionDelete(){
		offer_id_label.setText("Offer id*");
		offer_type_label.setText("Offer type");
		offer_start_label.setText("Offer start date (YYYY-MM-DD)");
		offer_start_adj_label.setText("Offer start date adjustment");
		offer_end_label.setText("Offer end date (YYYY-MM-DD)");
		offer_end_adj_label.setText("Offer end date adjustment");
		
		offer_id_label.setVisible(true);
		offer_type_label.setVisible(false);
		offer_start_label.setVisible(false);
		offer_start_adj_label.setVisible(false);
		offer_end_label.setVisible(false);
		offer_end_adj_label.setVisible(false);
		
		this.reset();
		
		offer_id_text.setVisible(true);
		offer_type_text.setVisible(false);
		offer_start_text.setVisible(false);
		offer_start_adj_text.setVisible(false);
		offer_end_text.setVisible(false);
		offer_end_adj_text.setVisible(false);
	}
	
	private void actionGet(){
		offer_id_label.setText("Offer id");
		offer_type_label.setText("Offer type");
		offer_start_label.setText("Offer start date (YYYY-MM-DD)");
		offer_start_adj_label.setText("Offer start date adjustment");
		offer_end_label.setText("Offer end date (YYYY-MM-DD)");
		offer_end_adj_label.setText("Offer end date adjustment");
		
		offer_id_label.setVisible(false);
		offer_type_label.setVisible(false);
		offer_start_label.setVisible(false);
		offer_start_adj_label.setVisible(false);
		offer_end_label.setVisible(false);
		offer_end_adj_label.setVisible(false);
		
		this.reset();
		
		offer_id_text.setVisible(false);
		offer_type_text.setVisible(false);
		offer_start_text.setVisible(false);
		offer_start_adj_text.setVisible(false);
		offer_end_text.setVisible(false);
		offer_end_adj_text.setVisible(false);
	}
	
	private void actionUpdate(){
		/* No action */
	}
	
	private void verifyStartDate(){
		if(offer_start_text.getText().length()>0){
			offer_start_adj_text.setText("");
			offer_start_adj_label.setVisible(false);
			offer_start_adj_text.setVisible(false);
		}
		else{
			offer_start_adj_label.setVisible(true);
			offer_start_adj_text.setVisible(true);
		}
	}
	
	private void verifyStartDateAdj(){
		if(offer_start_adj_text.getText().length()>0){
			offer_start_text.setText("");
			offer_start_label.setVisible(false);
			offer_start_text.setVisible(false);
		}
		else{
			offer_start_label.setVisible(true);
			offer_start_text.setVisible(true);
		}
	}
	
	private void verifyEndDate(){
		if(offer_end_text.getText().length()>0){
			offer_end_adj_text.setText("");
			offer_end_adj_label.setVisible(false);
			offer_end_adj_text.setVisible(false);
		}
		else{
			offer_end_adj_label.setVisible(true);
			offer_end_adj_text.setVisible(true);
		}
	}
	
	private void verifyEndDateAdj(){
		if(offer_end_adj_text.getText().length()>0){
			offer_end_text.setText("");
			offer_end_label.setVisible(false);
			offer_end_text.setVisible(false);
		}
		else{
			offer_end_label.setVisible(true);
			offer_end_text.setVisible(true);
		}
	}
	
	private void reset(){
		offer_id_text.setText("");
		offer_type_text.setText("");
		offer_start_text.setText("");
		offer_start_adj_text.setText("");
		offer_end_text.setText("");
		offer_end_adj_text.setText("");
	}
}
