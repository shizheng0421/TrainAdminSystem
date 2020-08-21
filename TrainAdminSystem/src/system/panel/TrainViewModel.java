package system.panel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import system.dataclass.DriverClass;
import system.dataclass.RouteClass;
import system.dataclass.SystemDataClass;
import system.dataclass.TrainClass;
import system.dataclass.TrainTimeClass;


public class TrainViewModel implements TableModel{
	

	private List<TrainClass> trainList;
	private List<String> columnList;
	private SystemDataClass data;
	private JPanel mainPanel;
	private TrainPanel panel;
	
	public TrainViewModel(SystemDataClass data,TrainPanel panel,JPanel mainPanel) {
		this.columnList = new ArrayList<String>();
		this.data = data;
		this.trainList = data.trainList;
		this.panel = panel;
		this.mainPanel = mainPanel;
		columnList.add("TrainID");
		columnList.add("TrainName");
		columnList.add("TrainStatus");
		columnList.add("TrainDriver");
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return trainList.size();
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
		TrainClass train = trainList.get(rowIndex);
		if(columnIndex==2 && train.status.equals("free")){
			return true;
		}
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

		TrainClass vo = trainList.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return vo.trainID;
		case 1:
			return vo.trainName;
		case 2:
			return vo.status;
		default:
			return vo.driver;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		String route = aValue.toString();
		TrainClass train = trainList.get(rowIndex);
		train.status = route;
		
		for (RouteClass temp : data.routeList) {
			if(temp.routeName.equals(route)){
				TrainTimeClass trainVo = new TrainTimeClass(train.trainName,"1:00",temp.stationList.size());
				trainVo.stationList = temp.stationList;
				temp.trainList.add(trainVo);
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
