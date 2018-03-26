package com.finds.service.image;

import com.finds.controller.BaseController;
import com.finds.dao.BaseDao;
import com.finds.entity.user.UserEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

/**
 * Created by 67552 on 2015/11/4.
 */
@SuppressWarnings("restriction")
@Service("userHeadService")
public class UserHeadService {
    @Resource(name = "baseDao")
    BaseDao baseDao;
    public void updetUserHead(String taken,String photourl){
        String user_id = baseDao.findUser_id(taken);
        UserEntity userEntity = baseDao.get(UserEntity.class,user_id);
        String odlurl = userEntity.getUserphoto();
        if(!odlurl.equals("")){
            try {
                String s = getFilename(odlurl);
                File file = new File(BaseController.headurl+"/"+s);
                file.delete();
                userEntity.setUserphoto(photourl);
                baseDao.update(userEntity);
            }
            catch (Exception e){
                userEntity.setUserphoto("");
                baseDao.update(userEntity);

                e.printStackTrace();
            }

        }
        else {
            userEntity.setUserphoto(photourl);
            baseDao.update(userEntity);
        }

    }
    public String getFilename(String odlurl){
        String[] s = odlurl.split("/");
        return s[5];
    }
}
