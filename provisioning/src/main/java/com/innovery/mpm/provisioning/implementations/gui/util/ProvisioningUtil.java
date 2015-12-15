package com.innovery.mpm.provisioning.implementations.gui.util;

import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;
import com.innovery.mpm.provisioning.implementations.gui.util.options.ProvisioningOptionType;
import com.innovery.mpm.provisioning.implementations.gui.util.options.ProvisioningOptionLoader;
import com.innovery.mpm.provisioning.implementations.gui.util.options.ProvisioningParameters;
import com.innovery.mpm.provisioning.implementations.util.OPTION_FAMILY;
import com.innovery.mpm.provisioning.implementations.util.PROVISIONINGActions;
import com.innovery.mpm.provisioning.implementations.xml.XmlScanner;
import com.innovery.mpm.provisioning.interfaces.gui.util.BsoResponseGridManagmentInterface;
import com.innovery.mpm.provisioning.interfaces.gui.util.ProvisioningUtilInterface;
import com.innovery.mpm.provisioning.interfaces.gui.util.options.ProvisioningOptionLoaderInterface;
import com.innovery.mpm.provisioning.interfaces.gui.util.options.ProvisioningOptionTypeInterface;
import com.innovery.mpm.provisioning.interfaces.gui.util.options.ProvisioningParametersInterface;
import com.innovery.mpm.provisioning.interfaces.xml.XmlScannerInterface;

public class ProvisioningUtil implements ProvisioningUtilInterface{

	private DAPBeanInterface components;
	
	private ButtonGroup actionButtonGroup;
	private ButtonGroup marketButtongroup;
	private ButtonGroup ctypeButtonGroup;
	
	private JComboBox optionComboboxSelection;
	
	private JTextArea textArea;
	private JButton btnExecute;
	private JFrame frame;
	
	private JTextField parameter_1_field;
	private JTextField parameter_2_field;
	private JTextField parameter_3_field;
	private JTextField parameter_4_field;
	private JTextField parameter_5_field;
	private JTextField parameter_6_field;
	
	private JLabel parameter_1_label;
	private JLabel parameter_2_label;
	private JLabel parameter_3_label;
	private JLabel parameter_4_label;
	private JLabel parameter_5_label;
	private JLabel parameter_6_label;
	
	private JRadioButton rdbtnIn;
	private JRadioButton rdbtnOut;
	private JRadioButton rdbtnBookOut;
	private JRadioButton rdbtnChange;
	private JRadioButton rdbtnGet;
	
	private JLabel discountLabel;
	private JComboBox discountComboboxSelection;
	
	private JLabel cugActionLabel;
	private ButtonGroup cugActionButtonGroup;
	private JPanel cugActionPanel;
	
	private String[] parametersList;
	private List<JLabel> labelList;
	private List<JTextField> fieldList;
	private List<String> inputParametersRegex;
	
	private ProvisioningOptionTypeInterface optionType;
	
	private ProvisioningOptionLoaderInterface optionLoader;
	private ProvisioningParametersInterface option_parameters;
	private XmlScannerInterface option_scanner;
	
	private BsoResponseGridManagmentInterface gridManager;
	
	public ProvisioningUtil(){
		optionLoader = new ProvisioningOptionLoader();
		option_parameters = new ProvisioningParameters();
		option_scanner = new XmlScanner();

		optionType = new ProvisioningOptionType();
	}
	
	/* SE MARKET E CTYPE SONO ENTRAMBI SELEZIONATI, SI CARICANO LE OPZIONI PREVISTE PER QUEL SEGMENTO UTENTE
	 */
	public void segmentSelected(){
		optionalParametersAndAction(PROVISIONINGActions._HIDE_OPTIONAL_ACTION);
		if(ctypeButtonGroup.getSelection()!=null && marketButtongroup.getSelection()!=null){
			optionComboboxSelection.removeAllItems();
			
			optionLoader.setComponents(components);
			optionLoader.setOptionComboboxSelection(optionComboboxSelection);
			optionLoader.setMarketButtonGroup(marketButtongroup);
			optionLoader.setCtypeButtonGroup(ctypeButtonGroup);
			optionLoader.load();
			
			optionComboboxSelection.setEnabled(true);
		}
	}
	
	public void optionSelected(){
		setOptionTypeClass();
		optionalParametersAndAction(PROVISIONINGActions._HIDE_OPTIONAL_ACTION);
		if (optionComboboxSelection.getSelectedIndex()!=0) {
			String actions = null;
			option_scanner.loadSelectedOption(optionComboboxSelection, marketButtongroup, ctypeButtonGroup);
			actions = option_scanner.getActionSelectedOption();
			if (actions != null) {
				if (actions.equals(OPTION_FAMILY.POSTPONE)){
					optionType.enablePostPone();
				}else if(actions.equals(OPTION_FAMILY.DISCOUNT)){
					optionType.enableDiscount();
				}else if(actions.equals(OPTION_FAMILY.TARIFF_PLAN) || ((String) optionComboboxSelection.getSelectedItem()).startsWith("GET")){
					optionType.enableTariffPlan();
				}else if (!actions.equals(OPTION_FAMILY.POSTPONE) && !actions.equals(OPTION_FAMILY.DISCOUNT)) {
					optionType.enableMaster(actions);
				}
			}
		} 
	}
	
	private void setOptionTypeClass(){
		optionType.setComponents(components);
		optionType.setRdbtnIn(rdbtnIn);
		optionType.setRdbtnOut(rdbtnOut);
		optionType.setRdbtnBookOut(rdbtnBookOut);
		optionType.setRdbtnChange(rdbtnChange);
		optionType.setRdbtnGet(rdbtnGet);
		optionType.setOptionComboboxSelection(optionComboboxSelection);
	}
	
	/*
	 * CERCA QUALI SONO I PARAMETRI PREVISTI PER L'OPZIONE SCELTA E PER L'AZIONE SELEZIONATA
	 */
	public String[] actionSelected() {
		if (optionComboboxSelection.getSelectedIndex()!=0  && actionButtonGroup.getSelection()!=null) {
			
			optionalParametersAndAction(PROVISIONINGActions._HIDE_OPTIONAL_ONLY);
			setProvisioningParameterClass();
			
			if(!optionComboboxSelection.getSelectedItem().equals("cug")){
				
				String parameters = null;
				parameters = option_scanner.getParameters(actionButtonGroup.getSelection().getActionCommand());
				components.getLogger().info("PARAMETERS: "+parameters);
				
				if(parameters!=null && !parameters.equals("")){
					parametersList = parameters.split("\\|");
					
					inputParametersRegex.clear();
					for (int i = 0; i < parametersList.length; i++) {
						
						String new_parameter;
						
						if(parametersList[i].contains("[") && parametersList[i].contains("]")){
							new_parameter = components.getCommonGUI().optional(parametersList[i]);
						}
						else {
							new_parameter = components.getCommonGUI().mandatory(parametersList[i]);
						}
						
						labelList.get(i).setText(new_parameter);
						inputParametersRegex.add(option_scanner.getRegex(parametersList[i]));
						
						if(labelList.get(i).getText().contains("FEEDISCOUNT")){
							option_parameters.enableParameterDiscount(i);
						}
						else{
							option_parameters.enableOtherParameters(i);
						}
					}
				}
			}
			
			else {
				option_parameters.enableTechnicalOptionParameters();
			}
		}
		return parametersList;
	}
	
	private void setProvisioningParameterClass(){
		option_parameters.setOptionComboboxSelection(optionComboboxSelection);
		option_parameters.setActionButtonGroup(actionButtonGroup);
		option_parameters.setCtypeButtonGroup(ctypeButtonGroup);
		
		option_parameters.setDiscountComboboxSelection(discountComboboxSelection);
		option_parameters.setDiscountLabelList(discountLabel);
		
		option_parameters.setLabelList(labelList);
		option_parameters.setFieldList(fieldList);
		option_parameters.setInputRegexList(inputParametersRegex);
		
		option_parameters.setCugLabel(cugActionLabel);
		option_parameters.setCugPanel(cugActionPanel);
		
		option_parameters.setParameter_1_label(parameter_1_label);
		option_parameters.setParameter_1_field(parameter_1_field);
	}
	
	private void optionalParametersAndAction(int toHide) {
		
		discountComboboxSelection.setVisible(false); discountLabel.setVisible(false);
		parameter_1_label.setVisible(false); parameter_1_field.setVisible(false); parameter_1_field.setText("");
		parameter_2_label.setVisible(false); parameter_2_field.setVisible(false); parameter_2_field.setText("");
		parameter_3_label.setVisible(false); parameter_3_field.setVisible(false); parameter_3_field.setText("");
		parameter_4_label.setVisible(false); parameter_4_field.setVisible(false); parameter_4_field.setText("");
		parameter_5_label.setVisible(false); parameter_5_field.setVisible(false); parameter_5_field.setText("");
		parameter_6_label.setVisible(false); parameter_6_field.setVisible(false); parameter_6_field.setText("");
		
		cugActionLabel.setVisible(false); cugActionPanel.setVisible(false); cugActionButtonGroup.clearSelection();

		if(toHide == PROVISIONINGActions._HIDE_OPTIONAL_ACTION){
			
			actionButtonGroup.clearSelection();
			rdbtnIn.setEnabled(false); 
			rdbtnOut.setEnabled(false); 
			rdbtnBookOut.setEnabled(false); 
			rdbtnChange.setEnabled(false); 
			rdbtnGet.setEnabled(false);
			textArea.setText("");
		}
	}
	
	public void backButtonActionPerformed() {
		if (btnExecute.getActionCommand().equals(PROVISIONINGActions.PROVISIONING_ACTION_RECONNECT)) {
			components.getLogger().info(PROVISIONINGActions.PROVISIONING_ACTION_RECONNECT);
			
			String redirect = components.getConnectionManagerStandard().reconnectionStandard(btnExecute.getActionCommand());
			
			textArea.setText(redirect);
			gridManager.setResponseGridAssurance(redirect);
			
			if (redirect.equals(components.getErrorMessages().get_RELOGGED())) {
				btnExecute.setText(PROVISIONINGActions.PROVISIONING_ACTION_EXECUTE);
				btnExecute.setActionCommand(PROVISIONINGActions.PROVISIONING_ACTION_EXECUTE);
			}
			else if (redirect.equals(components.getErrorMessages().get_NOT_LOGGED_ERROR()) || 
					 redirect.equals(components.getErrorMessages().get_MPM_CONNECTION_ERROR()) || 
					 redirect.equals(components.getErrorMessages().get_MPM_ERROR())) {
				btnExecute.setText(PROVISIONINGActions.PROVISIONING_ACTION_HOME);
				btnExecute.setActionCommand(PROVISIONINGActions.PROVISIONING_ACTION_HOME);
			}

		} else if (btnExecute.getActionCommand().equals(PROVISIONINGActions.PROVISIONING_ACTION_HOME)){
			components.getLogger().info(PROVISIONINGActions.PROVISIONING_ACTION_HOME);
			frame.dispose();
		}
	}

	public void setComponents(DAPBeanInterface components) {
		this.components = components;
		option_scanner.setComponents(components);
	}
	
	@Override
	public void setActionButtonGroup(ButtonGroup actionButtonGroup) {
		this.actionButtonGroup = actionButtonGroup;
	}

	@Override
	public void setMarketButtongroup(ButtonGroup marketButtongroup) {
		this.marketButtongroup = marketButtongroup;
	}

	@Override
	public void setCtypeButtonGroup(ButtonGroup ctypeButtonGroup) {
		this.ctypeButtonGroup = ctypeButtonGroup;
	}

	@Override
	public void setOptionComboboxSelection(JComboBox optionComboboxSelection) {
		this.optionComboboxSelection = optionComboboxSelection;
	}

	@Override
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	@Override
	public void setBtnExecute(JButton btnExecute) {
		this.btnExecute = btnExecute;
	}

	@Override
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void setParameter_1_field(JTextField parameter_1_field) {
		this.parameter_1_field = parameter_1_field;
	}

	@Override
	public void setParameter_2_field(JTextField parameter_2_field) {
		this.parameter_2_field = parameter_2_field;
	}

	@Override
	public void setParameter_3_field(JTextField parameter_3_field) {
		this.parameter_3_field = parameter_3_field;
	}

	@Override
	public void setParameter_4_field(JTextField parameter_4_field) {
		this.parameter_4_field = parameter_4_field;
	}

	@Override
	public void setParameter_5_field(JTextField parameter_5_field) {
		this.parameter_5_field = parameter_5_field;
	}

	@Override
	public void setParameter_6_field(JTextField parameter_6_field) {
		this.parameter_6_field = parameter_6_field;
	}

	@Override
	public void setParameter_1_label(JLabel parameter_1_label) {
		this.parameter_1_label = parameter_1_label;
	}

	@Override
	public void setParameter_2_label(JLabel parameter_2_label) {
		this.parameter_2_label = parameter_2_label;
	}

	@Override
	public void setParameter_3_label(JLabel parameter_3_label) {
		this.parameter_3_label = parameter_3_label;
	}

	@Override
	public void setParameter_4_label(JLabel parameter_4_label) {
		this.parameter_4_label = parameter_4_label;
	}

	@Override
	public void setParameter_5_label(JLabel parameter_5_label) {
		this.parameter_5_label = parameter_5_label;
	}

	@Override
	public void setParameter_6_label(JLabel parameter_6_label) {
		this.parameter_6_label = parameter_6_label;
	}

	@Override
	public void setRdbtnIn(JRadioButton rdbtnIn) {
		this.rdbtnIn = rdbtnIn;
	}

	@Override
	public void setRdbtnOut(JRadioButton rdbtnOut) {
		this.rdbtnOut = rdbtnOut;
	}

	@Override
	public void setRdbtnBookOut(JRadioButton rdbtnBookOut) {
		this.rdbtnBookOut = rdbtnBookOut;
	}

	@Override
	public void setRdbtnChange(JRadioButton rdbtnChange) {
		this.rdbtnChange = rdbtnChange;
	}

	@Override
	public void setRdbtnGet(JRadioButton rdbtnGet) {
		this.rdbtnGet = rdbtnGet;
	}

	@Override
	public void setDiscountLabel(JLabel discountLabel) {
		this.discountLabel = discountLabel;
	}

	@Override
	public void setDiscountComboboxSelection(JComboBox discountComboboxSelection) {
		this.discountComboboxSelection = discountComboboxSelection;
	}

	@Override
	public void setCugActionLabel(JLabel cugActionLabel) {
		this.cugActionLabel = cugActionLabel;
	}

	@Override
	public void setCugActionButtonGroup(ButtonGroup cugActionComboboxSelection) {
		this.cugActionButtonGroup = cugActionComboboxSelection;
	}

	@Override
	public void setCugActionPanel(JPanel cugActionPanel) {
		this.cugActionPanel = cugActionPanel;
	}

	@Override
	public void setLabelList(List<JLabel> labelList) {
		this.labelList = labelList;
	}

	@Override
	public void setInputParametersRegex(List<String> inputParametersRegex) {
		this.inputParametersRegex = inputParametersRegex;
	}

	@Override
	public void setOptionLoader(ProvisioningOptionLoaderInterface optionLoader) {
		this.optionLoader = optionLoader;
	}

	@Override
	public void setOption_parameters(ProvisioningParametersInterface option_parameters) {
		this.option_parameters = option_parameters;
	}

	@Override
	public void setOption_scanner(XmlScannerInterface option_scanner) {
		this.option_scanner = option_scanner;
	}

	public void setParametersList(String[] parametersList) {
		this.parametersList = parametersList;
	}

	@Override
	public void setGridManager(BsoResponseGridManagmentInterface gridManager) {
		this.gridManager = gridManager;
	}

	@Override
	public void setFieldList(List<JTextField> fieldList) {
		this.fieldList = fieldList;
	}
}
