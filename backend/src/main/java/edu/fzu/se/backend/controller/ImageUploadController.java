package edu.fzu.se.backend.controller;
import edu.Utils.ResultUtils;
import edu.Utils.ResultVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Tag(name = "ImageUploadController", description = "图片上传控制器")
@RestController
@RequestMapping("/api/upload")
public class ImageUploadController {
    //图片上传的路径
    @Value("${file-save-path}")
    private String webUploadpath;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");
    @Operation(summary = "上传图片")
    @Parameters({
            @Parameter(name = "file", description = "图片文件", required = true)
    })
    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        String directory = simpleDateFormat.format(new Date());
        String fileName = file.getOriginalFilename();
        //获取文件扩展名
        String fileExtenionName = null;
        if (fileName != null) {
            fileExtenionName = fileName.substring(fileName.indexOf("."));
        }
        //新的文件名
        String newFileName= UUID.randomUUID().toString().replaceAll("-", "")+fileExtenionName;
        File fileDir = new File(webUploadpath + directory);
        if(!fileDir.exists()){
            fileDir.mkdirs();
            //设置权限
            fileDir.setWritable(true);
        }
        File targetFile = new File(webUploadpath+ directory + newFileName);
        String Url;
        try{
            file.transferTo(targetFile);
            Url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +"/img/" + directory + newFileName;
        }catch (Exception e){
            return  null;
        }
        return Url;
    }
}
