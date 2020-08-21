package system.panel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import system.dataclass.DriverClass;
import system.dataclass.RouteClass;
import system.dataclass.TrainClass;


public class RouteViewModel implements TableModel{

	public List<RouteClass> routeList;
	private List<String> columnList;
	private int stationNum = 0;
	private JPanel panel;
	
	
	public RouteViewModel(List list) {
		this.columnList = new ArrayList<String>();
		this.routeList = list;
		columnList.add("routeName");
		columnList.add("JourneyNum");
		columnList.add("StationNum");
		columnList.add("Station_1");
		columnList.add("Station_2");
		columnList.add("Station_3");
		columnList.add("Station_4");
		
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 3;
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
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		if(rowIndex>routeList.size()-1)
			return "";
		RouteClass vo = routeList.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return vo.routeName;
		case 1:
			return vo.trainList.size();
		case 2:
			return vo.stationList.size();
		default:{
			int index = columnIndex-3;
			if(index<vo.stationList.size()){
				return vo.stationList.get(index);
			}else
				return "";
		}
			
		}
		
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
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
