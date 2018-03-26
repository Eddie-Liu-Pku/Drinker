package com.finds.service.user;

import com.finds.dao.BaseDao;
import com.finds.entity.Taken;
import com.finds.entity.user.UserEntity;
import com.finds.entity.user.UserInfoEntity;
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
@Service("userService")
public class UserService {
    @Resource(name = "baseDao")
    BaseDao baseDao;
    public void userSave(UserEntity userEntity){
        baseDao.save(userEntity);
    }
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
    public void savaUserinfo(UserInfoEntity userInfoEntity){
        baseDao.save(userInfoEntity);
    }
    public void updetUserinfo(UserInfoEntity userInfoEntity){
        baseDao.update(userInfoEntity);
    }
    public Object getUserinfo(String taken){
        String user_id = baseDao.findUser_id(taken);
          if(user_id!=null){
              String sql ="SELECT a.faculty,a.myclass,a.grade,a.sign,b.name,b.userphoto FROM userinfo a JOIN user b on a.user_id = b.id WHERE  a.user_id=:user_id";
              Map m = new HashMap();
              m.put("user_id",user_id);
              return baseDao.getBySql(sql,m);
          }
        return null;
    }
}
