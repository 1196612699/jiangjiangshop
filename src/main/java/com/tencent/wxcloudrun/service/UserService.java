package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.dto.UserLoginDTO;
import com.tencent.wxcloudrun.model.User;

public interface UserService {
    User wxLogin(UserLoginDTO userLoginDTO);
}
