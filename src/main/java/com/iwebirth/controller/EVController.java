package com.iwebirth.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iwebirth.controller.responsemodel.EVTreeResponseLeaf;
import com.iwebirth.controller.responsemodel.EVTreeResponseNode;
import com.iwebirth.controller.responsemodel.TreeResponseRoot;

/**
 * @author YY_410
 * 2015-1-24
 * Engineering Van's Controller
 * **/
@Controller
@RequestMapping("/ev")
public class EVController {
	@RequestMapping(value="/info/treegrid",method=RequestMethod.GET)
	public
	@ResponseBody TreeResponseRoot<EVTreeResponseNode> getEVinfoAsTreegrid(@RequestParam String username,@RequestParam(required=false) String catagory){
		System.out.println("username"+username+"  catagory:"+catagory);
		EVTreeResponseLeaf leaf_one = new EVTreeResponseLeaf("1", "a", "b", "rs", "treeleaf", "qtip_one",true);
		EVTreeResponseLeaf leaf_two = new EVTreeResponseLeaf("2", "a", "b", "rs", "treeleaf", "我是2号",true);
		EVTreeResponseLeaf leaf_three = new EVTreeResponseLeaf("3", "a", "b", "rs", "treeleaf","我是2号", true);
		EVTreeResponseLeaf leaf_four = new EVTreeResponseLeaf("4", "a", "b", "rs", "treeleaf","qtip_one", true);
		EVTreeResponseLeaf leaf_five = new EVTreeResponseLeaf("5", "a", "b", "rs", "treeleaf","qtip_one", true);
		ArrayList<EVTreeResponseLeaf> children_one = new ArrayList<EVTreeResponseLeaf>();
		children_one.add(leaf_one);
		children_one.add(leaf_two);
		children_one.add(leaf_three);
		for(int i=0;i<100;i++){
			EVTreeResponseLeaf leaf = new EVTreeResponseLeaf("1", "a", "b", "rs", "treeleaf", "",true);
			children_one.add(leaf);
		}
		ArrayList<EVTreeResponseLeaf> children_two = new ArrayList<EVTreeResponseLeaf>();
		children_two.add(leaf_four);
		children_two.add(leaf_five);
		EVTreeResponseNode node_one = new EVTreeResponseNode("类型1", "treenode", true,children_one);
		EVTreeResponseNode node_two = new EVTreeResponseNode("类型2", "treenode", false,children_two);
		ArrayList<EVTreeResponseNode> node_children = new ArrayList<EVTreeResponseNode>();
		node_children.add(node_one);
		node_children.add(node_two);
		TreeResponseRoot<EVTreeResponseNode> root = new TreeResponseRoot<EVTreeResponseNode>(node_children);
		return root;
	}
}
