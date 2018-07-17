package com.ice.brother.house.app.web.aspect;

import com.alibaba.druid.support.http.StatViewServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @author:ice
 * @Date: 2018/7/16 20:12
 */
@WebServlet(urlPatterns = "/druid/*",
    initParams = {
        @WebInitParam(name = "loginUsername", value = "admin"),// 用户名
        @WebInitParam(name = "loginPassword", value = "123456"),// 密码
        @WebInitParam(name = "resetEnable", value = "false")// 禁用HTML页面上的“Reset All”功能
    }
)
public class DruidStatViewServlet extends StatViewServlet {

}
