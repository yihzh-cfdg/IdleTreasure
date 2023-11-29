package edu.fzu.se.backend.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.fzu.se.backend.bean.WxUser;
import edu.fzu.se.backend.service.WxUserService;
import edu.fzu.se.backend.service.serviceimpl.WxUserServiceimpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Tag(name = "WxUserController", description = "微信用户控制器")
@RestController
@RequestMapping("/api/wxUser")
public class WxUserController {
    @Autowired
    private WxUserService wxUserService;

    //注册
    @Operation(summary = "注册")
    @Parameters({
            @Parameter(name = "user", description = "用户", required = true)
    })
    @PostMapping("/register")
    public String register(@RequestBody WxUser user){
        //判断账户是否被占用
        QueryWrapper<WxUser> query = new QueryWrapper<>();
        query.lambda().eq(WxUser::getUser_ID,user.getUser_ID());
        //查询用户
        WxUser one = wxUserService.getOne(query);
        if(one != null){
            return "用户名被占用!";
        }
        //密码加密
        user.setUser_Key(DigestUtils.md5DigestAsHex(user.getUser_Key().getBytes()));
        //存到数据库
        if(wxUserService.saveOrUpdate(user)){
            return "注册成功！";
        }
        return "注册失败!";
    }
    @Operation(summary = "登录")
    @Parameters({
            @Parameter(name = "userinfo", description = "用户信息", required = true)
    })
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> userinfo){
        Long x = wxUserService.loginService(userinfo.get("username"),userinfo.get("password"));
        if(x != null){
            return x.toString();
        }
        else throw new RuntimeException("用户名或密码错误");
    }

    @GetMapping("/get")
    public WxUser getUser(@RequestParam("id") Long id){
        WxUser u = wxUserService.getById(id);
        u.setUser_Key("");
        return u;
    }
}
