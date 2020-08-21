package system.panel;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.AbstractAction;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Action;
import javax.swing.JScrollPane;

import system.dataclass.RouteClass;
import system.dataclass.TimeClass;
import system.dataclass.TrainTimeClass;
import system.dataclass.FrameDataClass;
import system.thread.RouteTrainPath;
import system.thread.TimeSimulateThread;

import javax.swing.JComboBox;

public class adminControllerPanel extends JFrame {
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	private JScrollPane scrollPane = new JScrollPane();
	private JComboBox comboBox = new JComboBox();
	public TimeSimulateThread trainTimer;
	private JPanel panel = new JPanel();
	JButton btnStart = new JButton("Start");
	
	private FrameDataClass framedata;
	public RouteClass router; 
	private final Action action_3 = new SwingAction_3();
	private final JLabel lblNewLabel = new JLabel("");

		public adminControllerPanel(RouteClass router) {
		
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				for(RouteTrainPath thread: framedata.pathList){
					thread.stop();
				}
				for (TrainTimeClass train : router.trainList) {
					train.status = 0;
					train.direction = true;
				}
				
			}
		});
		initData(router);
		
		getContentPane().setLayout(null);
		
		
		panel.setBounds(6, 6, 718, 244);
		getContentPane().add(panel);
		
		for (TrainTimeClass trainVO : router.trainList) {
			addTrainTable(trainVO);
			comboBox.addItem(trainVO.trainID);
		}
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(scrollPane);
		
		
		btnStart.setAction(action);
		btnStart.setBounds(39, 279, 157, 29);
		getContentPane().add(btnStart);
		
		JButton btnStop = new JButton("Stop");
		btnStop.setAction(action_1);
		btnStop.setBounds(208, 279, 169, 29);
		getContentPane().add(btnStop);
		
		JButton btnResume = new JButton("Resume");
		btnResume.setAction(action_2);
		btnResume.setBounds(389, 279, 174, 29);
		getContentPane().add(btnResume);
		
		
		comboBox.setBounds(215, 330, 103, 27);
		getContentPane().add(comboBox);
		
		JButton btnDrivercontrollerframe = new JButton("DriverControllerFrame");
		btnDrivercontrollerframe.setAction(action_3);
		btnDrivercontrollerframe.setBounds(330, 329, 172, 29);
		getContentPane().add(btnDrivercontrollerframe);
		lblNewLabel.setBounds(595, 284, 109, 16);
		
		getContentPane().add(lblNewLabel);
		
		setTitle("AdminControllerFrame");
		setSize(747, 400);
		setVisible(true);

	}
	
	public void initData(RouteClass router){
		framedata = new FrameDataClass();
		this.router = router;
	}
	
	public void addTrainTable(TrainTimeClass trainVO){
		
		JFrame driveFrame = new JFrame();
		driveFrame.setTitle("DriverControllerFrame");
		JButton driver_stop = new JButton("stop");
		JButton driver_resume = new JButton("resume");
		JTable trainTable = new JTable();
		JTable driverTable = new JTable();
		framedata.tableList.add(trainTable);
		framedata.tableList.add(driverTable);
		TrainTableModel trainTableModel = new TrainTableModel(trainVO);
		trainTable.setModel(trainTableModel);
		driverTable.setModel(trainTableModel);
		JScrollPane trainTableScroll = new JScrollPane(trainTable);
		JScrollPane trainTableScroll_1 = new JScrollPane(driverTable);
		
		trainTable.setPreferredScrollableViewportSize(new Dimension(400, 50));
		driverTable.setPreferredScrollableViewportSize(new Dimension(400, 50));
		trainTableScroll.setAutoscrolls(true);
		
		RouteTrainPath trainPath = new RouteTrainPath(trainVO,trainTable,driverTable,router.stationList);
		framedata.pathList.add(trainPath);
		
		panel.add(trainTableScroll);
		trainTableScroll_1.setBounds(0, 0, 450, 80);
		driver_stop.setBounds(10, 85, 80, 30);
		driver_resume.setBounds(90, 85, 80, 30);
		
		driveFrame.getContentPane().add(trainTableScroll_1);
		driveFrame.getContentPane().add(driver_stop);
		driveFrame.getContentPane().add(driver_resume);
		driveFrame.getContentPane().setLayout(null);
		driveFrame.setSize(450, 150);
		driveFrame.setVisible(false);
		framedata.drverframeList.add(driveFrame);
		
		
		
		driver_stop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				framedata.stop_temp = System.currentTimeMillis();
				trainPath.suspend();
			}
		});
		driver_resume.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				framedata.end_temp = System.currentTimeMillis();
				int time_stop = (int) ((framedata.end_temp - framedata.stop_temp) / 1000);
				trainPath.setStopTime(time_stop);
				trainPath.resume();
			}
		});
	
	}
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Start Simulation");
			putValue(SHORT_DESCRIPTION, "Start all the trains.");
		}
		public void actionPerformed(ActionEvent e) {

			btnStart.setEnabled(false);
			trainTimer = new TimeSimulateThread(lblNewLabel);
			trainTimer.start();
			for (int index=0;index<framedata.pathList.size();index++) {
				framedata.pathList.get(index).setCurrentTime();
				framedata.pathList.get(index).start();
			}
		
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Stop Simulation");
			putValue(SHORT_DESCRIPTION, "Stop all the trains.");
		}
		public void actionPerformed(ActionEvent e) {
			framedata.stop_time = System.currentTimeMillis();
			for (int index=0;index<framedata.pathList.size();index++) {
				framedata.pathList.get(index).suspend();
			}
		}
	}
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "Resume Simulation");
			putValue(SHORT_DESCRIPTION, "Restart all the trains.");
		}
		public void actionPerformed(ActionEvent e) {
			framedata.end_time = System.currentTimeMillis();
			int stop = (int)((framedata.end_time - framedata.stop_time)/1000);
			for (int index=0;index<framedata.pathList.size();index++) {
				framedata.pathList.get(index).setStopTime(stop);
				framedata.pathList.get(index).resume();
			}
		}
	}
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "DriverControllerFrame");
			putValue(SHORT_DESCRIPTION, "Open the driver frame.");
		}
		public void actionPerformed(ActionEvent e) {
			int index = comboBox.getSelectedIndex();
			JFrame driver = framedata.drverframeList.get(index);
			driver.setVisible(true);
		}
	}
}
