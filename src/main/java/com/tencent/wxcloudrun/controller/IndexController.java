package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * index控制器
 */
@Controller

public class IndexController {
  final IndexService indexService;

  public IndexController(@Autowired IndexService indexService) {
    this.indexService = indexService;
  }

  /**
   * 主页页面
   * @return API response html
   */
  @GetMapping
  public String index() {
    return "index";
  }
  @GetMapping(value = "/get_reset")
  @ResponseBody
  ApiResponse Reset(Integer id){
    System.out.println(id);
    Integer flag = indexService.getAppFlag(id);
    return ApiResponse.ok(flag);
  }

}
