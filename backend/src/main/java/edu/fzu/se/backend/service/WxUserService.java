package edu.fzu.se.backend.service;
import edu.fzu.se.backend.bean.WxUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface WxUserService{
    //登录
    Long loginService(String name,String password);
    //注册
    boolean registerService(WxUser wxUser);
    //用户名是否存在
    boolean isUsernameExists(String name);

    Map<String, String> getById(Long id);

    //修改用户名
    boolean updateUsername(Long id, String username);

    Map<String, String> getAddress(Long id);

    //修改收件人
    boolean updateReceiver(Long id, String receiver);

    //修改电话号码
    boolean updatePhone(Long id, String phone);

    //修改地址
    boolean updateAddress(Long id, String address);
}

