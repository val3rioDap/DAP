package com.innovery.mpm.resource.implementations.gui.grid;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;

public class CommunityResponseGrid {

	private DAPBeanInterface components;
	
	public CommunityResponseGrid(DAPBeanInterface components){
		this.components = components;
	}
	
	public Map<Object, GridBagConstraints> getGrid(String[] mpm_response_splitted){
		
		Map<Object, GridBagConstraints> gridList = new HashMap<>();
		
		String[] resource_response_splitted = mpm_response_splitted[3].split(",");
		JLabel resource_label = new JLabel("COMMUNITY:");
		resource_label.setFont(new Font(resource_label.getFont().getName(), Font.BOLD, resource_label.getFont().getSize()));
		resource_label.setVisible(true);
		
		gridList.put(resource_label, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.CENTER, 10, 10, 0, 30, 0, 0));
		
		for(int i=1; i<resource_response_splitted.length; i++){
			
			JLabel community_id_label = new JLabel("ID"+i);
			community_id_label.setFont(new Font(community_id_label.getFont().getName(), Font.BOLD, community_id_label.getFont().getSize()));
			
			JLabel community_value_label = new JLabel(resource_response_splitted[i]);
			
			gridList.put(community_id_label, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.CENTER, 10, 15, 0, 0, i, 0));
			gridList.put(community_value_label, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.CENTER, 10, 15, 0, 0, i, 1));
		}
		return gridList;
	}
}