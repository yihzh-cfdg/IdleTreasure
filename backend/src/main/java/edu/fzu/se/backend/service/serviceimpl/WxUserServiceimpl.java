package edu.fzu.se.backend.service.serviceimpl;
import edu.fzu.se.backend.bean.WxUser;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import edu.fzu.se.backend.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import edu.fzu.se.backend.mapper.WxUserMapper;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class WxUserServiceimpl  extends ServiceImpl<WxUserMapper, WxUser> implements  WxUserService {
    @Autowired
    private  WxUserMapper wxUserMapper;
    @Override
    //登录
    public Long loginService(String name,String password){
        String md5PW = DigestUtils.md5DigestAsHex(password.getBytes());
        WxUser queryUser = wxUserMapper.selectByUserName(name);
        if(md5PW.equals(queryUser.getUser_Key()))
            return queryUser.getUser_ID();
        else
            throw new RuntimeException("用户名或密码错误");
    }
    //注册
    @Override
    public boolean registerService(WxUser wxUser){
        if (isUsernameExists(wxUser.getUser_Name())) {
            return false; // 用户名已存在，返回false表示注册失败
        }
        int result = wxUserMapper.insertUser(wxUser);
        return result > 0;
    }
    //检查用户名是否重复
    @Override
    public boolean isUsernameExists(String name) {
        List<WxUser> users = wxUserMapper.selectAll();
        for (WxUser u : users) {
            if (name.equals(u.getUser_Name())) {
                return true;
            }
        }
        return false;
    }
}
