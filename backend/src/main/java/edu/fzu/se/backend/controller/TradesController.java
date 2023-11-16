//商品上传接口功能实现
package edu.fzu.se.backend.controller;

import edu.Utils.ResultUtils;
import edu.Utils.ResultVo;
import edu.fzu.se.backend.bean.Goods;
import edu.fzu.se.backend.service.TradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequestMapping("/api/goods")
public class TradesController {
    @Autowired
    private TradesService goodsService;

    //发布
    @PostMapping("/release")
    public ResultVo release(@RequestBody Goods goods){
        //设置时间
        goods.setRelease_Time(new Date());
        if(goodsService.save(goods)){
            return ResultUtils.success("发布成功!");
        }
        return ResultUtils.error("发布失败!");
    }
}
