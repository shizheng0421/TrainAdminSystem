package system.panel;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import system.dataclass.RouteClass;
import system.dataclass.SystemDataClass;
import system.dataclass.TrainClass;


public class TrainPanel extends JPanel {
	private JTable table;
	private JScrollPane scroll;
	private SystemDataClass data;
	private TrainViewModel model;
	private JPanel mainPanel;

	/**
	 * Create the panel.
	 */
	public TrainPanel(SystemDataClass data,JPanel panel) {
		table = new JTable();
		this.data = data;
		model = new TrainViewModel(this.data,this, panel);
		this.mainPanel = panel;
		table.setModel(model);
		table.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD,13));
		table.setPreferredScrollableViewportSize(new Dimension(400, 180));
		changeCombox();
		scroll = new JScrollPane(table);
		scroll.setAutoscrolls(true);
		add(scroll);
	}
	
	public void changeCombox(){
		JComboBox operationBox = new JComboBox();
		operationBox.addItem("free");
		for (RouteClass route : data.routeList) {
			if(route.trainList.size()!=3)
				operationBox.addItem(route.routeName);
		}
		
		TableColumn operationColumn = table.getColumn("TrainStatus");
		operationColumn.setCellEditor(new DefaultCellEditor(operationBox));
	}

}
