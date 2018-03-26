package com.finds.controller;

import com.finds.entity.JsonResult;

import javax.servlet.http.HttpSession;

/**
 * Created by Mic_ on 2015/6/4.
 * 基本控制器
 */
public class BaseController {
    public final String SUFFIX =".action";
    public final String JSON_SUFFIX =".do";
    public static String hosturl = "http://115.29.109.206:8080";
    public static String headurl="/usr/local/tomcat/webapps/Finds/userhead";
    public static String lostimgurl ="/usr/local/tomcat/webapps/Finds/lostfinds";

    /**
     * 统一JSON格式
     */
    public JsonResult getJson(boolean success,Object data){
        return new JsonResult(success,null,data,99999);
    }
    public JsonResult getJson(boolean success,String msg,Object data){
        return new JsonResult(success,msg,data,99999);
    }
    public JsonResult getJson(boolean success,String msg,Object data,int totalCount){
        return new JsonResult(success,msg,data,totalCount);
    }
    public JsonResult getJson(boolean success,Object data,int totalCount){
        return new JsonResult(success,null,data,totalCount);
    }

   /* //HttpSession session  getLoginInfo(session);
    public LoginInfo getLoginInfo(HttpSession session){
       LoginInfo loginInfo= (LoginInfo)session.getAttribute("LoginInfo");
        return loginInfo;
    }*/

}
