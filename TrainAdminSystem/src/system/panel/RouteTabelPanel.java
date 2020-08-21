package system.panel;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.DefaultCellEditor;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.BoxLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.table.TableColumn;

import system.dataclass.RouteClass;
import system.dataclass.SystemDataClass;
import system.dataclass.TrainClass;
import system.dataclass.TrainTimeClass;

import javax.swing.AbstractAction;
import javax.swing.Action;



public class RouteTabelPanel extends JPanel {
	private JTable table;
	private final Action action = new SwingAction();
	private SystemDataClass datavo;
	private final Action action_1 = new SwingAction_1();
	private JPanel main;
	private TrainPanel train;

	/**
	 * Create the panel.
	 */
	public RouteTabelPanel(RouteViewModel model,SystemDataClass data,JPanel mainPanel,TrainPanel trainPanel) {
		this.main = mainPanel;
		this.datavo = data;
		this.train = trainPanel;
		table = new JTable();
		table.setModel(model);
		table.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD,13));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		table.setPreferredScrollableViewportSize(new Dimension(200, 180));
		
				JComboBox operationBox = new JComboBox();
		operationBox.addItem("1");
		operationBox.addItem("2");
		operationBox.addItem("3");
		operationBox.addItem("4");
		
		TableColumn operationColumn = table.getColumn("StationNum");
		operationColumn.setCellEditor(new DefaultCellEditor(operationBox));
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setAutoscrolls(true);
		add(scroll);
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(null);
		
		JButton btnRemoveroute = new JButton("removeroute");
		btnRemoveroute.setAction(action_1);
		btnRemoveroute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRemoveroute.setBounds(6, 43, 117, 29);
		panel.add(btnRemoveroute);
		
		JButton btnModefyroute = new JButton("modifyroute");
		btnModefyroute.setAction(action);
		btnModefyroute.setBounds(6, 110, 117, 29);
		panel.add(btnModefyroute);

	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Modifyroute");
			putValue(SHORT_DESCRIPTION, "Modify the route.");
		}
		public void actionPerformed(ActionEvent e) {
			ModifyRouteFrame frame = new ModifyRouteFrame(datavo);
			frame.setVisible(true);
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "removeroute");
			putValue(SHORT_DESCRIPTION, "Delete the route.");
		}
		public void actionPerformed(ActionEvent e) {
			
			int index = table.getSelectedRow();
			if(index>(datavo.routeList.size()-1))
				return;
			RouteClass temp = datavo.routeList.get(index);
			for (TrainTimeClass train : temp.trainList) {
				String name = train.trainID;
				for(TrainClass trainvo : datavo.trainList){
					if(trainvo.trainName.equals(name)){
						trainvo.status = "free";
					}
				}
			}
			datavo.routeList.remove(index);
			train.changeCombox();
			main.updateUI();
		}
	}
}
