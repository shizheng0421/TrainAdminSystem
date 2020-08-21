package system.testcase;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import system.panel.RouteTabelPanel;
import system.panel.RouteViewModel;
import system.start.mainFrame;

public class mainFrameTest {
	private mainFrame main;

	@Before
	public void setUp() throws Exception {
		main = new mainFrame();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMainFrame() {
		assertNotNull(main);
	}
	
	@Test
	public void testrouteModel() {
		RouteViewModel routeModel = main.routeTableModel;
		assertNotNull(routeModel);
	}
	
	@Test
	public void testroutePanel() {
		RouteTabelPanel routepanel = main.routePanel;
		assertNull(routepanel);
	}


}
