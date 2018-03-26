package com.finds.service.lostfinds;

import com.finds.controller.BaseController;
import com.finds.dao.BaseDao;
import com.finds.entity.Taken;
import com.finds.entity.lostfinds.ImageEntity;
import com.finds.entity.lostfinds.LostfindsEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 67552 on 2015/8/31.
 */
@SuppressWarnings("restriction")
@Service("publishService")
public class PublishService {
    @Resource(name = "baseDao")
    BaseDao baseDao;
    public void saveLostfinds(LostfindsEntity lostfindsEntity,Taken taken,String[] imageurl){
        String user_id= baseDao.findUser_id(taken.getTaken());
        lostfindsEntity.setUser_id(user_id);
        baseDao.save(lostfindsEntity);
        for(int i=0;i<imageurl.length;i++){
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setLostfind_id(lostfindsEntity.getId());
            imageEntity.setUrl(BaseController.hosturl+"/Finds/lostfinds/"+imageurl[i]);
            baseDao.save(imageEntity);
        }

    }
}
