package com.innovery.mpm.resource.implementations.gui.grid;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;

public class FafResponseGrid {

	private DAPBeanInterface components;
	
	private Map<Object, GridBagConstraints> gridList;
	
	public FafResponseGrid(DAPBeanInterface components){
		this.components = components;
	}
	
	public Map<Object, GridBagConstraints> getGrid(String[] mpm_response_splitted){
		gridList = new HashMap<>();
		setHeader();
		setValuse(mpm_response_splitted);
		return gridList;
	}
	
	private void setHeader(){
		JLabel resource_1_label = new JLabel("NUMBER");
		JLabel resource_2_label = new JLabel("FAF INDICATOR");
		JLabel resource_3_label = new JLabel("OWNER");
		
		resource_1_label.setFont(new Font(resource_1_label.getFont().getName(), Font.BOLD, resource_1_label.getFont().getSize()));
		resource_2_label.setFont(new Font(resource_2_label.getFont().getName(), Font.BOLD, resource_2_label.getFont().getSize()));
		resource_3_label.setFont(new Font(resource_3_label.getFont().getName(), Font.BOLD, resource_3_label.getFont().getSize()));
		
		gridList.put(resource_1_label, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.CENTER, 5, 40, 10, 0, 0, 0));
		gridList.put(resource_2_label, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.CENTER, 5, 60, 10, 0, 1, 0));
		gridList.put(resource_3_label, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.CENTER, 5, 0, 10, 0, 2, 0));
	}
	
	private void setValuse(String[] mpm_response_splitted){
		String[] resource_response_splitted = mpm_response_splitted[3].split(",");
		
		int y = 1;
		
		for(int i=1; i<resource_response_splitted.length-1; i++){
			
			JLabel label_temp = new JLabel(resource_response_splitted[i+1]);
			
			if(resource_response_splitted[i].equals("FAFNUMBER")){
				gridList.put(label_temp, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.CENTER, 5, 40, 0, 0, 0, y));
			}
			
			else if(resource_response_splitted[i].equals("KINDICATOR")){
				gridList.put(label_temp, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.CENTER, 5, 60, 0, 0, 1, y));
			}
			
			else if(resource_response_splitted[i].equals("OWNER")){
				
				JLabel faf_owner = null;
				
				if(label_temp.getText().equals("1")){
					faf_owner = new JLabel("SUBSCRIBER");
				}
				
				else if(label_temp.getText().equals("2")){
					faf_owner = new JLabel("ACCOUNT");
				}
				
				else{
					faf_owner = new JLabel("");
				}
				gridList.put(faf_owner, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.CENTER, 5, 0, 0, 0, 2, y));
				y = y+1;
			}
		}
	}
}
