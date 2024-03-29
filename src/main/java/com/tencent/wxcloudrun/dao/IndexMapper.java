package com.tencent.wxcloudrun.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface IndexMapper {
    Integer getAppFlag(@Param("id")Integer id);
}
