package com.tencent.wxcloudrun.dto;

import com.tencent.wxcloudrun.model.User;
import lombok.Data;

import java.io.Serializable;

/**
 * C端用户登录
 */
@Data
public class UserLoginDTO implements Serializable {

    private String code;
    private User userInfo;

}
