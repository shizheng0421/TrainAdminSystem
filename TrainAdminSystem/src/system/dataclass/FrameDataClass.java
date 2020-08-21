package system.dataclass;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;

import system.thread.RouteTrainPath;



public class FrameDataClass {
	//the stop time
	public long stop_time = 0;
	public long end_time = 0;
	public long stop_temp = 0;
	public long end_temp = 0;
		
	
	public List<RouteTrainPath> pathList;
	public List<JTable> tableList;
	public List<JFrame> drverframeList;
	
	public FrameDataClass() {
		// TODO Auto-generated constructor stub
		pathList = new ArrayList<RouteTrainPath>();
		tableList = new ArrayList<>();
		drverframeList = new ArrayList<>();
	}
}
