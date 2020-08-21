package system.panel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.Action;

import system.dataclass.RouteClass;



public class ChooseRouteFrame extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox = new JComboBox();
	private final Action action = new SwingAction();
	private List<RouteClass> routeList ;

	/**
	 * Create the frame.
	 */
	public ChooseRouteFrame(List list) {
		routeList = list;
		setBounds(100, 100, 263, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		comboBox.setBounds(80, 59, 99, 27);
		for (RouteClass vo : routeList) {
			comboBox.addItem(vo.routeName);
		}
		contentPane.add(comboBox);
		
		JButton btnSelectroute = new JButton("Select route");
		btnSelectroute.setAction(action);
		btnSelectroute.setBounds(73, 156, 117, 29);
		contentPane.add(btnSelectroute);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "OK");
		}
		public void actionPerformed(ActionEvent e) {
			int index = comboBox.getSelectedIndex();
			adminControllerPanel window = new adminControllerPanel(routeList.get(index));
		}
	}
}
