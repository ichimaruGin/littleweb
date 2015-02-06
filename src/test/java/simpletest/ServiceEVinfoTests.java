package simpletest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iwebirth.db.model.Department;
import com.iwebirth.db.model.EVinfo;
import com.iwebirth.db.service.CRUDEvent;
import com.iwebirth.db.service.DepartmentService;
import com.iwebirth.db.service.EVinfoService;
import com.iwebirth.util.StaticParam;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:spring-base.xml",
	"classpath:springmvc-hibernate.xml"
})
public class ServiceEVinfoTests {
	@Autowired
	DepartmentService departmentService;
	@Autowired
	EVinfoService eVinfoService;
	
//	@Test
//	public void addEVinfo(){
//		Department department = departmentService.getDepartmentById(1);
//		EVinfo ev1 = new EVinfo("ev05710001", "浙A00001", StaticParam.EVINFO_KIND_KINDONE, department,department);
//		EVinfo ev2 = new EVinfo("ev05710002", "浙A00002", StaticParam.EVINFO_KIND_KINDONE, department,department);
//		EVinfo ev3 = new EVinfo("ev05710003", "浙A00003", StaticParam.EVINFO_KIND_KINDONE, department,department);
//		EVinfo ev4 = new EVinfo("ev05710004", "浙A00004", StaticParam.EVINFO_KIND_KINDONE, department,department);
//		EVinfo ev5 = new EVinfo("ev05710005", "浙A00005", StaticParam.EVINFO_KIND_KINDONE, department,department);
//		EVinfo ev6 = new EVinfo("ev05710006", "浙A00006", StaticParam.EVINFO_KIND_KINDONE, department,department);
//		EVinfo ev7 = new EVinfo("ev05710007", "浙A00007", StaticParam.EVINFO_KIND_KINDONE, department,department);
//		EVinfo ev8 = new EVinfo("ev05710008", "浙A00008", StaticParam.EVINFO_KIND_KINDONE, department,department);
//		EVinfo ev9 = new EVinfo("ev05710009", "浙A00009", StaticParam.EVINFO_KIND_KINDONE, department,department);
//		EVinfo ev10 = new EVinfo("ev0571000A", "浙A0000A", StaticParam.EVINFO_KIND_KINDONE, department,department);
//		//System.out.println(CRUDEvent.getNameByValue(eVinfoService.addEVinfo(ev1)));
//		List<EVinfo> evs = new ArrayList<EVinfo>();
//		evs.add(ev1);evs.add(ev2);evs.add(ev3);evs.add(ev4);evs.add(ev5);evs.add(ev6);evs.add(ev7);evs.add(ev8);evs.add(ev9);evs.add(ev10);
//		System.out.println(CRUDEvent.getNameByValue(eVinfoService.addEVinfo(evs)));
//	}

    @Test
    public void getEVinfoByUser(){
        String userName = "seller";
        List<EVinfo> evs = eVinfoService.getEVinfoByUser(userName);
        for(EVinfo ev : evs){
            System.out.println(ev.toString());
        }
    }

    @Test
    public void getEVinfoAccordingKind(){
        String userName = "seller";
        Map<String,List> map = eVinfoService.getEVinfoAsTreeStore(userName);
        Set<String> keys = map.keySet();
        for(String key : keys){
            List<EVinfo> l_ev = map.get(key);
            for(EVinfo ev : l_ev){
                System.out.println(key+"  "+ev.getLicenseNumber());
            }
        }
    }
}
