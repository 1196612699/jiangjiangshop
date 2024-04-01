package com.tencent.wxcloudrun.model;

import lombok.Builder;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    //微信用户唯一标识
    private String openid;

    //姓名
    private String nickName;

    //手机号
    private String phone;

    //性别 0 男 1 女
    private Integer gender;

    //身份证号
    private String idNumber;

    //头像
    private String avatarUrl;

    //城市
    private String city;

    //省份
    private String country;

    //注册时间
    private LocalDateTime createTime;

}
