package com.finds.controller.lostfinds;

import com.finds.controller.BaseController;
import com.finds.entity.JsonResult;
import com.finds.service.lostfinds.LostfindsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 67552 on 2015/9/1.
 */
@Controller
@RequestMapping("/LostfindsController")
public class LostfindsController extends BaseController{
    @Resource(name="lostfindsService")
    LostfindsService lostfindsService;
    @RequestMapping(value = "/mylostfinds_get.json/{taken}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getMylostfinds(@PathVariable String taken){
        List list = new ArrayList();
        list = lostfindsService.getMylostfinds(taken);
        return getJson(true,"获取我的发布", list,list.size());
    }
    @RequestMapping(value = "/deletemylostfinds_post.json/{lostfinds_id}/{taken}",method = RequestMethod.GET )
    @ResponseBody
    public  JsonResult deleteMylostfinds(@PathVariable String lostfinds_id,@PathVariable String taken){
        if(lostfindsService.deleteMylsotfinds(lostfinds_id,taken)) {
            return getJson(true, "删除成功", null);
        }
        return getJson(false,"删除失败",null);
    }
    @RequestMapping(value = "/alllost_get.json/{taken}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getAllLost(@PathVariable String taken){
       Object object =lostfindsService.getAllLost(taken);
        if(object.equals(false)){
            return  getJson(false,"获取失败","用户身份错误");
        }
        else {
            List list = (List) object;
            return getJson(true, "获取失物信息", list, list.size());
        }
        }
    @RequestMapping(value = "/allfinds_get.json/{taken}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getAllFinds(@PathVariable String taken){
        Object object =lostfindsService.getAllFinds(taken);
        if(object.equals(false)){
            return  getJson(false,"获取失败","用户身份错误");
        }
        else {
            List list = (List) object;
            return getJson(true, "获取招领信息", list, list.size());
        }
    }
    @RequestMapping(value = "/lostfindsinfo_get.json/{lf_id}/{taken}",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getLostfindsinfo(@PathVariable String lf_id,@PathVariable String taken){
        Object object =lostfindsService.getLostFindsInfo(lf_id,taken);
        if(object.equals(false)){
            return  getJson(false,"获取失败","用户身份错误");
        }
        else {
            List list = (List) object;
            return getJson(true,"获取信息详细内容成功",list,list.size());
        }
    }
}


