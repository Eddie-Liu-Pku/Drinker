package com.finds.controller.user;

import com.finds.controller.BaseController;
import com.finds.entity.JsonResult;
import com.finds.entity.user.UserEntity;

import com.finds.service.user.UserService;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 67552 on 2015/8/28.
 */
@Controller
@RequestMapping("RegisteredController")
public class RegisteredController extends BaseController {
         /**由于是提交数据所以我们这里使用POST请求
            */
         @Resource(name = "userService")
         UserService userService;
        @RequestMapping(value = "/registered_post.json",method = RequestMethod.POST)
        @ResponseBody
         /** 此处@ModelAttribute 可以吧POST请求所携带的json字符串自动解析成后面所写的实体类
        然后就直接可以通过这个UserEntity取到Android端传过来的数据
            */

          public JsonResult registered(@ModelAttribute UserEntity entity){
            String name = entity.getName();
            String psw = entity.getPsw();
            String schuid = entity.getSchuid();
            String phone = entity.getPhone();
            List<Criterion> criterions1 = new ArrayList<Criterion>();
            List<Criterion> criterions2 = new ArrayList<Criterion>();
            criterions1.add(Restrictions.eq("schuid", schuid));
            criterions2.add(Restrictions.eq("name",name));
           // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            //System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
            //格式yyyy-MM-dd HH:mm:ss
            Date uptime = new Date();
            entity.setUptime(uptime);
            entity.setLfnum(0);

            if (name != null && psw != null&&schuid!=null &&phone!=null) {
              /** 成功返回消息告诉Android端
               */
            if(!userService.getUser(criterions1).equals(false)){
                return  getJson(false,"注册失败","学号已存在");
            }
                else if(!userService.getUser(criterions2).equals(false)){
                return  getJson(false,"注册失败","用户名已存在");
            }
                else {
                userService.userSave(entity);
                Map<String, String> map = new HashMap<String, String>();
                return getJson(true, "注册成功", entity.getId());
            }
            } else {
             /**失败返回消息告诉Android端
              */
              Map<String, String> map = new HashMap<String, String>();
                return  getJson(false,"注册失败","填写数据不完整");
            }
        }
}
