package data.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by YY_410 on 2015/4/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring-base.xml",
        "classpath:springmvc-hibernate.xml"
})
public class MockData {
    @Test
    public void mockVehicle(){
        String teminalId = "zj0571";
        String type =  10 * Math.random() < 5 ? ( 10 * Math.random() < 5 ? "A" : "B") : (10 * Math.random() < 5 ? "C" : "D");
    }
}
