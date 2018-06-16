package com.ice.brother.house.app.web.controller;

import com.ice.brother.house.app.provider.entities.User;
import com.ice.brother.house.app.provider.service.UserService;
import com.ice.brother.house.app.web.core.Rsp;
import com.ice.brother.house.app.web.core.Rsp.RspErr;
import com.ice.brother.house.app.web.rsp.RspLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

;

/**
 * @author:ice
 * @Date: 2018/6/16 11:45
 */
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService userService;


    /**
     * 用户登录
     */
    @GetMapping("/login")
    public Rsp login(HttpServletRequest request) {
        String email = request.getParameter("email");
        String pwd = request.getParameter("pwd");
        if (email == null || pwd == null) {
            return transEnd(RspErr.ERR_MISSING_PARAMS);
        }
        User user = userService.userLogin(email);
        if (user == null) {
            return transEnd(RspErr.ERR_NOT_FOUND_USER);
        }
        if (!pwd.equals(user.getPwd())) {
            return transEnd(RspErr.ERR_PWD_ERROR);
        }
        userService.updateLastLoginTime(user.getUserId(), new Date());//更新最后登录时间
        RspLogin rspLogin = new RspLogin();
        rspLogin.setUserId(user.getUserId());
        rspLogin.setUserName(user.getUserName());
        return transEnd(RspErr.ERR_NONE, rspLogin);
    }
}
