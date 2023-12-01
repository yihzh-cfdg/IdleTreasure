package edu.fzu.se.backend.controller;

import edu.fzu.se.backend.common.response.ResultMessage;
import edu.fzu.se.backend.mapper.ImagesMapper;
import edu.fzu.se.backend.service.serviceimpl.ImageServiceimpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Tag(name = "ImageUploadController", description = "图片上传控制器")
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/api/upload")
public class ImageUploadController {
    @Autowired
    ImageServiceimpl imageService;

    @Operation(summary = "上传图片")
    @Parameters({
            @Parameter(name = "file", description = "图片文件", required = true),
            @Parameter(name = "id", description = "对应商品id", required = true),
            @Parameter(name = "type", description = "类型: 1.用户，2.商品，3.评价，4.平台")
    })
    @PostMapping(value = "/uploadImage", consumes = "multipart/form-data")
    public List<String> uploadImage(@RequestParam("file") List<MultipartFile> files, HttpServletRequest request) throws Exception {
        List<String> url = imageService.upload(files, request.getParameter("type"), request.getParameter("id"));

        return url;
    }

    @DeleteMapping("/delImg")
    @ResponseBody
    public ResultMessage delImg(@RequestParam(value = "imgPath") String imgPath) throws Exception {
        return ResultMessage.success();
    }
}
