package service.common;

import com.iwebirth.db.model.User;
import com.iwebirth.db.service.common.CommonDao;
import com.iwebirth.util.PrintTools;
import com.iwebirth.util.StaticParam;
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
    public void insertSingleObject(){
        User user = new User("tiantian","adsfgh", StaticParam.USER_LEVEL_PERSONAL, 0);
        commonDao.insertSingleObject(user);
    }

    @Test
    public void getSingleObjectById(){
        Class clazz = User.class;
        Integer id = 1;
        User user = (User)commonDao.getSingleObjectById(clazz,1);
        user.show();
    }

    @Test
    public void getAllObejct(){
        List resList = commonDao.getAllObject(User.class);
        PrintTools.printList(resList);
    }


}
