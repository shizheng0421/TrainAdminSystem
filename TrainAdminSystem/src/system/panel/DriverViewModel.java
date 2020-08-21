package system.panel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import system.dataclass.DriverClass;
import system.dataclass.SystemDataClass;
import system.dataclass.TrainClass;




public class DriverViewModel implements TableModel{

	
	private List<DriverClass> driverList;
	private List<String> columnList;
	private SystemDataClass data;
	private DriverPanel panel;
	private JPanel mainPanel;
	
	public DriverViewModel(SystemDataClass data,DriverPanel panel,JPanel mainPanel) {
		this.columnList = new ArrayList<String>();
		this.mainPanel = mainPanel;
		this.data = data;
		this.driverList = data.driverList;
		this.panel = panel;
		columnList.add("DriverID");
		columnList.add("DriverName");
		columnList.add("DriverStatus");
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return driverList.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnList.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		return columnList.get(columnIndex);
		
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		DriverClass driver = driverList.get(rowIndex);
		if(columnIndex==2 && driver.status.equals("free")){
			return true;
		}
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
		
		DriverClass vo = driverList.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return vo.driverID;
		case 1:
			return vo.driverName;
		default:
			return vo.status;
		}
		
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		String train = aValue.toString();
		DriverClass driver = driverList.get(rowIndex);
		driver.status = train;
		
		for (TrainClass temp : data.trainList) {
			if(temp.trainName.equals(train)){
				temp.driver = driver.driverName;
			}
		}
		panel.changeCombox();
		mainPanel.updateUI();
		
		
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}



}
