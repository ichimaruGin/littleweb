package simpletest;

import com.iwebirth.db.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:spring-base.xml",
	"classpath:springmvc-hibernate.xml"
})
public class ServiceUserTests {
	
	@Autowired
	UserService userService;

	
}
