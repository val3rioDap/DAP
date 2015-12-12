package com.innovery.mpm.connection.implementations.util.gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GUICommon implements GUICommonInterface {
	
	public GridBagConstraints setContraints(int top, int left, int buttom, int right, int x, int y){
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(top, left, buttom, right);
		constraints.gridx = x;
		constraints.gridy = y;
		return constraints;
	}
	
	public GridBagConstraints setContraints(int in_fill, int in_anchor, int in_gridwidth, int x, int y){
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill =in_fill;
		constraints.anchor = in_anchor;
		constraints.gridwidth = in_gridwidth;
		constraints.gridx = x;
		constraints.gridy = y;
		return constraints;
	}
	
	public GridBagConstraints setContraintsAnchor(int in_anchor, int x, int y){
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = in_anchor;
		constraints.gridx = x;
		constraints.gridy = y;
		return constraints;
	}

	public GridBagConstraints setContraints(int in_fill, int in_anchor, int top, int left, int buttom, int right, int x, int y){
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill =in_fill;
		constraints.anchor = in_anchor;
		constraints.insets = new Insets(top, left, buttom, right);
		constraints.gridx = x;
		constraints.gridy = y;
		return constraints;
	}
	
	public GridBagConstraints setContraintsGridWidth(int in_fill, int in_anchor, int in_gridwidth, int top, int left, int buttom, int right, int x, int y){
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill =in_fill;
		constraints.anchor = in_anchor;
		constraints.gridwidth = in_gridwidth;
		constraints.insets = new Insets(top, left, buttom, right);
		constraints.gridx = x;
		constraints.gridy = y;
		return constraints;
	}
	
	public GridBagConstraints setContraintsGridWidth(int in_fill, int in_gridwidth, int top, int left, int buttom, int right, int x, int y){
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill =in_fill;
		constraints.gridwidth = in_gridwidth;
		constraints.insets = new Insets(top, left, buttom, right);
		constraints.gridx = x;
		constraints.gridy = y;
		return constraints;
	}
	
	public GridBagConstraints setContraints(int in_fill, int top, int left, int buttom, int right, int x, int y){
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill =in_fill;
		constraints.insets = new Insets(top, left, buttom, right);
		constraints.gridx = x;
		constraints.gridy = y;
		return constraints;
	}
	
	public GridBagConstraints setContraints(int in_fill, int in_anchor, int top, int left, int buttom, int right, int x, int y, int ix){
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.ipadx =ix;
		constraints.fill =in_fill;
		constraints.anchor = in_anchor;
		constraints.insets = new Insets(top, left, buttom, right);
		constraints.gridx = x;
		constraints.gridy = y;
		return constraints;
	}
	
	public GridBagConstraints setContraintsFill(int in_fill, int top, int left, int buttom, int right, int x, int y, int ix){
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.ipadx =ix;
		constraints.fill =in_fill;
		constraints.insets = new Insets(top, left, buttom, right);
		constraints.gridx = x;
		constraints.gridy = y;
		return constraints;
	}
	
	public GridBagConstraints setContraints(int in_fill, int x, int y, int gridwidth){
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill =in_fill;
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth =gridwidth;
		return constraints;
	}

	public GridBagConstraints setContraintsFill(int in_fill, int x, int y){
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill =in_fill;
		constraints.gridx = x;
		constraints.gridy = y;
		return constraints;
	}
	
	public GridBagConstraints setContraints(int x, int y){
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = x;
		constraints.gridy = y;
		return constraints;
	}
	
	public String getFontName(){
		return font;
	}
	
	public Font setFontSize(int size){
		return new Font(font, style, size);
	}
	
	public Font setFontSize(int style, int size){
		return new Font(font, style, size);
	}
	
	public Font getFont(){
		return new Font(font, style, size);
	}
	
	public String mandatory(String input){
		if(input.endsWith("*")){
			return input;
		}
		return input.concat("*");
	}
	
	public String optional(String input){
		if(input.contains("[") && input.contains("]")){
			return input.replace("[", "").replace("]", "");
		}
		
		else if(!input.endsWith("*")){
			return input;
		}
		
		else {
			return input.replace("*", "");
		}
	}
	
	public boolean isOptional(String input){
		if(input.contains("[") && input.contains("]")){
			return true;
		}
		
		else if(!input.contains("[") && !input.contains("]")){
			return true;
		}
		
		else {
			return false;
		}
	}
	
	public boolean isMandatory (String input){
		if(input.contains("*")){
			return true;
		}
		return false;
	}
}
