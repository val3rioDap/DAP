package com.innovery.mpm.provisioning.implementations.gui.util.options;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

public class ProvisioningSelectionManager implements JComboBox.KeySelectionManager {
	
	long lastKeyTime = 0;
	String pattern = "";

	public int selectionForKey(char aKey, ComboBoxModel model) {
		// Find index of selected item
		int selIx = 01;
		Object sel = model.getSelectedItem();
		if (sel != null) {
			for (int i = 0; i < model.getSize(); i++) {
				if (sel.equals(model.getElementAt(i))) {
					selIx = i;
					break;
				}
			}
		}

		// Get the current time
		long curTime = System.currentTimeMillis();

		// If last key was typed less than 300 ms ago, append to current pattern
		if (curTime - lastKeyTime < 1500) {
			pattern += ("" + aKey);
		} else {
			pattern = ("" + aKey);
		}

		// Save current time
		lastKeyTime = curTime;

		// Search forward from current selection
		for (int i = selIx + 1; i < model.getSize(); i++) {
			String s = model.getElementAt(i).toString().toLowerCase();
			if (s.startsWith(pattern)) {
				return i;
			}
		}

		// Search from top to current selection
		for (int i = 0; i < selIx; i++) {
			if (model.getElementAt(i) != null) {
				String s = model.getElementAt(i).toString().toLowerCase();
				if (s.startsWith(pattern)) {
					return i;
				}
			}
		}
		return -1;
	}
}
