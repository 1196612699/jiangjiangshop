package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.UserLoginDTO;
import com.tencent.wxcloudrun.model.User;
import com.tencent.wxcloudrun.properties.JwtProperties;
import com.tencent.wxcloudrun.service.UserService;
import com.tencent.wxcloudrun.uitls.JwtUtil;
import com.tencent.wxcloudrun.vo.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tencent.wxcloudrun.constant.JwtClaimsConstant;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;
    @PostMapping(value = "/wxLogin")
    public ApiResponse wxLogin(@RequestBody UserLoginDTO userLoginDTO){
        User user = userService.wxLogin(userLoginDTO);
        // 为微信用户生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID,user.getId());
        // JwtProperties与application中的配置相关联，然后通过注入的方式间接的拿到配置文件中添加的属性值
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .openid(user.getOpenid())
                .token(token)
                .build();
        return ApiResponse.ok(userLoginVO);
    }
}
