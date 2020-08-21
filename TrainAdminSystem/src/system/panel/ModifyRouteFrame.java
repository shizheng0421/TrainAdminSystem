package system.panel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;

import system.dataclass.RouteClass;
import system.dataclass.SystemDataClass;
import system.dataclass.TrainTimeClass;


public class ModifyRouteFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JLabel lblStation_3;
	private final Action action = new SwingAction();
	private SystemDataClass dataVO;
	private JLabel lblStationnum;
	private JTextField textField_5;
	private JButton button;
	private final Action action_1 = new SwingAction_1();

	/**
	 * Create the frame.
	 */
	public ModifyRouteFrame(SystemDataClass vo) {
		dataVO = vo;
		setBounds(100, 100, 606, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New route");
		lblNewLabel.setBounds(25, 72, 80, 16);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(16, 100, 89, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(141, 100, 89, 28);
		contentPane.add(textField_1);
		
		JLabel lblStation = new JLabel("Station_1");
		lblStation.setBounds(156, 72, 80, 16);
		contentPane.add(lblStation);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(262, 100, 89, 28);
		contentPane.add(textField_2);
		
		JLabel lblStation_1 = new JLabel("Station_2");
		lblStation_1.setBounds(271, 72, 80, 16);
		contentPane.add(lblStation_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(382, 100, 89, 28);
		contentPane.add(textField_3);
		
		JLabel lblStation_2 = new JLabel("Station_3");
		lblStation_2.setBounds(391, 72, 80, 16);
		contentPane.add(lblStation_2);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(500, 100, 89, 28);
		contentPane.add(textField_4);
		
		lblStation_3 = new JLabel("Station_4");
		lblStation_3.setBounds(509, 72, 80, 16);
		contentPane.add(lblStation_3);
		
		JButton btnAddroute = new JButton("Add route");
		btnAddroute.setAction(action);
		btnAddroute.setBounds(214, 175, 117, 29);
		contentPane.add(btnAddroute);
		
		lblStationnum = new JLabel("StationNum");
		lblStationnum.setBounds(25, 148, 80, 16);
		contentPane.add(lblStationnum);
		
		textField_5 = new JTextField("4");
		textField_5.setColumns(10);
		textField_5.setBounds(16, 176, 89, 28);
		contentPane.add(textField_5);
		
		button = new JButton("Add route");
		button.setAction(action_1);
		button.setBounds(354, 175, 117, 29);
		contentPane.add(button);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "addroute");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
						if(dataVO.routeNum>=3){
				JOptionPane.showMessageDialog(null, "The sum of the Journey is 3!", "Error!", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			
			
			String name = textField.getText();
			String numStr = textField_5.getText() ;
			int num = Integer.valueOf( numStr );
			String station_1 = textField_1.getText();
			String station_2 = textField_2.getText();
			String station_3 = textField_3.getText();
			String station_4 = textField_4.getText();
			List<String> list = new ArrayList<>();
			list.add(station_1);
			list.add(station_2);
			list.add(station_3);
			list.add(station_4);
			
			if(name==null){
				JOptionPane.showMessageDialog(null, "Please input the name", "Error!", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(numStr==null){
				JOptionPane.showMessageDialog(null, "Please input the StationNum", "Error!", JOptionPane.ERROR_MESSAGE);
				return;
			}
			for(RouteClass vo: dataVO.routeList){
				if(vo.routeName.equals(name)){
					JOptionPane.showMessageDialog(null, "The route exist!", "Error!", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}
			
			
			RouteClass newVo = new RouteClass();
			newVo.routeName = name;
			for(int i=0;i<num;i++){
				newVo.stationList.add(list.get(i));
			}
			dataVO.routeList.add(newVo);
			dataVO.changeNum();
			JOptionPane.showMessageDialog(null, "The route is added.", "Error!", JOptionPane.INFORMATION_MESSAGE);
			
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "saveChange");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			
			String name = textField.getText();
			String numStr = textField_5.getText() ;
			int num=1;
				num = Integer.valueOf( numStr );
				
			
			String station_1 = textField_1.getText();
			String station_2 = textField_2.getText();
			String station_3 = textField_3.getText();
			String station_4 = textField_4.getText();
			List<String> list = new ArrayList<>();
			list.add(station_1);
			list.add(station_2);
			list.add(station_3);
			list.add(station_4);
			
			if(name==null){
				JOptionPane.showMessageDialog(null, "Please input the name", "Error!", JOptionPane.ERROR_MESSAGE);
				return;
			}
			RouteClass indexvo = null;
			for(RouteClass temp : dataVO.routeList){
				if(temp.routeName.equals(name))
					indexvo = temp;
			}
						if(indexvo ==null){
				JOptionPane.showMessageDialog(null, "Please input the right RouteName", "Error!", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(numStr==null){
				JOptionPane.showMessageDialog(null, "Please input the StationNum", "Error!", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			
			
			RouteClass newVo = new RouteClass();
			newVo.routeName = name;
			for(int i=0;i<num;i++){
				newVo.stationList.add(list.get(i));
			}
			
			newVo.trainList = indexvo.trainList;
			for(TrainTimeClass train : newVo.trainList){
				train.stationList = newVo.stationList;
			}
			
			int index = dataVO.routeList.indexOf(indexvo);
			System.out.println(index);
			dataVO.routeList.remove(index);
			dataVO.routeList.add(index, newVo);
			JOptionPane.showMessageDialog(null, "The route is modified.", "Error!", JOptionPane.INFORMATION_MESSAGE);
				
			
		}
	}
}
