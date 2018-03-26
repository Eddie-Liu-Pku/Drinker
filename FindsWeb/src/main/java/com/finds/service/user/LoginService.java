package com.finds.service.user;

import com.finds.dao.BaseDao;
import com.finds.entity.Taken;
import com.finds.entity.user.UserEntity;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 67552 on 2015/8/30.
 */
@SuppressWarnings("restriction")
@Service("loginService")
public class LoginService {
    @Resource(name = "baseDao")
    BaseDao baseDao;
    public Object getUser(List<Criterion> criterions){
        List<UserEntity> list =baseDao.getAll(UserEntity.class,criterions);
        if(list.size()==0)
        {

            return false;// 登录失败给Android端返回一个错误消息提醒用户
        }
        else {
            return list.get(0);
        }
    }
    public void delteUser(String use_id){
        baseDao.delete(UserEntity.class, use_id);
    }
    public void saveTaken(Taken taken){
        baseDao.save(taken);
    }
    public void deleteTaken(String taken){
        baseDao.delete(baseDao.get(Taken.class,taken));
    }
}
