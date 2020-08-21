package system.testcase;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import system.dataclass.RouteClass;
import system.dataclass.SystemDataClass;
import system.dataclass.TrainTimeClass;
import system.panel.ModifyRouteFrame;

public class ModifyRouteFrameTest {
	private ModifyRouteFrame frame;
	private SystemDataClass systemdata;

	@Before
	public void setUp() throws Exception {
		systemdata = new SystemDataClass();
		frame = new ModifyRouteFrame(systemdata);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testModifyrouteFrame() {
		assertNotNull(frame);
	}
	
	@Test
	public void testSaveChange() {
		int routenum = systemdata.routeList.size();
		RouteClass newVo = new RouteClass();
		newVo.routeName = "route1";
		for(int i=0;i<4;i++){
			newVo.stationList.add(i+"_station");
		}
		
		newVo.trainList = systemdata.routeList.get(0).trainList;
		for(TrainTimeClass train : newVo.trainList){
			train.stationList = newVo.stationList;
		}
		int index = systemdata.routeList.indexOf(systemdata.routeList.get(0));
		assertEquals(routenum, systemdata.routeList.size());
	}
	
	
	//add a new route
	@Test
	public void testAddroute() {
		int routenum = systemdata.routeList.size();
		if(routenum<3){
			RouteClass newVo = new RouteClass();
			newVo.routeName = "newroute";
			for(int i=0;i<4;i++){
				newVo.stationList.add(i +"_station");
			}
			systemdata.routeList.add(newVo);
			systemdata.changeNum();
			assertEquals(routenum+1, systemdata.routeList.size());
		}
		
	}

}
