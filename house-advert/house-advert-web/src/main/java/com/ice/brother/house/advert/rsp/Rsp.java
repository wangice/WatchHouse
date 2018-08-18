package com.ice.brother.house.advert.rsp;

/**
 * @author:ice
 * @Date: 2018/6/16 11:45
 */
public class Rsp {

    public enum RspErr {
        ERR_NONE(0x00000, "成功"),

        ERR_SERVER_ERROR(0x00001, "服务器异常"),

        ERR_MISSING_PARAMS(0x00100, "缺少必要的参数"),

        ERR_NOT_FOUND_USER(0x00110, "没有找到用户"),

        ERR_PWD_ERROR(0x00111,"密码错误"),


        ERR_END(0xFFFFF, "结束");
        
        private int errCode;

        private String errName;

        private RspErr(int errCode, String errName) {
            this.errCode = errCode;
            this.errName = errName;
        }

        public int getErrCode() {
            return errCode;
        }

        public void setErrCode(int errCode) {
            this.errCode = errCode;
        }

        public String getErrName() {
            return errName;
        }

        public void setErrName(String errName) {
            this.errName = errName;
        }
    }

    private int err; //错误码
    private String desc;//错误描述
    private Object dat;//响应正文

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getDat() {
        return dat;
    }

    public void setDat(Object dat) {
        this.dat = dat;
    }
}
