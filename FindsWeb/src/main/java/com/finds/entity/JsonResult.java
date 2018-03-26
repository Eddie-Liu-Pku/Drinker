package com.finds.entity;

import java.io.Serializable;

/**
 * Created by Mic_ on 2015/6/5.
 * 统一的Json数据模板
 */
public class JsonResult implements Serializable{
    public boolean success;
    public String msg;
    public Object data;
    public int totalCount;
    public JsonResult(boolean success, String msg, Object data, int totalCount){
        this.success=success;
        this.msg=msg;
        this.data=data;
        this.totalCount=totalCount;
    }
}
