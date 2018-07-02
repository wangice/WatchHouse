package com.ice.brother.house.app.web.rsp;

/**
 * @author:ice
 * @Date: 2018/6/16 11:45
 */
public class RspLogin {
    private String userId;//用户ID
    private String userName;//用户名称

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
