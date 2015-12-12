package com.innovery.mpm.resource.implementations.gui.grid;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;

public class SimExpiryDateResponseGrid {

	private DAPBeanInterface components;
	
	public SimExpiryDateResponseGrid(DAPBeanInterface components){
		this.components = components;
	}
	
	public Map<Object, GridBagConstraints> getGrid(String[] mpm_response_splitted){
		
		Map<Object, GridBagConstraints> gridList = new HashMap<>();
		
		String[] resource_response_splitted = mpm_response_splitted[3].split(",");
		JLabel resource_label = new JLabel("SIM EXPIRY DATE:");
		resource_label.setFont(new Font(resource_label.getFont().getName(), Font.BOLD, resource_label.getFont().getSize()));
		resource_label.setVisible(true);
		
		JLabel resource_label_value = new JLabel(resource_response_splitted[1]);
		resource_label_value.setVisible(true);
	
		gridList.put(resource_label, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 10, 15, 0, 0, 0, 0));
		gridList.put(resource_label_value, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 10, 15, 0, 0, 1, 0));
		return gridList;
	}
}
