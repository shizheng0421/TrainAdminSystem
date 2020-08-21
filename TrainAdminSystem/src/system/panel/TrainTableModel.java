package system.panel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import system.dataclass.TimeClass;
import system.dataclass.TrainClass;
import system.dataclass.TrainTimeClass;


public class TrainTableModel implements TableModel{
	
	private TrainTimeClass vo;
	private int stationNum = 0;
	private List<String> columnList;
	public String[]  statusList;
	
	
	public TrainTableModel(TrainTimeClass vo) {
		this.columnList = vo.stationList;
		this.stationNum = columnList.size();
		this.vo = vo;
		statusList = new String[stationNum*2];
		for(int i = 0;i<stationNum*2;i++){
			statusList[i] = "";
		}
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return stationNum*2 +1;
	}

	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		if(columnIndex==0)
			return "Stations:";
		if((columnIndex-1)<stationNum)
			return columnList.get(columnIndex-1);
		else
			return columnList.get(stationNum*2 - columnIndex);
		
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
		changestaus();
		
		if(rowIndex==0){
			if(columnIndex==0){
				return vo.trainID;
			}
			if((columnIndex-1)<stationNum)
				return vo.outTimeList.get(columnIndex-1).toString();
			else
				return vo.backTimeList.get(stationNum*2 - columnIndex).toString();
		}else{
			if(columnIndex==0){
				return "";
			}else
				return statusList[columnIndex-1];
			
		}

		
	}
	
	public void changestaus(){
		if(vo.direction){
			statusList[vo.status] = "->";
			if(vo.status==(stationNum-1))
			statusList[vo.status + stationNum] = "";
		}else{
			statusList[vo.status+stationNum] = "->";
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
