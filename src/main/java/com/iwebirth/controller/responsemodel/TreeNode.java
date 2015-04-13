package com.iwebirth.controller.responsemodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YY_410 on 2015/4/13.
 */
public class TreeNode {

    private String text;
    /*attr from extjs's prototype*/
    private String iconCls;//node's icon

    private boolean expanded = false;//node auto_expanded(default to not expanded)

    private List children;

    public List getChildren() {
        if(children == null)
            children = new ArrayList();
        return children;
    }

    public void setChildren(List children) {
        this.children = children;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public TreeNode(String text, String iconCls, boolean expanded) {
        this.text = text;
        this.iconCls = iconCls;
        this.expanded = expanded;
    }
}
