package com.innovery.mpm.provisioning.implementations.gui.util;

import java.util.ArrayList;
import java.util.List;

import com.innovery.mpm.connection.interfaces.component.DAPBeanInterface;
import com.innovery.mpm.provisioning.implementations.xml.XmlListReader;
import com.innovery.mpm.provisioning.interfaces.gui.BsoResourceInterface;
import com.innovery.mpm.provisioning.interfaces.gui.util.ProvisioningOptionLoaderInterface;
import com.innovery.mpm.provisioning.interfaces.xml.XmlListReaderInterface;

public class ProvisioningOptionLoader implements ProvisioningOptionLoaderInterface {
	
	private DAPBeanInterface components;
	private BsoResourceInterface reference;

	@SuppressWarnings("null")
	public void load(){
		reference.getSelectOptionCombo().removeAllItems();
		XmlListReaderInterface listReader = new XmlListReader();
		listReader.setComponents(components);
		
		List<String> folderContent = listReader.getList(reference.getRadioGroupMarket().getSelection().getActionCommand(), reference.getRadioGroupCtype().getSelection().getActionCommand());

		if(folderContent==null){
			reference.getSelectOptionCombo().addItem(reference.getDEFAULT());
			return;
		}
		
		List<String> folderContentSorted = null;
		if(reference.getRadioGroupMarket().getSelection().getActionCommand().equals(DAPBeanInterface.CONSUMER)){
			folderContentSorted = folderContentSort(folderContent);
		}
		else if(reference.getRadioGroupMarket().getSelection().getActionCommand().equals(DAPBeanInterface.CORPORATE)){
			folderContentSorted = folderContent;
		}
		
		reference.getSelectOptionCombo().addItem(reference.getDEFAULT());
		for(int i=0; i<folderContentSorted.size(); i++){
			reference.getSelectOptionCombo().addItem(folderContentSorted.get(i));
		}
	}
	
	private List<String> folderContentSort(List<String> folderContent){
		List<Integer> originalInteger = new ArrayList<Integer>();
		List<Integer> notStandardIndex = new ArrayList<Integer>();
		List<String> outputList = new ArrayList<String>();
		
		for(int i=0; i<folderContent.size(); i++){
			if(folderContent.get(i).contains("ROPZ")){
				originalInteger.add(Integer.parseInt(folderContent.get(i).substring("ROPZ".length(), folderContent.get(i).length())));
			}
			else{
				notStandardIndex.add(i);
			}
		}
	
		for(int j=0; j<originalInteger.size(); j++){
			for(int i=0; i<(originalInteger.size()-1)-j; i++){
				
				int currentOption = originalInteger.get(i);
				int nextOption = originalInteger.get(i+1);
					
				if(currentOption > nextOption){
					originalInteger.set(i+1, currentOption);
					originalInteger.set(i, nextOption);					
				}
			}
		}
		
		for(int i=0; i<originalInteger.size(); i++){
			outputList.add(i, "ROPZ"+originalInteger.get(i).toString());
		}

		for(int i=0; i<notStandardIndex.size(); i++){
			outputList.add(outputList.size()-i, folderContent.get(notStandardIndex.get(i)));
		}
		
		return outputList;
	}

	public void setReference(BsoResourceInterface reference) {
		this.reference = reference;
	}

	public void setComponents(DAPBeanInterface components) {
		this.components = components;
	}
}
