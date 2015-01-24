package simpletest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iwebirth.db.model.Department;
import com.iwebirth.db.service.DepartmentService;
import com.iwebirth.util.StaticParam;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/resources/spring-base.xml",
	"file:src/main/resources/springmvc-hibernate.xml"
})
public class DepartmentServiceTests {
	
	@Autowired
	DepartmentService departmentService;
	
	@Test
	public void addDepartment(){
		Department department = new Department("a", "b", "c", "d", StaticParam.DEPARTMENT_STATUS_BSUSER.name());
		departmentService.addDepartment(department);
	}
	
	@Test
	public void updateDepartment(){
		Department department = new Department("a1", "b", "c", "d", StaticParam.DEPARTMENT_STATUS_BSUSER.name());
		departmentService.updateDepartment(1, department);
	}
}
