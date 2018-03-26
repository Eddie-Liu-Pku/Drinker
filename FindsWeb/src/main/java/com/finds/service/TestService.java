package com.finds.service;

import com.finds.dao.BaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 67552 on 2015/8/30.
 */

@SuppressWarnings("restriction")
@Service("testService")
public class TestService {
    @Resource(name = "baseDao")
    BaseDao baseDao;

}
