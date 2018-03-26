package com.finds.controller.user;

import com.finds.controller.BaseController;
import com.finds.entity.JsonResult;
import com.finds.entity.Taken;
import com.finds.entity.user.UserInfoEntity;
import com.finds.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/10/20.
 */
@Controller
@RequestMapping("UserInfoController")
public class UserInfoController extends BaseController {
    @Resource(name = "userService")
    UserService userService;
    @RequestMapping(value = "/saveuserinfo_post.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult savaUserInfo(@ModelAttribute UserInfoEntity userInfoEntity){

        userService.savaUserinfo(userInfoEntity);
        return getJson(true,"保存成功",null);

    }
    @RequestMapping(value = "updetuserinfo_post.json",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updetUserInfo(@ModelAttribute UserInfoEntity userInfoEntity){
        UserInfoEntity userInfoEntity1 =userInfoEntity;
        userInfoEntity1.setId("d7e509ea-3177-40c6-8e98-d0c43f29a9a5");
        userService.updetUserinfo(userInfoEntity1);
         return  getJson(true,"更新成功",null);
    }
    @RequestMapping(value = "getuserinfo_get.json/{taken}",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getUserinfo(@PathVariable String taken){
        Object o = userService.getUserinfo(taken);
        if(o!=null){
            return getJson(true,"获取个人信息成功",o);
        }
        return getJson(false,"获取个人信息失败",null);
    }
}
