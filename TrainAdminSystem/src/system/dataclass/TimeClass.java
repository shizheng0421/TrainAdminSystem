package system.dataclass;

public class TimeClass {
	public int hour=0;
	public int minute=0;
	
	
		public TimeClass(int hour,int minute) {
		// TODO Auto-generated constructor stub
		this.hour = hour;
		this.minute= minute;
	}
	public String toString(){
		if(minute<10)
			 return hour+":0"+minute;
		return hour+":"+minute;
	}
}
