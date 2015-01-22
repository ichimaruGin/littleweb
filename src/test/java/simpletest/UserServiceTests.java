package simpletest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iwebirth.controller.responsemodel.LoginResponse;
import com.iwebirth.controller.responsemodel.LoginStatus;
import com.iwebirth.db.model.Department;
import com.iwebirth.db.model.User;
import com.iwebirth.db.service.CRUDEvent;
import com.iwebirth.db.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/resources/spring-base.xml",
	"file:src/main/resources/springmvc-hibernate.xml"
})
public class UserServiceTests {
	
	@Autowired
	UserService userService;
	
	@Test
	public void addUser(){
		Department department = new Department("测试NAME", "测试LOCATION", "测试latitude", "测试longitude");
		User user = new User("admin", "admin", LoginStatus.ADMIN, department, System.currentTimeMillis());
		System.out.println(CRUDEvent.getNameByValue(userService.addUser(user)));
	}
}
