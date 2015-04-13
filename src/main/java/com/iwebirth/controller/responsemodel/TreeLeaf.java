package com.iwebirth.controller.responsemodel;

/**
 * Created by YY_410 on 2015/4/13.
 */
public class TreeLeaf {

    String text;
    /*attr from extjs's prototype*/
    String iconCls;//node's icon

    String qtip;//row's tip

    boolean leaf = true;//node is leaf(default is true)

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

    public String getQtip() {
        return qtip;
    }

    public void setQtip(String qtip) {
        this.qtip = qtip;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public TreeLeaf(String text, String qtip, String iconCls) {
        this.text = text;
        this.qtip = qtip;
        this.iconCls = iconCls;
    }
}
