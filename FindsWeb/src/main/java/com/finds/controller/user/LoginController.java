package com.finds.controller.user;

import com.finds.controller.BaseController;
import com.finds.entity.JsonResult;
import com.finds.entity.Taken;
import com.finds.entity.user.UserEntity;
import com.finds.service.user.LoginService;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 67552 on 2015/8/28.
 */
@Controller
@RequestMapping("/LoginController")
public class LoginController extends BaseController{
    /**路径参数{name}填入用户名，{pass}填入密码。我们设置为GET请求。
      value： 指定请求的实际地址；
      method： 指定请求的method类型， GET、POST、PUT、DELETE等；
     */
    @Resource(name = "loginService")
    LoginService loginService;

    @RequestMapping(value = "/login_get.json/{schuid}/{pass}", method = RequestMethod.GET)

    /** 该注解用于读取Request请求的body部分数据，使用系统默认配置的HttpMessageConverter进行解析，然后把相应的数据绑定到要返回的对象上；
        */
    @ResponseBody

    /** 那么我们要访问此方法是 完整路径应该是http://ip地址:端口/HelloController/hello.json
    */
    public JsonResult login(@PathVariable String schuid, @PathVariable String pass) {


        List<Criterion> criterions = new ArrayList<Criterion>();
        criterions.add(Restrictions.eq("schuid", schuid));
        criterions.add(Restrictions.eq("psw", pass));

        Object object = loginService.getUser(criterions);//数据库查询条件

       if( object.equals(false)) {
        return  getJson(false,"登陆失败","用户名或密码错误");
       }
        else {
           UserEntity userEntity  = (UserEntity) object;
           Map m = new HashMap();

           String username = userEntity.getName();
           String userphoto =userEntity.getUserphoto();
           Taken taken = new Taken();
           taken.setUser_id(userEntity.getId());
           loginService.saveTaken(taken);
           //return  getJson(true,"登录成功",taken.getTaken());
           return getJson(true, "登陆成功",username+"|"+userphoto+"|"+taken.getTaken());
       }
    }
    @RequestMapping(value = "/logout_get.json/{taken}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult logout(@PathVariable String taken){
        loginService.deleteTaken(taken);
        return getJson(true,"登出成功",null);

    }

}
