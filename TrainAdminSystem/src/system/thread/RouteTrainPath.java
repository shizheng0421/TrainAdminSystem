package system.thread;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.JTable;
import javax.swing.text.DateFormatter;

import system.dataclass.TrainTimeClass;
import system.dataclass.TimeClass;

public class RouteTrainPath extends Thread{
	
	private int trainRunTimer = 0;
	private int SumTimeout;
	private int SumTimeback;
	private int start_hour;
	private int start_minute;
	private TrainTimeClass trainvo;
	private JTable trainTable;
	private JTable driverTable;
	private String[] startarray;
	public List<String> stationArray = new ArrayList<>();;
	private int duration = 20;
	private String startTime = null;
	public TimeClass currentTime = new TimeClass(0, 0);
	private int waitTime = 0;
	

	public void setStartTime(String time){
		startarray = time.split(":");
		start_hour = Integer.valueOf(startarray[0]);
		start_minute = Integer.valueOf(startarray[1]);
		stringTime();
	}
	
	public void setCurrentTime(){
		waitTime = (start_hour - currentTime.hour) * 60 + start_minute - currentTime.minute;
	}

	public RouteTrainPath(TrainTimeClass vo,JTable trainTable,JTable driverTabel,List stationList) {
		this.trainvo = vo;
		this.trainTable = trainTable;
		this.driverTable = driverTabel;
		this.stationArray = stationList;
		this.startTime = vo.startTime;
		
		SumTimeout = duration * (vo.stationNum-1);
		SumTimeback = SumTimeout + 5;
		startarray = startTime.split(":");
		
		start_hour = Integer.valueOf(startarray[0]);
		start_minute = Integer.valueOf(startarray[1]);
		stringTime();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(waitTime<0){
			return;
		}
		while(trainRunTimer<waitTime){
			try {
				Thread.sleep(200);
				trainRunTimer+=1;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		trainRunTimer=0;
		while(trainRunTimer<SumTimeout){
			try {
				Thread.sleep(200);
				trainRunTimer+=1;
				if(trainRunTimer%10 == 0)
					judgeTime(trainRunTimer);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		trainRunTimer = 0;
		while(trainRunTimer<SumTimeback){
			try {
				Thread.sleep(200);
				trainRunTimer+=1;
				int backTime = trainRunTimer-5;
				if(backTime%10 ==0){
					judgeBackTime(backTime);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void setStopTime(int time_stop){
		start_minute+= time_stop * 5;
		stringTime();
		this.trainTable.updateUI();
	}
	
	public void judgeBackTime(int trainRunTimer){
		trainvo.direction = false;
		int index = trainRunTimer/20;
		trainvo.status = index;
		trainTable.updateUI();
		driverTable.updateUI();
	
	}
	
	public void judgeTime(int trainRunTimer){
		int index = trainRunTimer/20;
		trainvo.status = index;
		trainTable.updateUI();
		driverTable.updateUI();
	}
	
	public void stringTime(){
		SumTimeout = duration * (trainvo.stationNum-1);
		SumTimeback = SumTimeout + 5;
		for(int index = 0;index<trainvo.stationNum;index++){
			int durationSum = duration * index;
			if(trainRunTimer>=durationSum && trainRunTimer!=0)
				continue;
			int stationTime = start_minute + durationSum;
			int temp_start_hour = stationTime / 60 + start_hour;
			int temp_start_minute = stationTime % 60;
			if(temp_start_hour>=24){
				System.out.println("timeout");
			}
			TimeClass vo = new TimeClass(temp_start_hour,temp_start_minute);
			trainvo.outTimeList.set(index, vo);
		}
		for(int index = 0;index<trainvo.stationNum;index++){
			int durationSum = duration * (trainvo.stationNum-1 + index) + 5;
			
			if(trainRunTimer>=durationSum && trainRunTimer!=0)
				continue;
			int stationTime = start_minute + durationSum;
			int temp_start_hour = stationTime / 60 + start_hour;
			int temp_start_minute = stationTime % 60;
			if(temp_start_hour>=24){
				System.out.println("timeout");
			}
			TimeClass vo = new TimeClass(temp_start_hour,temp_start_minute);
			trainvo.backTimeList.set(trainvo.stationNum-1-index,vo);
		}
		
	}
	
}
