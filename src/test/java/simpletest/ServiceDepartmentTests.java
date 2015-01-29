package simpletest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iwebirth.db.model.Department;
import com.iwebirth.db.service.CRUDEvent;
import com.iwebirth.db.service.DepartmentService;
import com.iwebirth.util.StaticParam;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/resources/spring-base.xml",
	"file:src/main/resources/springmvc-hibernate.xml"
})
public class ServiceDepartmentTests {
	
	@Autowired
	DepartmentService departmentService;
	
	@Test
	public void addDepartment(){
		Department department = new Department("1号EVseller公司", "b", "c", "d", StaticParam.DEPARTMENT_STATUS_EV_SELLER);
		System.out.println(CRUDEvent.getNameByValue(departmentService.addDepartment(department)));
		Department department1 = new Department("1号SBbuyer公司", "b", "c", "d", StaticParam.DEPARTMENT_STATUS_SB_BUYER);
		System.out.println(CRUDEvent.getNameByValue(departmentService.addDepartment(department1)));
		Department department2 = new Department("admin","0","0","0",StaticParam.USER_LEVEL_ADMIN);
		System.out.println(CRUDEvent.getNameByValue(departmentService.addDepartment(department2)));
		Department department3 = new Department("parent","0","0","0",StaticParam.USER_LEVEL_PARENT);
		System.out.println(CRUDEvent.getNameByValue(departmentService.addDepartment(department3)));
	}
	
	@Test
	public void updateDepartment(){
		Department department = new Department("1号EVbuyer公司", "bb", "cv", "df", StaticParam.DEPARTMENT_STATUS_EV_BUYER);
		System.out.println(CRUDEvent.getNameByValue(departmentService.updateDepartment(2, department)));
		department = new Department("1号SBseller公司", "bb", "cv", "df", StaticParam.DEPARTMENT_STATUS_SB_SELLER);
		System.out.println(CRUDEvent.getNameByValue(departmentService.updateDepartment(4, department)));
	}
}
