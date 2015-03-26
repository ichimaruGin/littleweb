package HiernateTests.dao;

import com.iwebirth.db.model.User;
import com.iwebirth.db.service.common.CommonDao;
import com.iwebirth.util.PrintTools;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by YY_410 on 2015/3/26.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring-base.xml",
        "classpath:springmvc-hibernate.xml"
})
public class commonDaoTests {

    @Autowired
    CommonDao commonDao;

    @Test
    public void init(){
        System.out.println("Hello"+ Integer.MAX_VALUE+System.currentTimeMillis());
    }

    @Test
    public void getAllObejct(){
        List resList = commonDao.getAllObject(User.class);
        PrintTools.printList(resList);
    }
}
