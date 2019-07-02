package org.dashboard.enums;

/**
 * @ClassName: StatusEmnus
 * @Auther: jchan
 * @Date: 2019/7/1 上午11:06
 * @Version 1.0 2019/7/1 上午11:06 by 韩进城(hanjc@cloud-young.com)
 * @Description:
 **/
public enum StatusEmnus {
    SUCCESS("200","success"),
    ERROR("500","error");


    private String code;

    private String message;

    StatusEmnus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
