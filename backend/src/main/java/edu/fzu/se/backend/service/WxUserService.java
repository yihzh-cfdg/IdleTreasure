package edu.fzu.se.backend.service;
import edu.fzu.se.backend.bean.WxUser;
import com.baomidou.mybatisplus.extension.service.IService;

public interface WxUserService extends IService<WxUser>{
    //登录
    Long loginService(String name,String password);
    //注册
    boolean registerService(WxUser wxUser);
    //用户名是否存在
    boolean isUsernameExists(String name);
}

