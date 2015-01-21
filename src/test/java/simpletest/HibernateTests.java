package simpletest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/resources/springmvc-hibernate.xml"
})
public class HibernateTests {
	@Test
	public void test(){
		System.out.println("Hibertane is OK");
	}
}
