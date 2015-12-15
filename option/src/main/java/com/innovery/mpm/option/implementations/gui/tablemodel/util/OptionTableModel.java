package com.innovery.mpm.option.implementations.gui.tablemodel.util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import com.innovery.mpm.option.implementations.gui.tablemodel.OptionBean;
import com.innovery.mpm.option.interfaces.gui.tablemodel.OptionBeanInterface;
import com.innovery.mpm.option.interfaces.gui.tablemodel.util.OptionTableModelInterface;


/**
 * 
 * @author Daniela R. Auricchio
 *
 */
public class OptionTableModel extends AbstractTableModel implements OptionTableModelInterface {
	
	private String[] columnNames = { "Description", "Option\nCode", "Service\nCode", "Dedicated\nAccount", "Accumulator", 
			"Offer", "Community\nid", "Faf", "Service\nOffering", "Promotion\nPlan", "Account\ngroup id", "UC", 
			"UT", "Related to", "Service\nCode"};

	
	private List<OptionBeanInterface> data = new ArrayList<OptionBeanInterface>();
	
	/* (non-Javadoc)
	 * @see com.innovery.mpm.option.implementations.gui.tablemodel.util.OptionTableModelInterface#getColumnCount()
	 */
	public int getColumnCount() {
		return columnNames.length;
	}

	/* (non-Javadoc)
	 * @see com.innovery.mpm.option.implementations.gui.tablemodel.util.OptionTableModelInterface#getRowCount()
	 */
	public int getRowCount() {
		return data.size();
	}
	
	/* (non-Javadoc)
	 * @see com.innovery.mpm.option.implementations.gui.tablemodel.util.OptionTableModelInterface#getColumnName(int)
	 */
	public String getColumnName(int col){
		return columnNames[col];
	}
	
	/* (non-Javadoc)
	 * @see com.innovery.mpm.option.implementations.gui.tablemodel.util.OptionTableModelInterface#getValueAt(int, int)
	 */
	public Object getValueAt(int row, int col) {
        Object value = null;
        switch (col) {
        	case 0:
	            value = data.get(row).getDescription();
	            break;
        	case 1:
	            value = data.get(row).getMaster();
                break;
        	case 2:
                value = data.get(row).getSctoString();
                break;
        	case 3:
        		value = data.get(row).getDaListtoString();
                break;
        	case 4:
        		value = data.get(row).getAccumulatorListtoString();
                break;
        	case 5:
        		value = data.get(row).getOfferIdListtoString();
                break;
        	case 6:
                value = data.get(row).getCommunity();
                break;
        	case 7:
        		value = data.get(row).getFafListtoString();
                break;
        	case 8:
                value = data.get(row).getSo();
                break;
        	case 9:
                value = data.get(row).getPplan();
                break;
        	case 10:
                value = data.get(row).getAgi();
                break;
        	case 11:
        		value = data.get(row).getUCtoString();
                break;
        	case 12:
        		value = data.get(row).getUTtoString();
                break;
        	case 13:
        		value = data.get(row).getDiscountstoString();
                break;
        	case 14:
        		value = data.get(row).getScDiscountstoString();
                break;
        	
        }
        return value;
	}


	public List<OptionBeanInterface> getData() {
		return data;
	}

	public void setData(List<OptionBeanInterface> data) {
		this.data = data;
	}

	public void reset(){
		this.data = new ArrayList<OptionBeanInterface>();
		this.fireTableDataChanged();
	}
	
	public String getOptionCode(int row){
		return data.get(row).getMaster();
	}
	
	public void removeRow(int row){
		data.remove(row);
	    fireTableRowsDeleted(row, row);
	}
	
	public void addRow(OptionBean option){
		data.add(option);
		fireTableDataChanged();
	}
	
	public void setColumnWidth(JTable table){
		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(140);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(5).setPreferredWidth(45);
		table.getColumnModel().getColumn(7).setPreferredWidth(40);
		table.getColumnModel().getColumn(11).setPreferredWidth(40);
		table.getColumnModel().getColumn(12).setPreferredWidth(40);
		table.getColumnModel().getColumn(13).setPreferredWidth(65);

	}
}