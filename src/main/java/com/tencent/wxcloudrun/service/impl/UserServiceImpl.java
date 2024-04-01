package com.tencent.wxcloudrun.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.tencent.wxcloudrun.dao.UserMapper;
import com.tencent.wxcloudrun.dto.UserLoginDTO;
import com.tencent.wxcloudrun.properties.WeChatProperties;
import com.tencent.wxcloudrun.model.User;
import com.tencent.wxcloudrun.service.UserService;
import com.tencent.wxcloudrun.uitls.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
@Service
public class UserServiceImpl implements UserService {
    // 微信服务接口地址
    public static final String  WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private WeChatProperties weChatProperties;
    @Autowired
    private UserMapper userMapper;
    @Override
    public User wxLogin(UserLoginDTO userLoginDTO) {
        String openid = getOpenid(userLoginDTO.getCode());
        if(openid == null){
            throw new RuntimeException("登录失败,openid为空");
        }
        // 判断当前用户是否是该系统的新用户
        User user = userMapper.getByOpenid(openid);
//
        // 如果是新用户，则自动完成注册
        if(user == null){
            User userInfo = userLoginDTO.getUserInfo();
            user = User.builder()
                    .openid(openid)
                    .avatarUrl(userInfo.getAvatarUrl())
                    .nickName(userInfo.getNickName())
                    .createTime(LocalDateTime.now())
                    .build();
            userMapper.insert(user);
        }
        return user;
    }
    /**
     * 调用微信接口服务，获得当前微信用户的openid
     * @param code
     * @return
     */
    private String getOpenid(String code){
        // 调用微信接口服务，获得当前微信用户的openid
        Map<String, String> map = new HashMap<>();
        map.put("appid",weChatProperties.getAppid());
        map.put("secret",weChatProperties.getSecret());
        map.put("js_code",code);
        map.put("grant_type","authorization_code");
        String json = HttpClientUtil.doGet(WX_LOGIN, map); // 得到微信端服务器返回的json数据
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = jsonObject.getString("openid"); // 解析json数据，获取openid

        return openid;
    }
}
