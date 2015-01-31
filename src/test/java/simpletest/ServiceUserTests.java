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
import com.iwebirth.util.StaticParam;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:spring-base.xml",
	"classpath:springmvc-hibernate.xml"
})
public class ServiceUserTests {
	
	@Autowired
	UserService userService;
	
	@Test
	public void addUser(){
		Department department = new Department("123", "测试123", "测试12", "测试1",StaticParam.DEPARTMENT_STATUS_EV_SELLER);
		User user = new User("test", "test", StaticParam.USER_LEVEL_NORMAL, department, System.currentTimeMillis());
		System.out.println(CRUDEvent.getNameByValue(userService.addUser(user)));
	}
	
}
