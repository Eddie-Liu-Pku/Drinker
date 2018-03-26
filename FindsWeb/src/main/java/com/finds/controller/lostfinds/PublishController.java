package com.finds.controller.lostfinds;

import com.finds.controller.BaseController;
import com.finds.entity.ImageStr;
import com.finds.entity.JsonResult;
import com.finds.entity.Taken;
import com.finds.entity.lostfinds.LostfindsEntity;
import com.finds.entity.user.UserEntity;
import com.finds.service.lostfinds.PublishService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 67552 on 2015/8/31.
 */
@Controller
@RequestMapping("/PublishController")
public class PublishController extends BaseController{
        @Resource(name="publishService")
        PublishService publishService;
    @RequestMapping(value = "/published_post.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult registered(@ModelAttribute LostfindsEntity lostfindsEntity,@ModelAttribute Taken taken,@ModelAttribute ImageStr image) {
        String imagestr = image.getImage();
        String[] imageurl =imagestr.split("\\|");
        Map map = new HashMap();
        if(lostfindsEntity.getThing()!=null&&lostfindsEntity.getAddress()!=null){
            lostfindsEntity.setPubtime(new Date());
            lostfindsEntity.setIsget(0);
            publishService.saveLostfinds(lostfindsEntity,taken,imageurl);

          return getJson(true,"发表成功",lostfindsEntity.getId());
        }
        else {
         return getJson(false,"发布失败",null);

        }
    }


}
