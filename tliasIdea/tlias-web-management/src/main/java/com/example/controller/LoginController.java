package com.example.controller;


import com.example.pojo.Emp;
import com.example.pojo.LoginInfo;
import com.example.pojo.Result;
import com.example.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制器
 */
@RestController
public class LoginController {
    //定义Logger对象
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    //注入业务逻辑
    @Autowired
    private EmpService empService;

    /*
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        //记录日志
        logger.info("登录参数：{}", emp);
        //封装登录结果
        LoginInfo loginInfo = empService.login(emp);
        //对loginInfo进行判断
        if (loginInfo != null){
            return Result.success(loginInfo);
        }else{
            return Result.error("用户名或密码错误");
        }
    }
}
