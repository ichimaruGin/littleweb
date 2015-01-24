package com.iwebirth.controller.responsemodel;

import java.util.ArrayList;

/**
 * @author YY_410
 * 2015-1-24
 * this class is represents the root node
 * **/
//T为孩子节点的类(eg:EVTreeResponseNode)
public class TreeResponseRoot<T> {
	String text = ".";
	ArrayList<T> children;
	
	public ArrayList<T> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<T> children) {
		this.children = children;
	}
	public TreeResponseRoot(ArrayList<T> children) {
		super();
		this.children = children;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	
}
