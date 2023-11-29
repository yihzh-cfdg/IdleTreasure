package edu.fzu.se.backend.controller;

import edu.fzu.se.backend.bean.Goods;
import edu.fzu.se.backend.service.serviceimpl.ReleaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/release")
public class ReleaseController {
    @Autowired
    ReleaseServiceImpl releaseService;
    @PostMapping
    public String releaseGood(@RequestBody Goods goods) {
        return  releaseService.releaseGoods(goods);
    }
}
