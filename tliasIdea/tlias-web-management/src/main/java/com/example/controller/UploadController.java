package com.example.controller;

import com.example.pojo.Result;
import com.example.utils.AliyunOSSOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传控制器：本地磁盘存储
 */
/*@RestController
public class UploadController {
    //记录日志
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
    @PostMapping("/upload")
    public String upload( MultipartFile file)  {
        logger.info("上传成功");
        //获取文件名
        String originalFilename = file.getOriginalFilename();
        //新文件名
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));//获取文件后缀名
        String newFileName = UUID.randomUUID().toString() + extension;
        //保存文件
        try {
            file.transferTo(new File("D:\\idea\\code\\java web\\TliasStudyManage\\tliasIdea\\tlias-web-management\\src\\main\\resources\\static\\files\\" + newFileName ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "上传成功";
    }
}*/
@RestController
public class UploadController {
    //注入阿里云OSS工具类
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    //记录日志
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        logger.info("文件上传：{}",  file.getOriginalFilename());
        //文件传入阿里云OSS
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        logger.info("文件上传成功：{}", url);
        return Result.success(url);

    }
}
