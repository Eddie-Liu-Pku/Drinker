package com.finds.service.comment;

import com.finds.dao.BaseDao;
import com.finds.entity.Taken;
import com.finds.entity.comment.CommentEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 67552 on 2015/9/1.
 */
@SuppressWarnings("restriction")
@Service("commentService")
public class CommentService {
    @Resource(name = "baseDao")
    BaseDao baseDao;
    public void makeComment(CommentEntity commentEntity,Taken taken){
        Taken taken1 = baseDao.get(Taken.class,taken.getTaken());
        commentEntity.setUser_id(taken1.getUser_id());
        baseDao.save(commentEntity);

    }

}
