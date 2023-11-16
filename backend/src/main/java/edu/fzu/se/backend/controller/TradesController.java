//商品上传接口功能实现
package edu.fzu.se.backend.controller;

import edu.Utils.ResultUtils;
import edu.Utils.ResultVo;
import edu.fzu.se.backend.bean.Goods;
import edu.fzu.se.backend.service.TradesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Tag(name = "TradesController", description = "商品上传控制器")
@RestController
@RequestMapping("/api/goods")
public class TradesController {
    @Autowired
    private TradesService goodsService;

    @Operation(summary = "发布商品")
    @Parameters({
            @Parameter(name = "goods", description = "商品", required = true)
    })
    @PostMapping("/release")
    public String release(@RequestBody Goods goods){
        //设置时间
        goods.setRelease_Time(new Date());
        if(goodsService.save(goods)){
            return "发布成功!";
        }
        return "发布失败!";
    }
}
