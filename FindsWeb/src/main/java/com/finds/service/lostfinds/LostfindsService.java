package com.finds.service.lostfinds;

import com.finds.dao.BaseDao;
import com.finds.entity.lostfinds.LostfindsEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 67552 on 2015/9/1.
 */

@SuppressWarnings("restriction")
@Service("lostfindsService")
public class LostfindsService {
    @Resource(name = "baseDao")
    BaseDao baseDao;
    public List getMylostfinds(String taken){
        String sql= "SELECT a.id,a.thing,a.address,a.pubtime,a.isget FROM lostfinds a JOIN taken b on a.user_id=b.user_id AND b.taken=:taken ORDER BY a.pubtime DESC";
        Map m = new HashMap();
        m.put("taken",taken);
        return baseDao.getBySql(sql,m);
    }
    public Boolean deleteMylsotfinds(String lostfinds_id,String taken){
        String user_id = baseDao.findUser_id(taken);
        if(user_id!=null){
            baseDao.delete(LostfindsEntity.class,lostfinds_id);
            return true;
        }
        return false;
    }


    public Object getAllLost(String taken){
        String user_id = baseDao.findUser_id(taken);
        if(user_id!=null){
            String sql1= "SELECT a.id,a.thing,a.address,a.pubtime,b.name,b.userphoto FROM lostfinds a  JOIN user b on  a.islost=1 and a.isget=0 and a.user_id= b.id ORDER BY a.pubtime DESC ";
            Map m1 = new HashMap();
            return baseDao.getBySql(sql1,m1);
        }
        return false;

    }
    public Object getAllFinds(String taken){
        String user_id = baseDao.findUser_id(taken);
        if(user_id!=null){
            String sql1= "SELECT a.id,a.thing,a.address,a.pubtime,b.name,b.userphoto FROM lostfinds a  JOIN user b on  a.islost=0 and a.isget=0 and a.user_id= b.id ORDER BY a.pubtime DESC ";
            Map m1 = new HashMap();
            return baseDao.getBySql(sql1,m1);
        }
        return false;
    }

    public Object getLostFindsInfo(String lf_id,String taken){
        String user_id = baseDao.findUser_id(taken);
        if(user_id!=null){
            String sql2="SELECT a.thing,a.address,a.content,b.name,b.userphoto,b.phone FROM lostfinds a  JOIN user b on a.id=:lf_id and a.user_id=b.id";
            Map m2 = new HashMap();
            m2.put("lf_id",lf_id);
            return baseDao.getBySql(sql2,m2);
        }
        return false;
    }

    public Object getImage(String lf_id){
        String sql = "SELECT url FROM image WHERE lostfind_id=:lf_id";
        Map map = new HashMap();
        map.put("lf_id",lf_id);
        return baseDao.getBySql(sql,map);
    }
}
