package com.emn.fil.automaticdiscover.dto;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class EnhancedTableModel extends AbstractTableModel {
	
	private String[] columnNames;
	private Object[][] data;
	
	public EnhancedTableModel(String[] columnNames, ArrayList<ArrayList<Object>> arrayList) {
		this.columnNames = columnNames;
		this.data = new Object[arrayList.size()][columnNames.length];
		int row = 0;
		for(ArrayList<?> arr : arrayList) {
			int col = 0;
			for(Object value : arr) {
				this.data[row][col] = value;
				col++;
			}
			row++;
	    }
	}
	
	private void enhancedTableModel(ArrayList<ArrayList<Object>> arrayList) {
		this.data = new Object[arrayList.size()][columnNames.length];
		int row = 0;
		for(ArrayList<?> arr : arrayList) {
			int col = 0;
			for(Object value : arr) {
				this.data[row][col] = value;
				col++;
			}
			row++;
	    }
	}
	
	public void setEnhancedTableModel(ArrayList<Object> arrayList) {
		// test si la nouvelle valeur passé en param existe déjà dans la JTable		
		int i = 0;
		boolean bool = false;
		while((!bool) && (i < this.getRowCount())) {
			int j = 0;
			bool = true;
			while(j < this.getColumnCount()) {
				if(!(arrayList.get(j).equals(this.data[i][j])) && (bool))
					bool = false;
				j++;
			}
			i++;
		}
		
		if(!bool) {
			Object[][] backup = this.data;
			this.data = new Object[(this.getRowCount() + 1)][this.getColumnCount()];
			
			// restauration des anciennes valeurs
			int row = 0;
			for(Object[] arr : backup) {
				int col = 0;
				for(Object value : arr) {
					this.data[row][col] = value;
					col++;
				}
				row++;
		    }
			
			// ajout des nouveaux param
			int colunm = 0;
			for(Object value : arrayList) {
				this.data[row][colunm] = value;
				colunm++;
			}
			// Update of entire table (data only).
			fireTableDataChanged();
		}
	}
	
    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }
    
    public void removeValueAt(int i) {
    	ArrayList<ArrayList<Object>> table = new ArrayList<ArrayList<Object>>();
    	int col, row = 0;
		for(Object[] arr : this.data) {
			if(row != i) {
				col = 0;
				ArrayList<Object> array = new ArrayList<Object>();
				for(Object value : arr) {
					array.add(value);
					col++;
				}
				table.add(array);
			}
			row++;
	    }
		enhancedTableModel(table);
		// Update of entire table (data only).
		fireTableDataChanged();
    }
}