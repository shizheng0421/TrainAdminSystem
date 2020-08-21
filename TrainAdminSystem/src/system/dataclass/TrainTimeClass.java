package system.dataclass;

import java.util.ArrayList;
import java.util.List;


public class TrainTimeClass {
	public String trainID;
	public String startTime;
	public int stationNum = 0;
	public int status = 0;
	public List<String> stationList;
	public List<TimeClass> outTimeList;
	public List<TimeClass> backTimeList;
	public boolean direction = true;
	
	
		public TrainTimeClass(String trainID,String startTime,int stationNum){
		this.trainID = trainID;
		this.startTime = startTime;
		this.stationNum = stationNum;
		outTimeList = new ArrayList<>();
		backTimeList = new ArrayList<>();
		
		for(int index=0;index<stationNum;index++){
			outTimeList.add(new TimeClass(0,0));
			backTimeList.add(new TimeClass(0,0));
		}
		
	
	}
	public void addNode(){
		stationNum++;
		outTimeList.add(new TimeClass(0,0));
		backTimeList.add(new TimeClass(0,0));
	}
	
	public void setTimeTable(List outlist,List backlist) {
		this.outTimeList = outlist;
		this.backTimeList = backlist;
	}
	
	public TimeClass getOutTimeVO(int index){
		return outTimeList.get(index);
	}
	
	public TimeClass getBackTimeVO(int index){
		return backTimeList.get(index);
	}
}
