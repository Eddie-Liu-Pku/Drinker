package com.finds.controller;

import com.finds.entity.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by 67552 on 2015/8/28.
 */
//朋友一定很好奇这个是干什么的，这个呢就是告诉spring这个类是Controller
@Controller
// 是一个用来处理请求地址映射的注解，可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径
@RequestMapping("/HelloController")
public class HelloController extends BaseController{
    // value： 指定请求的实际地址；
// method： 指定请求的method类型， GET、POST、PUT、DELETE等；
    @RequestMapping(value = "/hello.gson", method = RequestMethod.GET)
// 该注解用于读取Request请求的body部分数据，使用系统默认配置的HttpMessageConverter进行解析，然后把相应的数据绑定到要返回的对象上；
    @ResponseBody
// 那么我们要访问此方法是 完整路径应该是http://ip地址:端口/HelloController/hello.json
    public Object hello() {
        return "Hello SpringMVC!";
    }
    @RequestMapping(value = "/testFile.json",method =RequestMethod.POST )
    @ResponseBody
    public JsonResult updetFile(HttpServletRequest request,
                                HttpServletResponse response, HttpSession session){
        List<MultipartFile> files = ((MultipartRequest) request).getFiles("file");
        System.out.print(files.size());



        try{
            for(MultipartFile file :files){
              String filename = file.getOriginalFilename();
              SaveFileFromInputStream(file.getInputStream(),"/usr/local/tomcat/webapps/Finds/userhead/",filename);

            }


            //之里把得到的files自己处理下保存文件或其他操作

        }catch(Exception e){
           return getJson(false,"上传失败");
        }

        return getJson(true,"上传成功");


    }
    public void SaveFileFromInputStream(InputStream stream,String path,String filename) throws IOException
    {
        FileOutputStream fs=new FileOutputStream( path + "/"+ filename);
        byte[] buffer =new byte[1024*1024];
        int bytesum = 0;
        int byteread = 0;
        while ((byteread=stream.read(buffer))!=-1)
        {
            bytesum+=byteread;
            fs.write(buffer,0,byteread);
            fs.flush();
        }
        fs.close();
        stream.close();
    }
}
