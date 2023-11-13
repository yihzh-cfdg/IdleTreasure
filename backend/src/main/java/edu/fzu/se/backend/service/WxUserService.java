package edu.fzu.se.backend.service;

import edu.fzu.se.backend.bean.WxUser;

import java.util.List;

public interface WxUserService {
    //登录
    boolean loginService(String name,String password);
    //注册
    boolean registerService(WxUser wxUser);
    //用户名是否存在
    boolean isUsernameExists(String name);
}

