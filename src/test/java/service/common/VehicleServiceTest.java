package service.common;

import com.iwebirth.controller.responsemodel.TreeRoot;
import com.iwebirth.db.service.UserService;
import com.iwebirth.db.service.VehicleService;
import com.iwebirth.util.Jackson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class VehicleServiceTest {
    @Autowired
    VehicleService vehicleService;
    @Autowired
    UserService userService;
    @Test
    public void getVehicleTreeByName(){
        TreeRoot root = vehicleService.getVehicleTreeByName("ev_seller", "type");
        try {
            System.out.println(Jackson.writeValueAsString(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
