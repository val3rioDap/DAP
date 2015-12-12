package com.innovery.mpm.provisioning.implementations.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.innovery.mpm.connection.implementations.util.gui.GUICommonInterface;
import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;
import com.innovery.mpm.connection.interfaces.standard.ConnectionManagmentStandardInterface;
import com.innovery.mpm.connection.interfaces.standard.session.SessionStandardInterface;
import com.innovery.mpm.connection.interfaces.util.AutoCompleteMsisdnInterface;
import com.innovery.mpm.connection.interfaces.util.ErrorMessagesInterface;
import com.innovery.mpm.connection.interfaces.util.NotificationInterface;
import com.innovery.mpm.connection.interfaces.util.TransIdGeneratorInterface;
import com.innovery.mpm.connection.interfaces.util.UserLevelsInterface;
import com.innovery.mpm.provisioning.implementations.gui.util.MyKeySelectionManager;
import com.innovery.mpm.provisioning.implementations.util.PROVISIONINGActions;
import com.innovery.mpm.provisioning.interfaces.gui.BsoResourceInterface;
import com.innovery.mpm.provisioning.interfaces.gui.BsoResponseGridManagmentInterface;
import com.innovery.mpm.provisioning.interfaces.gui.util.ProvisioningUtilInterface;
import com.innovery.mpm.provisioning.interfaces.gui.util.ProvisioningResponseManagmentInterface;
import com.innovery.mpm.provisioning.interfaces.gui.verification.BsoResourcesVerifyInterface;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 
 * @author Daniela R. Auricchio, Valerio Boschi
 * 
 */
public class BsoResource implements BsoResourceInterface{

	private JFrame frame;
	private JPanel panel_center;
	private JPanel command_fieldset;
	private JPanel response_fieldset;
	private JPanel response_fieldset_grid;
	private JPanel execute_details_panel;
	private JPanel radioGroupChangeCug_panel;
	
	private JTextArea textArea;
	private JButton btnExecute;
	
	private JTextField msisdn_field;
	
	private ButtonGroup radioGroupAction;
	private ButtonGroup radioGroupMarket;
	private ButtonGroup radioGroupCtype;
	private ButtonGroup radioGroupChangeCug;
	
	private JRadioButton rdbtnConsumer;
	private JRadioButton rdbtnCorporate;
	
	private JRadioButton rdbtnPrepaid;
	private JRadioButton rdbtnPostpaid;
	
	private JRadioButton rdbtnAddCug;
	private JRadioButton rdbtnDelCug;
	
	private JRadioButton rdbtnIn;
	private JRadioButton rdbtnOut;
	private JRadioButton rdbtnBookOut;
	private JRadioButton rdbtnChange;
	private JRadioButton rdbtnGet;
	
	private JComboBox selectOptionCombo;
	private JComboBox selectDiscountCombo;

	private String[] parametersList;
	
	private JLabel discount_label;
	
	private JLabel cug_action_label;

	private JLabel parameter_1_label;
	private JTextField parameter_1_field;
	
	private JLabel parameter_2_label;
	private JTextField parameter_2_field;

	private JLabel parameter_3_label;
	private JTextField parameter_3_field;

	private JLabel parameter_4_label;
	private JTextField parameter_4_field;
	
	private JLabel parameter_5_label;
	private JTextField parameter_5_field;
	
	private JLabel parameter_6_label;
	private JTextField parameter_6_field;

	private List<JLabel> labelList;
	private List<JTextField> fieldList;
	private List<String> inputParametersRegex;
	
	public final String DEFAULT = "Select option";
	
	private String[] option_list = {DEFAULT};
	
	private JButton button_details;
	private JLabel generic_response_msg;
	

	private ProvisioningUtilInterface util;
	private DAPBeanInterface components;
	private BsoResponseGridManagmentInterface grid_managment;
	private ProvisioningResponseManagmentInterface responseManagment;
	private BsoResourcesVerifyInterface inputVerifier;
	private ConnectionManagmentStandardInterface connectionManagmentStandard;
	private SessionStandardInterface sessionStandard;
	private TransIdGeneratorInterface TRANSID;
	private UserLevelsInterface user_levels;
	private ErrorMessagesInterface errorMessages;
	private NotificationInterface notification;
	private GUICommonInterface commonGUI;
	private AutoCompleteMsisdnInterface autocompleteMsisdn;

	public BsoResource(DAPBeanInterface components){
		this.components = components;
		connectionManagmentStandard = components.getConnectionManagerStandard();
		sessionStandard = connectionManagmentStandard.getSession();
		
		TRANSID = components.getTRANSID();
		user_levels = components.getUserLevels();
		errorMessages = components.getErrorMessages();
		notification = components.getNotifications();
		commonGUI = components.getCommonGUI();
		autocompleteMsisdn = components.getMsisdnCompleter();
		
		labelList = new ArrayList<JLabel>();
		fieldList = new ArrayList<JTextField>();
		inputParametersRegex = new ArrayList<String>();
	}

	public void initialize() {
		
		setFrameInitialSettings();
		setPanelNorth();
		setPanelSouth();
		setPanelWest();
		setPanelEast();
		setPanelCenter();
		
		setPanelCommand();
		
		setPanelResponse();
		setRespondeFieldComponents();

		selectOptionCombo = new JComboBox(option_list);
		selectOptionCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					components.getLogger().info(getSelectOptionCombo().getSelectedItem());
					util.serviceSelection();
				}
			}
		});
		
		setServiceLabel();
		setServiceCombo();
		
		setMsisdnLabel();
		setMsisdnText();

		rdbtnIn = new JRadioButton(PROVISIONINGActions.PROVISIONING_ACTION_IN_LABEL);
		rdbtnIn.setActionCommand(PROVISIONINGActions.PROVISIONING_ACTION_IN);
		rdbtnIn.setEnabled(false);
		rdbtnIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				util.actionSelection();
			}
		});

		rdbtnOut = new JRadioButton(PROVISIONINGActions.PROVISIONING_ACTION_OUT_LABEL);
		rdbtnOut.setActionCommand(PROVISIONINGActions.PROVISIONING_ACTION_OUT);
		rdbtnOut.setEnabled(false);
		rdbtnOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				util.actionSelection();
			}
		});

		rdbtnBookOut = new JRadioButton(PROVISIONINGActions.PROVISIONING_ACTION_BOOK_LABEL);
		rdbtnBookOut.setActionCommand(PROVISIONINGActions.PROVISIONING_ACTION_BOOK);
		rdbtnBookOut.setEnabled(false);
		rdbtnBookOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				util.actionSelection();
			}
		});

		rdbtnChange = new JRadioButton(PROVISIONINGActions.PROVISIONING_ACTION_CHG_LABEL);
		rdbtnChange.setActionCommand(PROVISIONINGActions.PROVISIONING_ACTION_CHG);
		rdbtnChange.setEnabled(false);
		rdbtnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				util.actionSelection();
			}
		});

		rdbtnGet = new JRadioButton(PROVISIONINGActions.PROVISIONING_ACTION_GET_LABEL);
		rdbtnGet.setActionCommand(PROVISIONINGActions.PROVISIONING_ACTION_GET);
		rdbtnGet.setEnabled(false);
		rdbtnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				util.actionSelection();
			}
		});
		
		setActionLabel();
		setRadioActionGroup();
		setRadioActionPanel();
		
		setFeeDiscountLabel();
		setFeeDiscountCombo();
		
		setParameter_1_Label();
		setParameter_1_Text();
		
		setParameter_2_Label();
		setParameter_2_Text();
		
		setParameter_3_Label();
		setParameter_3_Text();
		
		setParameter_4_Label();
		setParameter_4_Text();
		
		setParameter_5_Label();
		setParameter_5_Text();
		
		setParameter_6_Label();
		setParameter_6_Text();
		
		rdbtnAddCug = new JRadioButton(PROVISIONINGActions.PROVISIONING_ACTION_ADD_CUG);
		rdbtnAddCug.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				rdbtnAddCug.setActionCommand(PROVISIONINGActions.PROVISIONING_ACTION_ADD_CUG);
			}
		});

		rdbtnDelCug = new JRadioButton(PROVISIONINGActions.PROVISIONING_ACTION_DEL_CUG);
		rdbtnDelCug.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				rdbtnDelCug.setActionCommand(PROVISIONINGActions.PROVISIONING_ACTION_DEL_CUG);
			}
		});
		
		setChangeCugLabel();
		setRadioCugGroup();
		setRadioCugPanel();
		
		rdbtnConsumer = new JRadioButton(DAPBeanInterface.CONSUMER);
		rdbtnConsumer.setActionCommand(DAPBeanInterface.CONSUMER);
		rdbtnConsumer.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				util.marketSelection();
			}
		});

		rdbtnCorporate = new JRadioButton(DAPBeanInterface.CORPORATE);
		rdbtnCorporate.setActionCommand(DAPBeanInterface.CORPORATE);
		rdbtnCorporate.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				util.marketSelection();
			}
		});
		
		setMarketLabel();
		setRadioMarketGroup();
		setRadioMarketPanel();
		
		rdbtnPrepaid = new JRadioButton(DAPBeanInterface.PREPAID);
		rdbtnPrepaid.setActionCommand(DAPBeanInterface.PREPAID);
		rdbtnPrepaid.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				util.ctypeSelection();
			}
		});

		rdbtnPostpaid = new JRadioButton(DAPBeanInterface.POSTPAID);
		rdbtnPostpaid.setActionCommand(DAPBeanInterface.POSTPAID);
		rdbtnPostpaid.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				util.ctypeSelection();
			}
		});
		
		setCtypeLabel();
		setRadioCtypeGroup();
		setRadioCtypePanel();

		setButtonDetails();
		setExecuteButton();
		
		frame.getRootPane().setDefaultButton(btnExecute);
		frame.setVisible(true);
	}
	
	private void setFrameInitialSettings(){
		frame = new JFrame();
		frame.setResizable(false);
		frame.setSize(1190, 700);
		frame.setTitle("Dashboard Assurance Prepaid - PROVISIONING");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				getFrame().dispose();
			}
		});
		
		frame.getRootPane().getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("ENTER"), "none");
		frame.getRootPane().getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("released ENTER"), "press");

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				getFrame().dispose();
			}
		});
	}
	
	private void setPanelNorth(){
		JPanel panel_north = new JPanel();
		FlowLayout flowLayout_north = (FlowLayout) panel_north.getLayout();
		flowLayout_north.setVgap(5);
		frame.getContentPane().add(panel_north, BorderLayout.NORTH);
		
		JLabel title_label = new JLabel("PROVISIONING");
		title_label.setFont(commonGUI.setFontSize(18));
		panel_north.add(title_label);
		
	}

	private void setPanelSouth(){
		JPanel panel_south = new JPanel();
		frame.getContentPane().add(panel_south, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_south = new GridBagLayout();
		gbl_panel_south.columnWidths = new int[]{0, 0};
		gbl_panel_south.rowHeights = new int[] {0};
		gbl_panel_south.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_south.rowWeights = new double[]{0.0};
		panel_south.setLayout(gbl_panel_south);
		
		JLabel devByLabel = new JLabel();
		devByLabel.setText("- By Innovery S.p.A. -");
		devByLabel.setHorizontalAlignment(SwingConstants.CENTER);
		devByLabel.setForeground(Color.DARK_GRAY);
		devByLabel.setFont(commonGUI.setFontSize(Font.BOLD, 11));
		panel_south.add(devByLabel, commonGUI.setContraints(5, 0, 3, 0, 0, 0));
	}
	
	private void setPanelWest(){
		JPanel panel_west = new JPanel();
		FlowLayout flowLayout_west = (FlowLayout) panel_west.getLayout();
		flowLayout_west.setHgap(10);
		frame.getContentPane().add(panel_west, BorderLayout.WEST);
	}
	
	private void setPanelEast(){
		JPanel panel_east = new JPanel();
		FlowLayout flowLayout_east = (FlowLayout) panel_east.getLayout();
		flowLayout_east.setHgap(10);
		frame.getContentPane().add(panel_east, BorderLayout.EAST);
	}
	
	private void setPanelCenter(){
		panel_center = new JPanel();
		GridBagLayout gbl_panel_center = new GridBagLayout();
		gbl_panel_center.columnWidths = new int[] {711, 0};
		gbl_panel_center.rowHeights = new int[] {628, 0};
		gbl_panel_center.columnWeights = new double[] { 0.0, 1.0 };
		gbl_panel_center.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_center.setLayout(gbl_panel_center);
		frame.getContentPane().add(panel_center, BorderLayout.CENTER);
	}
	
	private void setPanelCommand(){
		command_fieldset = new JPanel();
		command_fieldset.setBorder(new TitledBorder(new LineBorder(new Color(171, 173, 179)), "BSO Command", TitledBorder.LEADING, TitledBorder.TOP, commonGUI.setFontSize(Font.BOLD, 13), null));
		GridBagLayout gbl_command_fieldset = new GridBagLayout();
		gbl_command_fieldset.columnWidths = new int[] {113, 578, 0 };
		gbl_command_fieldset.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_command_fieldset.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_command_fieldset.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,	0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		command_fieldset.setLayout(gbl_command_fieldset);
		panel_center.add(command_fieldset, commonGUI.setContraints(GridBagConstraints.BOTH, 5, 0, 5, 5, 0, 0));
	}
	
	private void setPanelResponse() {
		response_fieldset = new JPanel();
		response_fieldset.setBorder(new TitledBorder(new LineBorder(new Color(171, 173, 179)), "Response", TitledBorder.LEADING, TitledBorder.TOP, commonGUI.setFontSize(Font.BOLD, 13), null));
		GridBagLayout gbl_response_fieldset = new GridBagLayout();
		gbl_response_fieldset.columnWidths = new int[]{0, 0};
		gbl_response_fieldset.rowHeights = new int[] {300, 0};
		gbl_response_fieldset.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_response_fieldset.rowWeights = new double[] { 1.0, Double.MIN_VALUE};
		response_fieldset.setLayout(gbl_response_fieldset);
		panel_center.add(response_fieldset, commonGUI.setContraints(GridBagConstraints.BOTH, 5, 0, 5, 0, 1, 0));
	}
	
	private void setRespondeFieldComponents(){
		setResponseFieldGrid();
		setExecuteDetailsPanel();
		setTextArea();
	}
	
	private void setResponseFieldGrid(){
		response_fieldset_grid = new JPanel();
		GridBagLayout gbl_response_fieldset_grid = new GridBagLayout();
		gbl_response_fieldset_grid.columnWidths = new int[] {70, 110, 110, 0};
		gbl_response_fieldset_grid.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_response_fieldset_grid.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_response_fieldset_grid.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		
		response_fieldset_grid.setLayout(gbl_response_fieldset_grid);
		response_fieldset.add(response_fieldset_grid, commonGUI.setContraints(GridBagConstraints.BOTH, 0, 0, 5, 0, 0, 0));
	}
	
	private void setExecuteDetailsPanel(){
		execute_details_panel = new JPanel();
		GridBagLayout gbl_execute_details_panel = new GridBagLayout();
		gbl_execute_details_panel.columnWidths = new int[]{0, 0, 0};
		gbl_execute_details_panel.rowHeights = new int[]{0, 0};
		gbl_execute_details_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_execute_details_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		execute_details_panel.setLayout(gbl_execute_details_panel);
		response_fieldset.add(execute_details_panel, commonGUI.setContraints(GridBagConstraints.BOTH,0, 0, 5, 0, 0, 1));
	}
	
	private void setTextArea(){
		textArea = new JTextArea();
		textArea.setVisible(false);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		response_fieldset.add(textArea, commonGUI.setContraints(GridBagConstraints.BOTH, 0, 0, 5, 0, 0, 0));
	}
	
	private void setMsisdnLabel(){
		JLabel msisdn_label = new JLabel("MSISDN*");
		msisdn_label.setHorizontalAlignment(SwingConstants.RIGHT);
		command_fieldset.add(msisdn_label, commonGUI.setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 5, 0, 5, 0, 0));
	}
	
	private void setMsisdnText(){
		msisdn_field = new JTextField();
		msisdn_field.setColumns(10);
		msisdn_field.setText(sessionStandard.getCurrent_msisdn());
		command_fieldset.add(msisdn_field, commonGUI.setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 0, 0, 0, 1, 0));
		
		JComboBox msisdn_combo = autocompleteMsisdn.setupAutoComplete(msisdn_field);
		msisdn_field.setLayout(new BorderLayout());
		msisdn_field.add(msisdn_combo, BorderLayout.SOUTH);
	}
	
	private void setMarketLabel(){
		JLabel market_label = new JLabel("MARKET*");
		market_label.setHorizontalAlignment(SwingConstants.RIGHT);
		command_fieldset.add(market_label, commonGUI.setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 5, 0, 5, 0, 1));
	}
	
	private void setCtypeLabel(){
		JLabel ctype_label = new JLabel("CTYPE*");
		ctype_label.setHorizontalAlignment(SwingConstants.RIGHT);
		command_fieldset.add(ctype_label, commonGUI.setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 5, 0, 5, 0, 2));
	}
	
	private void setChangeCugLabel(){
		cug_action_label = new JLabel("CUG ACTION");
		cug_action_label.setVisible(false);
		cug_action_label.setHorizontalAlignment(SwingConstants.RIGHT);
		command_fieldset.add(cug_action_label, commonGUI.setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 5, 0, 5, 0, 5));
	}
	
	private void setRadioMarketGroup(){
		radioGroupMarket = new ButtonGroup();
		radioGroupMarket.add(rdbtnConsumer);
		radioGroupMarket.add(rdbtnCorporate);
		
		if (sessionStandard.getCurrent_market().equals(DAPBeanInterface.CONSUMER)) {
			rdbtnConsumer.setSelected(true);
		}
		else if(sessionStandard.getCurrent_market().equals(DAPBeanInterface.CORPORATE)){
			rdbtnCorporate.setSelected(true);
		}
	}
	
	private void setRadioCtypeGroup(){
		radioGroupCtype = new ButtonGroup();
		radioGroupCtype.add(rdbtnPrepaid);
		radioGroupCtype.add(rdbtnPostpaid);
		
		if (sessionStandard.getCurrent_ctype().equals(DAPBeanInterface.PREPAID)) {
			rdbtnPrepaid.setSelected(true);
		}
		else if(sessionStandard.getCurrent_ctype().equals(DAPBeanInterface.POSTPAID)){
			rdbtnPostpaid.setSelected(true);
		}
	}
	
	private void setRadioCugGroup(){
		radioGroupChangeCug = new ButtonGroup();
		radioGroupChangeCug.add(rdbtnAddCug);
		radioGroupChangeCug.add(rdbtnDelCug);
	}
	
	private void setRadioMarketPanel(){
		JPanel radioGroupMarket_panel = new JPanel();
		command_fieldset.add(radioGroupMarket_panel, commonGUI.setContraints(GridBagConstraints.BOTH, 5, 0, 0, 0, 1, 1));

		GroupLayout gl_radioGroupMarket_panel = new GroupLayout(radioGroupMarket_panel);
		gl_radioGroupMarket_panel.setAutoCreateContainerGaps(true);
		gl_radioGroupMarket_panel.setHorizontalGroup(gl_radioGroupMarket_panel.
				createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_radioGroupMarket_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(rdbtnConsumer, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(rdbtnCorporate, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		
		gl_radioGroupMarket_panel.setVerticalGroup(gl_radioGroupMarket_panel.
				createParallelGroup(Alignment.LEADING)
				.addGroup(gl_radioGroupMarket_panel.createSequentialGroup()
						.addGroup(gl_radioGroupMarket_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnConsumer)
								.addComponent(rdbtnCorporate))
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		
		radioGroupMarket_panel.setLayout(gl_radioGroupMarket_panel);
		gl_radioGroupMarket_panel.setAutoCreateContainerGaps(true);
		
	}
	
	private void setRadioCtypePanel(){
		JPanel radioGroupCtype_panel = new JPanel();
		command_fieldset.add(radioGroupCtype_panel, commonGUI.setContraints(GridBagConstraints.BOTH, 5, 0, 0, 0, 1, 2));

		GroupLayout gl_radioGroupCtype_panel = new GroupLayout(radioGroupCtype_panel);
		gl_radioGroupCtype_panel.setAutoCreateContainerGaps(true);
		gl_radioGroupCtype_panel.setHorizontalGroup(gl_radioGroupCtype_panel.
				createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_radioGroupCtype_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(rdbtnPrepaid, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(rdbtnPostpaid, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		
		gl_radioGroupCtype_panel.setVerticalGroup(gl_radioGroupCtype_panel.
				createParallelGroup(Alignment.LEADING)
				.addGroup(gl_radioGroupCtype_panel.createSequentialGroup()
						.addGroup(gl_radioGroupCtype_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnPrepaid)
								.addComponent(rdbtnPostpaid))
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		
		radioGroupCtype_panel.setLayout(gl_radioGroupCtype_panel);
		gl_radioGroupCtype_panel.setAutoCreateContainerGaps(true);
		
	}

	private void setRadioCugPanel(){
		radioGroupChangeCug_panel = new JPanel();
		radioGroupChangeCug_panel.setVisible(false);
		command_fieldset.add(radioGroupChangeCug_panel, commonGUI.setContraints(GridBagConstraints.BOTH, 5, 0, 0, 0, 1, 5));

		GroupLayout gl_radioGroupChangeCug_panel = new GroupLayout(radioGroupChangeCug_panel);
		gl_radioGroupChangeCug_panel.setAutoCreateContainerGaps(true);
		gl_radioGroupChangeCug_panel.setHorizontalGroup(gl_radioGroupChangeCug_panel.
				createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_radioGroupChangeCug_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(rdbtnAddCug, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(rdbtnDelCug, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		
		gl_radioGroupChangeCug_panel.setVerticalGroup(gl_radioGroupChangeCug_panel.
				createParallelGroup(Alignment.LEADING)
				.addGroup(gl_radioGroupChangeCug_panel.createSequentialGroup()
						.addGroup(gl_radioGroupChangeCug_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnAddCug)
								.addComponent(rdbtnDelCug))
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		
		radioGroupChangeCug_panel.setLayout(gl_radioGroupChangeCug_panel);
		gl_radioGroupChangeCug_panel.setAutoCreateContainerGaps(true);
		
	}
	
	private void setServiceLabel(){
		JLabel service_label = new JLabel("SERVICE*");
		service_label.setHorizontalAlignment(SwingConstants.RIGHT);
		command_fieldset.add(service_label, commonGUI.setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 5, 0, 5, 0, 3));
	}
	
	private void setServiceCombo(){
		selectOptionCombo.setPrototypeDisplayValue("ROPZ0000");
		selectOptionCombo.setEnabled(false);
		selectOptionCombo.setKeySelectionManager(new MyKeySelectionManager());
		selectOptionCombo.setMaximumRowCount(20);
		command_fieldset.add(selectOptionCombo, commonGUI.setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 0, 0, 0, 1, 3, 25));
	}
	
	private void setActionLabel(){
		JLabel action_label = new JLabel("ACTION*");
		action_label.setHorizontalAlignment(SwingConstants.RIGHT);
		command_fieldset.add(action_label, commonGUI.setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 5, 0, 5, 0, 4));
	}
	
	private void setRadioActionGroup(){
		radioGroupAction = new ButtonGroup();
		radioGroupAction.add(rdbtnIn);
		radioGroupAction.add(rdbtnOut);
		radioGroupAction.add(rdbtnBookOut);
		radioGroupAction.add(rdbtnChange);
		radioGroupAction.add(rdbtnGet);
	}
	
	private JPanel setRadioActionPanel(){
		JPanel radioGroupAction_panel = new JPanel();
		command_fieldset.add(radioGroupAction_panel, commonGUI.setContraints(GridBagConstraints.BOTH, 5, 0, 0, 0, 1, 4));
	
		GroupLayout gl_radioGroupAction_panel = new GroupLayout(
				radioGroupAction_panel);
		gl_radioGroupAction_panel.setAutoCreateContainerGaps(true);
		gl_radioGroupAction_panel.setHorizontalGroup(
			gl_radioGroupAction_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_radioGroupAction_panel.createSequentialGroup()
					.addComponent(rdbtnIn)
					.addGap(20)
					.addComponent(rdbtnOut)
					.addGap(20)
					.addComponent(rdbtnBookOut)
					.addGap(20)
					.addComponent(rdbtnChange)
					.addGap(20)
					.addComponent(rdbtnGet)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		gl_radioGroupAction_panel.setVerticalGroup(
			gl_radioGroupAction_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_radioGroupAction_panel.createSequentialGroup()
					.addGroup(gl_radioGroupAction_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnIn)
						.addComponent(rdbtnOut)
						.addComponent(rdbtnBookOut)
						.addComponent(rdbtnChange)
						.addComponent(rdbtnGet))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_radioGroupAction_panel.setAutoCreateContainerGaps(true);
		radioGroupAction_panel.setLayout(gl_radioGroupAction_panel);
		return radioGroupAction_panel;
	}
	
	private void setFeeDiscountLabel(){
		discount_label = new JLabel("FEEDISCOUNT");
		discount_label.setVisible(false);
		discount_label.setHorizontalAlignment(SwingConstants.RIGHT);
		command_fieldset.add(discount_label, commonGUI.setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 5, 0, 5, 0, 9));
	}
	
	private void setFeeDiscountCombo(){
		selectDiscountCombo = new JComboBox();
		selectDiscountCombo.setPrototypeDisplayValue("ROPZ0000");
		selectDiscountCombo.setMaximumRowCount(5);
		selectDiscountCombo.setVisible(false);
		command_fieldset.add(getSelectDiscountCombo(), commonGUI.setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 0, 0, 0, 1, 9, 25));
	}
	
	private void setParameter_1_Label() {
		parameter_1_label = new JLabel();
		parameter_1_label.setVisible(false);
		parameter_1_label.setHorizontalAlignment(SwingConstants.RIGHT);
		labelList.add(parameter_1_label);
		command_fieldset.add(parameter_1_label, commonGUI.setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 5, 0, 5, 0, 10));
	}
	
	private void setParameter_2_Label() {
		parameter_2_label = new JLabel();
		parameter_2_label.setVisible(false);
		parameter_2_label.setHorizontalAlignment(SwingConstants.RIGHT);
		labelList.add(parameter_2_label);
		command_fieldset.add(parameter_2_label, commonGUI.setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 5, 0, 5, 0, 11));
	}
	
	private void setParameter_3_Label() {
		parameter_3_label = new JLabel();
		parameter_3_label.setVisible(false);
		parameter_3_label.setHorizontalAlignment(SwingConstants.RIGHT);
		labelList.add(parameter_3_label);
		command_fieldset.add(parameter_3_label, commonGUI.setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 5, 0, 5, 0, 12));
	}
	
	private void setParameter_4_Label() {
		parameter_4_label = new JLabel();
		parameter_4_label.setVisible(false);
		parameter_4_label.setHorizontalAlignment(SwingConstants.RIGHT);
		labelList.add(parameter_4_label);
		command_fieldset.add(parameter_4_label, commonGUI.setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 5, 0, 5, 0, 13));
	}
	
	private void setParameter_5_Label() {
		parameter_5_label = new JLabel();
		parameter_5_label.setVisible(false);
		parameter_5_label.setHorizontalAlignment(SwingConstants.RIGHT);
		labelList.add(parameter_5_label);
		command_fieldset.add(parameter_5_label, commonGUI.setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 5, 0, 5, 0, 14));
	}
	
	private void setParameter_6_Label() {
		parameter_6_label = new JLabel();
		parameter_6_label.setVisible(false);
		parameter_6_label.setHorizontalAlignment(SwingConstants.RIGHT);
		labelList.add(parameter_6_label);
		command_fieldset.add(parameter_6_label, commonGUI.setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 5, 0, 5, 0, 15));
	}

	private void setParameter_1_Text() {
		parameter_1_field = new JTextField();
		parameter_1_field.setVisible(false);
		parameter_1_field.setColumns(10);
		fieldList.add(parameter_1_field);
		command_fieldset.add(parameter_1_field, commonGUI.setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 0, 0, 0, 1, 10));
	}

	private void setParameter_2_Text() {
		parameter_2_field = new JTextField();
		parameter_2_field.setVisible(false);
		parameter_2_field.setColumns(10);
		fieldList.add(parameter_2_field);
		command_fieldset.add(parameter_2_field, commonGUI.setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 0, 0, 0, 1, 11));
	}

	private void setParameter_3_Text() {
		parameter_3_field = new JTextField();
		parameter_3_field.setVisible(false);
		parameter_3_field.setColumns(10);
		fieldList.add(parameter_3_field);
		command_fieldset.add(parameter_3_field, commonGUI.setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 0, 0, 0, 1, 12));
	}

	private void setParameter_4_Text() {
		parameter_4_field = new JTextField();
		parameter_4_field.setVisible(false);
		parameter_4_field.setColumns(10);
		fieldList.add(parameter_4_field);
		command_fieldset.add(parameter_4_field, commonGUI.setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 0, 0, 0, 1, 13));
	}

	private void setParameter_5_Text() {
		parameter_5_field = new JTextField();
		parameter_5_field.setVisible(false);
		parameter_5_field.setColumns(10);
		fieldList.add(parameter_5_field);
		command_fieldset.add(parameter_5_field, commonGUI.setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 0, 0, 0, 1, 14));
	}

	private void setParameter_6_Text() {
		parameter_6_field = new JTextField();
		parameter_6_field.setVisible(false);
		parameter_6_field.setColumns(10);
		fieldList.add(parameter_6_field);
		command_fieldset.add(parameter_6_field, commonGUI.setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 0, 0, 0, 1, 15));
	}
	
	private void setExecuteButton(){
		btnExecute = new JButton("Execute");
		btnExecute.setActionCommand("execute");
		execute_details_panel.add(btnExecute, commonGUI.setContraints(GridBagConstraints.CENTER, GridBagConstraints.WEST, 0, 0, 5, 0, 0, 0));
		frame.getRootPane().setDefaultButton(btnExecute);
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnExecute.setEnabled(false);
				if(btnExecute.getActionCommand().equals("execute")){
					if (inputVerifier.verifyOptionalParameters()) {
						grid_managment.resetResponseGrid();
						Thread thread = new Thread(new Runnable() {
							public void run() {
								try {
									Thread.sleep(100); // to give time for users to
														// read
								} catch (InterruptedException e) {
									components.getLogger().warn("UNABLE TO SLEEP");
									components.getLogger().warn(e.getCause());
								} finally {
									responseManagment.getResponse();
								}
							}
						});
						thread.start();
					}
					else{
						btnExecute.setEnabled(true);
					}
				}
				else{
					util.backButtonActionPerformed();
					btnExecute.setEnabled(true);
				}
			}
		});
	}
	
	private void setButtonDetails() {
		button_details = new JButton("+");
		button_details.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if(textArea.isVisible()){
					textArea.setVisible(false);
					response_fieldset_grid.setVisible(true);
					button_details.setText("+");
				}
				else{
					textArea.setVisible(true);
					response_fieldset_grid.setVisible(false);
					button_details.setText("-");
				}
			}
		});
		execute_details_panel.add(button_details, commonGUI.setContraints(GridBagConstraints.CENTER, GridBagConstraints.EAST, 0, 0, 5, 0, 1, 0));
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public JButton getBtnExecute() {
		return btnExecute;
	}

	public JTextField getMsisdn_field() {
		return msisdn_field;
	}

	public ButtonGroup getRadioGroupAction() {
		return radioGroupAction;
	}

	public ButtonGroup getRadioGroupMarket() {
		return radioGroupMarket;
	}
	
	public ButtonGroup getRadioGroupCtype() {
		return radioGroupCtype;
	}

	public JRadioButton getRdbtnConsumer() {
		return rdbtnConsumer;
	}

	public JRadioButton getRdbtnCorporate() {
		return rdbtnCorporate;
	}

	public JRadioButton getRdbtnIn() {
		return rdbtnIn;
	}

	public JRadioButton getRdbtnOut() {
		return rdbtnOut;
	}

	public JRadioButton getRdbtnBookOut() {
		return rdbtnBookOut;
	}

	public JRadioButton getRdbtnChange() {
		return rdbtnChange;
	}

	public JRadioButton getRdbtnGet() {
		return rdbtnGet;
	}

	public JComboBox getSelectOptionCombo() {
		return selectOptionCombo;
	}

	public JComboBox getSelectDiscountCombo() {
		return selectDiscountCombo;
	}

	public String[] getParametersList() {
		return parametersList;
	}
	
	public void setParametersList(String[] parametersList){
		this.parametersList = parametersList;
	}

	public JLabel getDiscount_label() {
		return discount_label;
	}

	public JLabel getParameter_1_label() {
		return parameter_1_label;
	}

	public JTextField getParameter_1_field() {
		return parameter_1_field;
	}

	public JLabel getParameter_2_label() {
		return parameter_2_label;
	}

	public JTextField getParameter_2_field() {
		return parameter_2_field;
	}

	public JLabel getParameter_3_label() {
		return parameter_3_label;
	}

	public JTextField getParameter_3_field() {
		return parameter_3_field;
	}

	public JLabel getParameter_4_label() {
		return parameter_4_label;
	}

	public JTextField getParameter_4_field() {
		return parameter_4_field;
	}

	public JLabel getParameter_5_label() {
		return parameter_5_label;
	}

	public JTextField getParameter_5_field() {
		return parameter_5_field;
	}

	public JLabel getParameter_6_label() {
		return parameter_6_label;
	}

	public JTextField getParameter_6_field() {
		return parameter_6_field;
	}

	public List<JLabel> getLabelList() {
		return labelList;
	}

	public List<JTextField> getFieldList() {
		return fieldList;
	}

	public List<String> getInputParametersRegex() {
		return inputParametersRegex;
	}

	public String getDEFAULT() {
		return DEFAULT;
	}

	public String[] getOption_list() {
		return option_list;
	}

	public SessionStandardInterface getSessionStandard() {
		return sessionStandard;
	}

	public JPanel getResponse_fieldset_grid() {
		return response_fieldset_grid;
	}

	public JButton getButton_details() {
		return button_details;
	}

	public JLabel getGeneric_response_msg() {
		return generic_response_msg;
	}

	public void setGeneric_response_msg(JLabel generic_response_msg) {
		this.generic_response_msg = generic_response_msg;
	}

	public BsoResponseGridManagmentInterface getGrid_managment() {
		return grid_managment;
	}

	public void setResponse_fieldset_grid(JPanel response_fieldset_grid) {
		this.response_fieldset_grid = response_fieldset_grid;
	}

	public JPanel getResponse_fieldset() {
		return response_fieldset;
	}

	public JLabel getCug_action_label() {
		return cug_action_label;
	}

	public ButtonGroup getRadioGroupChangeCug() {
		return radioGroupChangeCug;
	}

	public JPanel getRadioGroupChangeCug_panel() {
		return radioGroupChangeCug_panel;
	}
	
	public ErrorMessagesInterface getErrorMessages() {
		return errorMessages;
	}
	
	public TransIdGeneratorInterface getTRANSID() {
		return TRANSID;
	}
	
	public UserLevelsInterface getUser_levels() {
		return user_levels;
	}
	
	public ConnectionManagmentStandardInterface getConnectionManagmentStandard() {
		return connectionManagmentStandard;
	}
	
	public NotificationInterface getNotification() {
		return notification;
	}
	
	public GUICommonInterface getCommonGUI() {
		return commonGUI;
	}
	
	public void setResponseManagment(ProvisioningResponseManagmentInterface responseManagment) {
		this.responseManagment = responseManagment;
	}

	public void setInputVerifier(BsoResourcesVerifyInterface inputVerifier) {
		this.inputVerifier = inputVerifier;
	}

	public void setUtil(ProvisioningUtilInterface util) {
		this.util = util;
	}

	public void setGrid_managment(BsoResponseGridManagmentInterface grid_managment) {
		this.grid_managment = grid_managment;
	}
}