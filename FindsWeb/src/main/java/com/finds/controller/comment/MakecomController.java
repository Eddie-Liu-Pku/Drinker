package com.finds.controller.comment;

import com.finds.controller.BaseController;
import com.finds.entity.JsonResult;
import com.finds.entity.Taken;
import com.finds.entity.comment.CommentEntity;
import com.finds.service.comment.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by 67552 on 2015/9/1.
 */
@Controller
@RequestMapping("/makecomController")
public class MakecomController extends BaseController {
    @Resource(name="commentService")
    CommentService commentService;
    @RequestMapping(value = "/makecom_post.json", method = RequestMethod.POST)
    public JsonResult makeComment(@ModelAttribute CommentEntity commentEntity,@ModelAttribute Taken taken){
        commentService.makeComment(commentEntity,taken);
        return getJson(true,"发表评论成功",null);
    }

}
