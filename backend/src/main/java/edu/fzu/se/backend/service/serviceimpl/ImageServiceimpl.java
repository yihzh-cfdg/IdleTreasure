package edu.fzu.se.backend.service.serviceimpl;

import cn.hutool.core.lang.UUID;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import edu.fzu.se.backend.bean.Goods_Images_Conns;
import edu.fzu.se.backend.bean.Images;
import edu.fzu.se.backend.common.enums.ImageEnum;
import edu.fzu.se.backend.config.OssUploadConfig;
import edu.fzu.se.backend.mapper.Goods_Images_ConnsMapper;
import edu.fzu.se.backend.mapper.ImagesMapper;
import edu.fzu.se.backend.mapper.WxUserMapper;
import edu.fzu.se.backend.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class ImageServiceimpl implements ImageService {
    @Autowired
    private OssUploadConfig ossConfig;
    @Autowired
    private Environment environment;
    @Autowired
    private Goods_Images_ConnsMapper goodsImagesConnsMapper;
    @Autowired
    private ImagesMapper imagesMapper;
    @Autowired
    private WxUserMapper wxUserMapper;
    public String upload(MultipartFile file, Integer type, Long id) throws Exception
    {
        String bucketName = ossConfig.getBucketName();
        String endPoint = ossConfig.getEndPoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();

        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
        String originalFilename = file.getOriginalFilename();
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        String folder = dft.format(time);
        String fileName = generateUUID();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uploadFileName = "image/" + folder + "/" + fileName + extension;
        try {
            PutObjectResult result = ossClient.putObject(bucketName, uploadFileName, file.getInputStream());
            //拼装返回路径
            if (result != null) {
                String url = "https://"+bucketName+"."+endPoint+"/"+uploadFileName;
                Images image = new Images(null, originalFilename, url, ImageEnum.getNameByType(type));
                Long imageID = imagesMapper.insertImage(image);
                if(imageID < 0) {
                    throw new RuntimeException("数据库操作失败");
                }
                if(type == 2) {
                    Goods_Images_Conns conn = new Goods_Images_Conns(id, imageID);
                    if (goodsImagesConnsMapper.insertConnection(conn) < 0)
                        throw new RuntimeException("数据库操作失败");
                }
                if(type == 1)
                {
                    wxUserMapper.updateHeaderById(id, url);
                }
                return url;
            }

        } catch (IOException e) {
            log.error("文件上传失败:{}",e.getMessage());
            throw e;
        } finally {
            //OSS关闭服务，不然会造成OOM
            ossClient.shutdown();
        }
        return null;
    }
    private String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }
}
