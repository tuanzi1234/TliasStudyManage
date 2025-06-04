package com.example.utils;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component // 将当前类声明为组件
public class AliyunOSSOperator {

    //阿里云OSS的参数声明为静态常量
    //直接将配置写死成静态常量不利于维护，可以在配置文件中进行配置，再使用@Value注解获取
    /*public static final String endpoint = "https://oss-cn-beijing.aliyuncs.com";
    public static final String bucketName = "web-work0000001";
    public static final String region = "cn-beijing";*/

    //使用@Value注解获取阿里云OSS的参数，但当需要注入的参数过多会很繁琐
    /*@Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;
    @Value("${aliyun.oss.region}")
    private String region;*/

    //因此可以使用@Autowired注解注入封装好阿里云配置信息的AliyunOSSProperties对象
    @Autowired
    private AliyunOSSProperties aliyunOSSProperties;

    //上传文件
    public String upload(byte[] content, String originFileName) throws Exception {
        //获取AliyunOSSProperties对象中封装的参数
        String endpoint = aliyunOSSProperties.getEndpoint();
        String bucketName = aliyunOSSProperties.getBucketName();
        String region = aliyunOSSProperties.getRegion();
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        //构建文件名
        //获取系统日期字符串
        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        //生成新文件名:  UUID + 文件名后缀
        String newFileName = UUID.randomUUID() + originFileName.substring(originFileName.lastIndexOf("."));
        String objectName = dir + "/" + newFileName;

        // 创建OSSClient实例。
        // 当OSSClient实例不再使用时，调用shutdown方法以释放资源。
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(clientBuilderConfiguration)
                .region(region)
                .build();
        try {
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content));
        } finally {
            ossClient.shutdown();
        }
        //  返回文件访问路径: 以该项目为例 “https:” + “//” + "web-work0000001“ + "." + "oss-cn-beijing.aliyuncs.com” + ”/” + "dir + "/" + newFileName"
        return endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + objectName;
    }

}
