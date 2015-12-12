package com.innovery.mpm.resource.implementations.gui.grid;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;

public class MainAccountResponseGrid {

	private DAPBeanInterface components;
	
	public MainAccountResponseGrid(DAPBeanInterface components){
		this.components = components;
	}
	
	public Map<Object, GridBagConstraints> getGrid(String[] mpm_response_splitted){
		
		Map<Object, GridBagConstraints> gridList = new HashMap<>();
		
		String[] resource_response_splitted = mpm_response_splitted[3].split(",");
		JLabel resource_label = new JLabel("MAIN ACCOUNT (�):");
		resource_label.setFont(new Font(resource_label.getFont().getName(), Font.BOLD, resource_label.getFont().getSize()));
		resource_label.setVisible(true);
		
		JLabel resource_label_value = new JLabel(resource_response_splitted[1]);
		resource_label_value.setVisible(true);
		
		gridList.put(resource_label, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 10, 15, 0, 0, 0, 0));
		
		StringBuffer main_account_value = new StringBuffer(resource_label_value.getText());
		
		if(main_account_value.length()==1){
			resource_label_value = new JLabel("0,0"+main_account_value.toString());
			gridList.put(resource_label_value, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 10, 15, 0, 0, 1, 0));
		}
		
		else if(main_account_value.length()==2){
			resource_label_value = new JLabel("0,"+main_account_value.toString());
			gridList.put(resource_label_value, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 10, 15, 0, 0, 1, 0));
		}
		
		else if(main_account_value.length()!=2 && main_account_value.length()!=2){
			StringBuffer main_account_value_euro = new StringBuffer(main_account_value.substring(0, main_account_value.length()-2)+","+main_account_value.substring(main_account_value.length()-2, main_account_value.length()));
			resource_label_value = new JLabel(main_account_value_euro.toString());
			gridList.put(resource_label_value, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 10, 15, 0, 0, 1, 0));
		}
		return gridList;
	}
}
