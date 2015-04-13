package com.iwebirth.db.service;

import com.iwebirth.db.model.Department;
import com.iwebirth.db.model.User;
import com.iwebirth.db.model.Vehicle;
import com.iwebirth.db.service.common.CommonDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by YY_410 on 2015/3/27.
 */
@Service
public class AdminService {
    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    CommonDao commonDao;
    /**
     * 对Admin中的对象进行update or save
     * *
     */
    public int updateObject(Object obj) {
        int res = CRUDEvent.UPDATE_SUCCESS.getValue();
        Class clazz = obj.getClass();
        try{
            if(clazz == User.class){
                System.out.println("user update");
                User user = (User)obj;
                Integer id = user.getId();
                if(id == 0){
                    //save
                    res = commonDao.insertSingleObject(user);
                }else{
                    //update
                    User dUser = (User)commonDao.getSingleObjectById(User.class,id);
                    if(dUser == null)
                        res = CRUDEvent.UPDATE_EXCEPTION.getValue();
                    else{
                        dUser.setUsername(user.getUsername());
                        dUser.setPassword(user.getPassword());
                        dUser.setUserLevel(user.getUserLevel());
                        dUser.setIsValid(user.getIsValid());
                        dUser.setDepartmentId(user.getDepartmentId());
                    }
                }
            }else if(clazz == Department.class){
                System.out.println("Department update");
                Department department = (Department)obj;
                Integer id = department.getId();
                if(id == 0){
                    res = commonDao.insertSingleObject(department);
                }else{
                    Department dDepart = (Department)commonDao.getSingleObjectById(clazz,id);
                    if(dDepart == null)
                        res = CRUDEvent.UPDATE_EXCEPTION.getValue();
                    else{
                        dDepart.setName(department.getName());
                        dDepart.setLocation(department.getLocation());
                        dDepart.setFunction(department.getFunction());
                        dDepart.setLatitude(department.getLatitude());
                        dDepart.setLongitude(department.getLongitude());
                    }
                }
            }else if(clazz == Vehicle.class){
                System.out.println("vehicle update or save");
                Vehicle vehicle = (Vehicle)obj;
                Integer id = vehicle.getId();
                if(id == 0){
                    res = commonDao.insertSingleObject(vehicle);
                }else{
                    Vehicle dVehicle = (Vehicle)commonDao.getSingleObjectById(clazz,id);
                    dVehicle.setTerminalId(vehicle.getTerminalId());
                    dVehicle.setType(vehicle.getType());
                    dVehicle.setTerminalLicense(vehicle.getTerminalLicense());
                    dVehicle.setBelongId(vehicle.getBelongId());
                    dVehicle.setOriginId(vehicle.getOriginId());
                    dVehicle.setCurrentStatus(vehicle.getCurrentStatus());
                    dVehicle.setRecentRentId(vehicle.getRecentRentId());
                    dVehicle.setRecentSellId(vehicle.getRecentSellId());
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            res = CRUDEvent.UPDATE_EXCEPTION.getValue();
        }
        return res;
    }

    /**
     * 并不是删除记录，而是将记录的账号有效性字段设为false
     * 其实做了一个update操作
     * *
     */
    public int deleteUserById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        int res = CRUDEvent.UPDATE_SUCCESS.getValue();
        try {
            User dUser = (User) session.get(User.class, id);
            if (dUser != null)
                dUser.setIsValid(false);
            else
                res = CRUDEvent.UPDATE_FAIL.getValue();
        } catch (Exception e) {
            e.printStackTrace();
            CRUDEvent.UPDATE_EXCEPTION.getValue();
        }
        return res;
    }

}
