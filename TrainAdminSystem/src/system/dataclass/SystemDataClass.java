package system.dataclass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SystemDataClass {
	public RouteClass route = new RouteClass();
	public List<RouteClass> routeList = new ArrayList<>();
	public List<TrainClass> trainList = new ArrayList<>();
	public List<DriverClass> driverList = new ArrayList<>();
	public int routeNum=0;
	public int trainNum = 0;
	public int driverNum = 0;
	
	public SystemDataClass() {
				readDriverData();
		readrouteData();
		readTrainData();
		judge();
		changeNum();
	}
	
	public void changeNum(){
		routeNum = routeList.size();
		trainNum = trainList.size();
		driverNum = driverList.size();
	}
	
	public void readrouteData(){
		File routeFile = new File("systemdata/RouteData.txt");
		if(!routeFile.exists()){
			try {
				routeFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(routeFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String inputString = null;
		try {
			//split the string and store the data in routeVO
			while((inputString = in.readLine())!=null){
				String[] routeArray = inputString.split("@@");
				String route_name = routeArray[0];
				String[] stationArray = routeArray[1].split("->");
				String[] trainArray = routeArray[2].split(",");
				
				RouteClass routeVo = new RouteClass();
				routeVo.routeName = route_name;
				for (String station : stationArray) {
					routeVo.stationList.add(station);
				}
				int num = routeVo.stationList.size();
				
				for(String train : trainArray){
					String[] trainData = train.split("-");
					TrainTimeClass trainVo = new TrainTimeClass(trainData[0],trainData[1],num);
					trainVo.stationList = routeVo.stationList;
					routeVo.trainList.add(trainVo);
					
				}
				
				routeList.add(routeVo);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
	}
	
	public void readTrainData(){	
		File routeFile = new File("systemdata/TrainData.txt");
		if(!routeFile.exists()){
			try {
				routeFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(routeFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String inputString = null;
		try {
			
			while((inputString = in.readLine())!=null){
				String[] trainArray = inputString.split(",");
				
				TrainClass trainVo = new TrainClass();
				trainVo.trainID = trainArray[0];
				trainVo.trainName = trainArray[1];
				trainVo.status = trainArray[2];
				trainVo.driver = trainArray[3];
				
				trainList.add(trainVo);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readDriverData(){	
		File routeFile = new File("systemdata/DriverData.txt");
		if(!routeFile.exists()){
			try {
				routeFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(routeFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String inputString = null;
		try {
			//split the string and store the data in routeVO
			while((inputString = in.readLine())!=null){
				String[] driverArray = inputString.split(",");
				
				DriverClass driverVo = new DriverClass();
				driverVo.driverID = driverArray[0];
				driverVo.driverName = driverArray[1];
				driverVo.status = driverArray[2];
				
				driverList.add(driverVo);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void judge(){
		for(RouteClass vo : routeList){
			for (TrainTimeClass trainvo : vo.trainList) {
				for(TrainClass train: trainList){
					if(train.trainName.equals(trainvo.trainID)){
						train.status = vo.routeName;
					}
				}
			}
		}
		
		for(TrainClass train : trainList){
			for(DriverClass driver:driverList){
				if(train.driver.equals(driver.driverName)){
					driver.status = train.trainName;
				}
			}
		}
	}

}
