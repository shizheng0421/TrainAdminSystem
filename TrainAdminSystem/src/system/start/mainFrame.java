package system.start;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.AbstractAction;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import system.dataclass.SystemDataClass;
import system.panel.DriverPanel;
import system.panel.DriverViewModel;
import system.panel.RouteTabelPanel;
import system.panel.RouteViewModel;
import system.panel.ChooseRouteFrame;
import system.panel.TrainPanel;
import system.panel.TrainViewModel;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;

public class mainFrame extends JFrame {
	//The TableModel
	private DriverViewModel driverTableModel;
	private TrainViewModel trainTbaleModel;
	public RouteViewModel routeTableModel;

	private JPanel contentPane;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();


	SystemDataClass dataVO = new SystemDataClass();
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	JPanel panelroute = new JPanel();
	TrainPanel trainPanel;
	public RouteTabelPanel routePanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		mainFrame frame = new mainFrame();
		frame.setVisible(true);
			
	}

	/**
	 * Create the frame.
	 */
	public mainFrame() {
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		routeTableModel = new RouteViewModel(dataVO.routeList);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 510);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnDataview = new JMenu("DataView");
		menuBar.add(mnDataview);
		
		JMenuItem mntmAdmindataview = new JMenuItem("AdminDataView");
		mntmAdmindataview.setAction(action_2);
		mnDataview.add(mntmAdmindataview);
		
		JMenu mnView = new JMenu("ControllerView");
		menuBar.add(mnView);
		
		JMenuItem mntmAdminview = new JMenuItem("AdminView");
		mntmAdminview.setAction(action);
		mnView.add(mntmAdminview);
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		DriverPanel driverPanel = new DriverPanel(dataVO,contentPane);
		panel.add(driverPanel);
		
		trainPanel = new TrainPanel(dataVO,contentPane);
		panel.add(trainPanel);
		contentPane.add(panel);
		
		
		panelroute.setLayout(new BoxLayout(panelroute, BoxLayout.X_AXIS));
		RouteTabelPanel routePanel = new RouteTabelPanel(routeTableModel,dataVO,contentPane,trainPanel);
		panelroute.add(routePanel);
		contentPane.add(panelroute);
		
		
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "AdminControllerView");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			ChooseRouteFrame selectView = new ChooseRouteFrame(dataVO.routeList);
			selectView.setVisible(true);
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "DriverControllerView");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "AdminDataView");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			//System.out.println(dataVO.routeList.size());
			if(routeTableModel.routeList.size()==3){
				System.out.println(routeTableModel.routeList.get(2).toString());
			};
			
			trainPanel.changeCombox();
		}
	}
	
}
