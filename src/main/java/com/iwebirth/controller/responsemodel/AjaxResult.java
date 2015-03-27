package com.iwebirth.controller.responsemodel;

/**
 * Created by YY_410 on 2015/3/27.
 */
public class AjaxResult {

    boolean success;

    String result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public AjaxResult(boolean success) {
        this.success = success;
    }
}
