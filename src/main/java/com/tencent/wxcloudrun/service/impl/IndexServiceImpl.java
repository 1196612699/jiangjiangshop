package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.IndexMapper;
import com.tencent.wxcloudrun.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private IndexMapper indexMapper;



    @Override
    public Integer getAppFlag(Integer id) {
        return indexMapper.getAppFlag(id);
    }
}
