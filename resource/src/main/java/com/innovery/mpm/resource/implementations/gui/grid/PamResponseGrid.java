package com.innovery.mpm.resource.implementations.gui.grid;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;

public class PamResponseGrid {

	private DAPBeanInterface components;
	
	public PamResponseGrid(DAPBeanInterface components){
		this.components = components;
	}
	
	public Map<Object, GridBagConstraints> getGrid(String[] mpm_response_splitted){
		
		Map<Object, GridBagConstraints> gridList = new HashMap<>();
		
		String[] resource_response_splitted = mpm_response_splitted[3].split(",");
		
		int x = 0;
		int y = 0;
		
		for(int i=1; i<resource_response_splitted.length-1; i++){
			
			JLabel temp_label_name = new JLabel(resource_response_splitted[i]);
			JLabel temp_label_value = new JLabel(resource_response_splitted[i+1]);
			
			if(temp_label_name.getText().equals("PAMSERVICEID")){
				temp_label_name = new JLabel("PAM SERVICE ID:");
				temp_label_name.setFont(new Font(temp_label_name.getFont().getName(), Font.BOLD, temp_label_name.getFont().getSize()));
				
				if(y==0){
					gridList.put(temp_label_name, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.EAST, 10, 5, 0, 0, x, y));
				}
				
				else{
					gridList.put(temp_label_name, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.EAST, 20, 5, 0, 0, x, y));
				}
				
				x = x+1;
				
				if(y==0){
					gridList.put(temp_label_value, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 10, 5, 0, 0, x, y));
				}
				
				else{
					gridList.put(temp_label_value, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 20, 5, 0, 0, x, y));
				}
				
				x = x+1;
			}
			
			else if(temp_label_name.getText().equals("PAMCLASSID")){
				temp_label_name = new JLabel("CLASS ID:");
				temp_label_name.setFont(new Font(temp_label_name.getFont().getName(), Font.BOLD, temp_label_name.getFont().getSize()));
				
				if(y==0){
					gridList.put(temp_label_name, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 10, 20, 0, 0, x, y));
				}
				
				else{
					gridList.put(temp_label_name, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 20, 20, 0, 0, x, y));
				}
				
				x = x+1;
				
				if(y==0){
					gridList.put(temp_label_value, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 10, 20, 0, 0, x, y));
				}
				
				else{
					gridList.put(temp_label_value, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 20, 20, 0, 0, x, y));
				}
				
				x = 2;
				
				y = y+1;
			}
			
			else if(temp_label_name.getText().equals("SCHEDULEID")){
				temp_label_name = new JLabel("SCHEDULE ID:");
				temp_label_name.setFont(new Font(temp_label_name.getFont().getName(), Font.BOLD, temp_label_name.getFont().getSize()));
				gridList.put(temp_label_name, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 20, 0, 0, x, y));
				
				x = x+1;
				
				gridList.put(temp_label_value, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 20, 0, 0, x, y));
				
				x = 2;
				
				y = y+1;
			}
			
			else if(temp_label_name.getText().equals("CURRENTPAMPERIOD")){
				temp_label_name = new JLabel("PAM PERIOD:");
				temp_label_name.setFont(new Font(temp_label_name.getFont().getName(), Font.BOLD, temp_label_name.getFont().getSize()));
				gridList.put(temp_label_name, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 20, 0, 0, x, y));
				
				x = x+1;
				
				gridList.put(temp_label_value, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 20, 0, 0, x, y));
				
				x = 2;
				
				y = y+1;
			}
			
			else if(temp_label_name.getText().equals("LASTEVALUATIONDATE")){
				temp_label_name = new JLabel("LAST EVALUATION DATE:");
				temp_label_name.setFont(new Font(temp_label_name.getFont().getName(), Font.BOLD, temp_label_name.getFont().getSize()));
				gridList.put(temp_label_name, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 20, 0, 0, x, y));
				
				x = x+1;
				
				gridList.put(temp_label_value, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 20, 0, 0, x, y));
				
				x = 2;
				
				y = y+1;
			}
			
			else if(temp_label_name.getText().equals("PAMSERVICEPRIORITY")){
				temp_label_name = new JLabel("SERVICE PRIORITY:");
				temp_label_name.setFont(new Font(temp_label_name.getFont().getName(), Font.BOLD, temp_label_name.getFont().getSize()));
				gridList.put(temp_label_name, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 20, 0, 0, x, y));
				
				x = x+1;
				
				gridList.put(temp_label_value, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 20, 0, 0, x, y));
				
				x = 0;
				
				y = y+1;
			}
		}
		return gridList;
	}
}
