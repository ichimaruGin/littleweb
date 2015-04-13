package com.iwebirth.controller.responsemodel;

import java.util.ArrayList;

/**
 * @author YY_410
 *         2015-1-24
 *         this class is represents the root node
 *         *
 */
//T为孩子节点的类(eg:EVTreeResponseNode)
public class TreeRoot<T> {

    String text = ".";

    ArrayList<T> children;

    public ArrayList<T> getChildren() {
        if(children == null)
            children = new ArrayList<T>();
        return children;
    }

    public void setChildren(ArrayList<T> children) {
        this.children = children;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
