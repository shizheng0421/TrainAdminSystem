package system.thread;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class TimeSimulateThread extends Thread{
	public JLabel timeLabel;
	public int runingTime = 0;
	
	public TimeSimulateThread(JLabel timeLabel) {
		this.timeLabel = timeLabel;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			runingTime += 5;
			String time = SimulateTime();;
			int current_hour = Integer.valueOf(time.substring(0, 1));
			if(current_hour>=24){
				time = "24:00";
				return;
			}
			timeLabel.setText(time);
		}
	}
	public String SimulateTime(){
		int current_hour = runingTime / 60;
		int currnet_minute = runingTime % 60;
		
		return current_hour + ":" + currnet_minute;
	}

}
