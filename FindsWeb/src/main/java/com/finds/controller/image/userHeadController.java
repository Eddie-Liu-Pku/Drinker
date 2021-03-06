package com.finds.controller.image;

import com.finds.controller.BaseController;
import com.finds.entity.JsonResult;
import com.finds.service.image.UserHeadService;
import com.finds.service.user.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Created by 67552 on 2015/11/4.
 */
@Controller
@RequestMapping("userHeadController")
public class userHeadController extends BaseController {
    @Resource(name = "userHeadService")
    UserHeadService userHeadService;

    @RequestMapping(value = "/changgeHeade.json",method = RequestMethod.POST )
    @ResponseBody

    public JsonResult updetFile(HttpServletRequest request,
                                HttpServletResponse response, HttpSession session){
        List<MultipartFile> files = ((MultipartRequest) request).getFiles("file");
       // System.out.print(files.size());
        String filename = null;
        try{
            for(MultipartFile file :files){
                 filename = file.getOriginalFilename();
                saveFile.SaveFileFromInputStream(file.getInputStream(),headurl, filename);

            }
            //之里把得到的files自己处理下保存文件或其他操作
        }catch(Exception e){
            return getJson(false,"上传失败");
        }
        return getJson(true,"上传成功",filename);
    }
    @RequestMapping(value = "/saveHead.json/{taken}/{image}",method = RequestMethod.GET )
    @ResponseBody
    public JsonResult savaHead(@PathVariable String taken,@PathVariable String image){
        String photourl = hosturl+"/Finds"+"/userhead/"+image;
       userHeadService.updetUserHead(taken,photourl);
        return getJson(true,"保存成功",null);
    }

}
