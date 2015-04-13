package com.iwebirth.controller;

import com.iwebirth.controller.responsemodel.TreeLeaf;
import com.iwebirth.controller.responsemodel.TreeNode;
import com.iwebirth.controller.responsemodel.TreeRoot;
import com.iwebirth.db.service.VehicleService;
import com.iwebirth.util.Jackson;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by YY_410 on 2015/4/13.
 */
@Controller
@RequestMapping(value={"/vehicle"})
public class VehicleController {

    @Autowired
    VehicleService vehicleService;
    //test
    @RequestMapping(value="/tree/test",method = RequestMethod.GET)
    public
    @ResponseBody
    TreeRoot createTotalVehicleTreeAccordToUserNameTest(@RequestParam(required=false) String username,@RequestParam(required=false) String category){
        System.out.println("/vehicle/tree/test");
        TreeRoot<TreeNode> root = new TreeRoot<TreeNode>();
        try{
            for(int i=0;i<3;i++){
                TreeNode node = new TreeNode("node"+i,"treenode",false);
                for(int j=0;j<5;j++){
                    TreeLeaf leaf= new TreeLeaf("leaf"+i,i+"","treeleaf");
                    node.getChildren().add(leaf);
                }
                root.getChildren().add(node);
            }
            System.out.println(Jackson.writeValueAsString(root));
        }catch(Exception e){
            e.printStackTrace();
        }
        return root;
    }

    //category作为树的中间层
    @RequestMapping(value="/tree/category",method = RequestMethod.GET)
    public
    @ResponseBody
    TreeRoot createTotalVehicleTreeAccordToUserName(@RequestParam String username,@RequestParam(required=false) String category){
        System.out.println("/vehicle/tree");
        TreeRoot<TreeNode> root = new TreeRoot<TreeNode>();
        try{
            if(category != null && category.length() > 0)
                root = vehicleService.getVehicleTreeByName(username,category);
            System.out.println(Jackson.writeValueAsString(root));
        }catch(Exception e){
            e.printStackTrace();
        }
        return root;
    }
}
