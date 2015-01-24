package com.iwebirth.controller.responsemodel;

import java.util.ArrayList;

/**
 * @author YY_410
 * 2015-1-24
 * Engineering Van's gridtree data model
 * this class is represents the tree node
 * **/
//属性名称要和EVInfoModel.js中的field进行11对应
public class EVTreeResponseNode {
	//attr from EVinfo.class
	String tid;  // not the really tid, but refer to folder name (here I user the EVinfo.tKind)
	
	/*attr from extjs's prototype*/	
	String iconCls;//node's icon
		
	boolean expanded = true;//node auto_expanded(default to expanded)
	
	ArrayList<EVTreeResponseLeaf> children;

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}



	public ArrayList<EVTreeResponseLeaf> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<EVTreeResponseLeaf> children) {
		this.children = children;
	}



	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public EVTreeResponseNode(String tid, String iconCls, boolean expanded,
			ArrayList<EVTreeResponseLeaf> children) {
		super();
		this.tid = tid;
		this.iconCls = iconCls;
		this.expanded = expanded;
		this.children = children;
	}





	
	
	
}
