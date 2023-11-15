package edu.fzu.se.backend.service.serviceimpl;
import edu.fzu.se.backend.bean.WxUser;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import edu.fzu.se.backend.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import edu.fzu.se.backend.mapper.WxUserMapper;
import java.util.List;

@Service
public class WxUserServiceimpl  extends ServiceImpl<WxUserMapper, WxUser> implements  WxUserService {
    @Autowired
    private  WxUserMapper wxUserMapper;
    @Override
    //登录
    public boolean loginService(String name,String password){
        List<WxUser> users = wxUserMapper.selectAll();
        for (WxUser u : users) {
            if (name.equals(u.getUser_Name()) && password.equals(u.getUser_Key())) {
                return true;
            }
        }
        return false;
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
