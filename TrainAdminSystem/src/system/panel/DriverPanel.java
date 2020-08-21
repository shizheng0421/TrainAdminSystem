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

import system.dataclass.SystemDataClass;
import system.dataclass.TrainClass;


public class DriverPanel extends JPanel {
	private JTable table;
	private SystemDataClass data;
	private DriverViewModel model;

	/**
	 * Create the panel.
	 */
	public DriverPanel(SystemDataClass data,JPanel panel) {
		this.data = data;
		table = new JTable();
		model = new DriverViewModel(this.data,this, panel);
		table.setModel(model);
		table.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD,13));
		table.setPreferredScrollableViewportSize(new Dimension(360, 180));
		changeCombox();
		JScrollPane scroll = new JScrollPane(table);
		scroll.setAutoscrolls(true);
		add(scroll);
	}
	
	public void changeCombox(){
		JComboBox operationBox = new JComboBox();
		operationBox.addItem("free");
		for (TrainClass train : data.trainList) {
			if(train.driver.equals("null"))
				operationBox.addItem(train.trainName);
		}
		
		TableColumn operationColumn = table.getColumn("DriverStatus");
		operationColumn.setCellEditor(new DefaultCellEditor(operationBox));
	}
	

}
