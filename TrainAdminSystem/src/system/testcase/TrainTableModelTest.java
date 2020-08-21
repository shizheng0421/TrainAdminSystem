package system.testcase;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import system.dataclass.SystemDataClass;
import system.panel.TrainTableModel;

//Test TrainTableModel
public class TrainTableModelTest {
	private SystemDataClass systemdata;
	private TrainTableModel trainmodel;

	@Before
	public void setUp() throws Exception {
		systemdata = new SystemDataClass();
		trainmodel = new TrainTableModel(systemdata.routeList.get(0).trainList.get(0));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetRowCount() {
		assertEquals(2, trainmodel.getRowCount());
	}

	@Test
	public void testGetColumnCount() {
		assertEquals(9, trainmodel.getColumnCount());
	}

	@Test
	public void testGetColumnName() {
		assertEquals("Stations:", trainmodel.getColumnName(0));
	}

	@Test
	public void testGetColumnClass() {
		assertEquals(String.class, trainmodel.getColumnClass(0));
	}

	@Test
	public void testGetValueAt() {
		assertEquals("train1", trainmodel.getValueAt(0, 0));
	}

	@Test
	public void testChangestaus() {
		trainmodel.changestaus();
		assertEquals("->", trainmodel.statusList[0]);
	}

}
