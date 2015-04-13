package com.iwebirth.db.service;

import com.iwebirth.controller.responsemodel.TreeLeaf;
import com.iwebirth.controller.responsemodel.TreeNode;
import com.iwebirth.controller.responsemodel.TreeRoot;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by YY_410 on 2015/4/13.
 */
@Service
public class VehicleService {
    @Autowired
    SessionFactory sessionFactory;

    public TreeRoot getVehicleTreeByName(String username,String category){
        TreeRoot root = new TreeRoot();
        try{
            Session session = sessionFactory.getCurrentSession();
            String sql0 = "select t.department_id from user t where username = :dusername";
            Integer departId = (Integer)session.createSQLQuery(sql0).setString("dusername",username).uniqueResult();
            if(departId == null){
                System.out.println("用户名对应部门id为空");
            }else{
                System.out.println("departId="+departId);
                String sql1 = "select t.function from department t where id = :dDepartId";
                String function = (String)session.createSQLQuery(sql1).setInteger("dDepartId",departId).uniqueResult();
                System.out.println("function="+function);
                if(function.endsWith("seller")){
                    //作为销售方 以origin_department_id作为条件
                    HashMap<String,TreeNode> typeMap = new HashMap<String,TreeNode>();
                    if("type".equalsIgnoreCase(category)){
                        //其实不需要group by(这边以type字段作为树的中间node)
                        String sql2 = "select t.type, t.terminal_id, COUNT(*) count from (select v.type, v.terminal_id from vehicle v where origin_department_id ="+departId+") t group by t.type,t.terminal_id";
                        List<Object[]> rows = session.createSQLQuery(sql2).list();
                        for(int i=0;i<rows.size();i++){
                            Object[] row = rows.get(i);
                            if(!typeMap.containsKey(row[0])){
                                TreeNode node = new TreeNode((String)row[0],"treenode",false);
                                node.getChildren().add(new TreeLeaf((String)row[1],(String)row[1],"treeleaf"));
                                typeMap.put((String)row[0],node);
                            }else{
                                TreeNode node = typeMap.get(row[0]);
                                node.getChildren().add(new TreeLeaf((String) row[1], (String) row[1], "treeleaf"));
                                typeMap.put((String)row[0],node);
                            }
                        }
                    }else{

                    }
                    for(String key : typeMap.keySet()){
                        root.getChildren().add(typeMap.get(key));
                    }
                }else{
                    //作为管理房 以belong_department_id作为条件
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return root;
    }
}
