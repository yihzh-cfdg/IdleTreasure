package edu.fzu.se.backend.controller;

import edu.fzu.se.backend.bean.Goods;
import edu.fzu.se.backend.service.serviceimpl.ReleaseServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/release")
@Tag(name = "ReleaseController", description = "商品发布控制器")
public class ReleaseController {
    @Autowired
    ReleaseServiceImpl releaseService;
    @Operation(summary = "发布商品")
    @Parameters({
            @Parameter(name = "goods", description = "商品", required = true)
    })
    @PostMapping
    public String releaseGood(@RequestBody Goods goods) throws Exception {
        return releaseService.releaseGoods(goods);
    }
}
