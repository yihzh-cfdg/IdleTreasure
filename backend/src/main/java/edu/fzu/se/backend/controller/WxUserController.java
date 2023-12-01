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

import java.util.HashMap;
import java.util.Map;


@Tag(name = "WxUserController", description = "用户控制器")
@RestController
@RequestMapping("/api/wxUser")
public class WxUserController {
    @Autowired
    private WxUserServiceimpl wxUserService;

    //注册
    @Operation(summary = "注册")
    @Parameters({
            @Parameter(name = "user", description = "用户", required = true)
    })
    @PostMapping("/register")
    public String register(@RequestBody WxUser user){
        if(!wxUserService.registerService(user))
            throw new RuntimeException("注册失败！");
        return "success";
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

    @Operation(summary = "获取用户信息")
    @Parameters({
            @Parameter(name = "id", description = "用户id", required = true)
    })
    @GetMapping("/get")
    public Map<String, String> getUser(@RequestParam("id") Long id){
        return wxUserService.getById(id);
    }

    @Operation(summary = "修改用户名")
    @Parameters({
            @Parameter(name = "id", description = "用户id", required = true),
            @Parameter(name = "username", description = "用户名", required = true)
    })
    @GetMapping("/change-username")
    public String changeUsername(@RequestParam("id") Long id, @RequestParam("username") String username){
        if( wxUserService.updateUsername(id, username))
            return "success";
        else
            throw new RuntimeException("修改失败");
    }

    @Operation(summary = "获取地址信息")
    @Parameters({
            @Parameter(name = "id", description = "用户id", required = true)
    })
    @GetMapping("/get-address")
    public Map<String, String> getAddress(@RequestParam("id") Long id){
        return wxUserService.getAddress(id);
    }

    @Operation(summary = "修改收件人")
    @Parameters({
            @Parameter(name = "id", description = "用户id", required = true),
            @Parameter(name = "recipient", description = "收件人", required = true)
    })
    @GetMapping("/change-recipient")
    public String changeReceiver(@RequestParam("id") Long id, @RequestParam("recipient") String recipient){
        if( wxUserService.updateReceiver(id, recipient))
            return "success";
        else
            throw new RuntimeException("修改失败");
    }

    @Operation(summary = "修改电话号码")
    @Parameters({
            @Parameter(name = "id", description = "用户id", required = true),
            @Parameter(name = "phone", description = "电话号码", required = true)
    })
    @GetMapping("/change-phone")
    public String changePhone(@RequestParam("id") Long id, @RequestParam("phone") String phone){
        if( wxUserService.updatePhone(id, phone))
            return "success";
        else
            throw new RuntimeException("修改失败");
    }

    @Operation(summary = "修改地址")
    @Parameters({
            @Parameter(name = "id", description = "用户id", required = true),
            @Parameter(name = "address", description = "地址", required = true)
    })
    @GetMapping("/change-address")
    public String changeAddress(@RequestParam("id") Long id, @RequestParam("address") String address){
        if( wxUserService.updateAddress(id, address))
            return "success";
        else
            throw new RuntimeException("修改失败");
    }


}
