package edu.fzu.se.backend.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.Utils.ResultUtils;
import edu.Utils.ResultVo;
import edu.fzu.se.backend.bean.WxUser;
import edu.fzu.se.backend.service.WxUserService;
import edu.fzu.se.backend.service.serviceimpl.WxUserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/wxUser")
public class WxUserController {
    @Autowired
    private WxUserService wxUserService;

    //注册
    @PostMapping("/register")
    public ResultVo register(@RequestBody WxUser user){
        //判断账户是否被占用
        QueryWrapper<WxUser> query = new QueryWrapper<>();
        query.lambda().eq(WxUser::getUser_ID,user.getUser_ID());
        //查询用户
        WxUser one = wxUserService.getOne(query);
        if(one != null){
            return ResultUtils.error("用户名被占用!");
        }
        //密码加密
        user.setUser_Key(DigestUtils.md5DigestAsHex(user.getUser_Key().getBytes()));
        //存到数据库
        if(wxUserService.saveOrUpdate(user)){
            return ResultUtils.success("注册成功！");
        }
        return ResultUtils.error("注册失败!");
    }

    //登录
   @PostMapping("/login")
    public ResultVo login(@RequestBody WxUser user){
        //构造查询条件
        QueryWrapper<WxUser> query = new QueryWrapper<>();
        query.lambda().eq(WxUser::getUser_Name,user.getUser_Name()).eq(WxUser::getUser_Key,
                DigestUtils.md5DigestAsHex(user.getUser_Key().getBytes()));
        WxUser wxUser = wxUserService.getOne(query);
        if(wxUser != null){
           
            //返回成功的数据
            WxUser vo = new WxUser();
            
            vo.setUser_ID(wxUser.getUser_ID());
            vo.setUser_Key(wxUser.getUser_Key());
            return ResultUtils.success("登录成功",vo);
        }
        return ResultUtils.error("用户密码或密码错误!");
    }

}
