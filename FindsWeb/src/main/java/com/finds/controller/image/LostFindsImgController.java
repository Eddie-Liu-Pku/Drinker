package com.finds.controller.image;

import com.finds.controller.BaseController;
import com.finds.entity.JsonResult;
import com.finds.service.lostfinds.LostfindsService;
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
 * Created by 67552 on 2015/11/5.
 */
@Controller
@RequestMapping("lostFindsImgController")
public class LostFindsImgController extends BaseController {
    @Resource(name="lostfindsService")
    LostfindsService lostfindsService;

    @RequestMapping(value = "/updetImg.json",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updetFile(HttpServletRequest request,
                                HttpServletResponse response, HttpSession session){
        List<MultipartFile> files = ((MultipartRequest) request).getFiles("file");
        String filename = null;
        try{
            for(MultipartFile file :files){
                filename = file.getOriginalFilename();
                saveFile.SaveFileFromInputStream(file.getInputStream(),lostimgurl, filename);

            }
            //之里把得到的files自己处理下保存文件或其他操作
        }catch(Exception e){
            return getJson(false,"上传失败");
        }
        return getJson(true,"上传成功",filename);
    }
    @RequestMapping(value = "/gettImg.json/{lf_id}",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getIamge(@PathVariable String lf_id){
        Object object = lostfindsService.getImage(lf_id);
        List list = (List) object;
        if(list.size()!=0) {
            return getJson(true, "获取图片成功",list,list.size());
        }
        return getJson(false,"获取图片失败",null);
    }
}
