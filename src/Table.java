import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class Table extends AbstractTableModel
{
    int columnCount = 8;
	ArrayList<String[]> dataArrayList;
    
	public Table()
    {
		dataArrayList = new ArrayList<String[]>();
		for(int i = 0; i < dataArrayList.size(); i++)
		{
			dataArrayList.add(new String[getColumnCount()]);
		}
    }
	
	public void addDate(String[] row)
	{
		String[] rowTable = new String[getColumnCount()];
		rowTable = row;
		dataArrayList.add(rowTable);
	}
	
	@Override
	public int getRowCount() {
		
		return dataArrayList.size();
	}

	@Override
	public int getColumnCount() {
		
		return columnCount;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		String[] rows = dataArrayList.get(rowIndex);
		
		return rows[columnIndex];
	}
	
	@Override
	public String getColumnName(int columnIndex)
	{
		switch(columnIndex)
		{
		   case 0: return "Время";
		   case 1: return "Количетсво коробок, шт";
		   case 2: return "Стоимость, руб.";
		   case 3: return "Количетсво коробок, шт";
		   case 4: return "Стоимость, руб.";
		   case 5: return "Количетсво коробок, шт";
		   case 6: return "Стоимость, руб.";
		   case 7: return "Итого, руб.";
		
		
		}
		return "";
	}

}
