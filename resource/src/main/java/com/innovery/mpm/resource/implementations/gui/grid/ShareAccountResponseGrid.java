package com.innovery.mpm.resource.implementations.gui.grid;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;

public class ShareAccountResponseGrid {

	private DAPBeanInterface components;
	
	Map<Object, GridBagConstraints> gridList;
	
	private int x;
	private int y;
	
	public ShareAccountResponseGrid(DAPBeanInterface components){
		this.components = components;
	}
	
	public Map<Object, GridBagConstraints> getGrid(String[] mpm_response_splitted){
		
		gridList = new HashMap<>();
		
		String[] resource_response_splitted = mpm_response_splitted[3].split(",");
		
		for(int i=1; i<resource_response_splitted.length-1; i++){
			
			JLabel temp_label_value = new JLabel(resource_response_splitted[i+1]);
			
			if(resource_response_splitted[i].equals("UCID")){
				setUC(temp_label_value);
			}
			
			else if(resource_response_splitted[i].equals("UCMVALUE1")){
				setUCValue(temp_label_value);
			}
			
			else if(resource_response_splitted[i].equals("UTID")){
				setUT(temp_label_value);
			}
			
			else if(resource_response_splitted[i].equals("UTMVALUE1")){
				setUTValue(temp_label_value);
			}
		}
		
		return gridList;
	}
	
	private void setUC(JLabel value){
		JLabel temp_label_name = new JLabel("UC ID:");
		x = 0;
		
		temp_label_name.setFont(new Font(temp_label_name.getFont().getName(), Font.BOLD, temp_label_name.getFont().getSize()));
		
		if(y==0){
			gridList.put(temp_label_name, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.EAST, 10, 20, 0, 0, x, y));
		}
		
		else {
			gridList.put(temp_label_name, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.EAST, 20, 20, 0, 0, x, y));
		}
		
		x = x+1;
		
		if(y==0){
			gridList.put(value, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 10, 10, 0, 0, x, y));
		}
		
		else{
			gridList.put(value, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 20, 10, 0, 0, x, y));
		}
		
		
		x = x+1;
	}
	
	private void setUCValue(JLabel value){
		JLabel temp_label_name = new JLabel("VALUE:");
		temp_label_name.setFont(new Font(temp_label_name.getFont().getName(), Font.BOLD, temp_label_name.getFont().getSize()));
		if(y==0){
			gridList.put(temp_label_name, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.EAST, 10, 10, 0, 0, x, y));
		}
		
		else {
			gridList.put(temp_label_name, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.EAST, 20, 10, 0, 0, x, y));
		}
		x = x+1;
		
		if(y==0){
			gridList.put(value, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 10, 10, 0, 0, x, y));
		}
		
		else{
			gridList.put(value, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 20, 10, 0, 0, x, y));
		}
		x = x+1;
	}
	
	private void setUT(JLabel value){
		JLabel temp_label_name = new JLabel("UT ID:");
		temp_label_name.setFont(new Font(temp_label_name.getFont().getName(), Font.BOLD, temp_label_name.getFont().getSize()));
		
		if(y==0){
			gridList.put(temp_label_name, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 10, 30, 0, 0, x, y));
		}
		
		else if(y%3==0){
			gridList.put(temp_label_name, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 20, 30, 0, 0, x, y));
		}
		
		else{
			gridList.put(temp_label_name, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 30, 0, 0, x, y));
		}
		x = x+1;
		
		if(y==0){
			gridList.put(value, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 10, 10, 0, 0, x, y));
		}
		
		else if(y%3==0){
			gridList.put(value, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 20, 10, 0, 0, x, y));
		}
		
		else{
			gridList.put(value, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 10, 0, 0, x, y));
		}
		x = x+1;
	}
	
	private void setUTValue(JLabel value){
		JLabel temp_label_name = new JLabel("VALUE:");
		temp_label_name.setFont(new Font(temp_label_name.getFont().getName(), Font.BOLD, temp_label_name.getFont().getSize()));
		
		if(y==0){
			gridList.put(temp_label_name, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 10, 10, 0, 0, x, y));
		}
		
		else if(y%3==0){
			gridList.put(temp_label_name, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 20, 10, 0, 0, x, y));
		}
		
		else{
			gridList.put(temp_label_name, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 10, 0, 0, x, y));
		}
		x = x+1;
		
		if(y==0){
			gridList.put(value, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 10, 10, 0, 0, x, y));
		}
		
		else if(y%3==0){
			gridList.put(value, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 20, 10, 0, 0, x, y));
		}
		
		else{
			gridList.put(value, components.getCommonGUI().setContraints(GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 5, 10, 0, 0, x, y));
		}
		x = 4;
		y = y+1;
	}
}
