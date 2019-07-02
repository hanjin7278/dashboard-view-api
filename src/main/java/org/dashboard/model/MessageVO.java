package org.dashboard.model;

import lombok.Data;
import org.dashboard.util.DateUtil;
import org.dashboard.enums.StatusEmnus;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: MessageVO
 * @Auther: jchan
 * @Date: 2019/7/1 上午11:05
 * @Version 1.0 2019/7/1 上午11:05 by 韩进城(hanjin7278@126.com)
 * @Description:
 **/
@Data
public class MessageVO<T> implements Serializable {
    private static final long serialVersionUID = 211423191531659616L;
    private Boolean result = false;
    private String code;
    private String message;
    private T data;
    private String systemDate = DateUtil.formatDateTime(new Date(), "yyyy-MM-dd HH:mm:ss");
    private Long systemTimeMillis = System.currentTimeMillis();

    public MessageVO() {
    }

    public MessageVO(Boolean result, T data) {
        this.result = result;
        this.data = data;
        this.code = StatusEmnus.SUCCESS.getCode();
        this.message = StatusEmnus.SUCCESS.getMessage();
    }

    public MessageVO(Boolean result, StatusEmnus enums) {
        this.result = result;
        this.code = enums.getCode();
        this.message = enums.getMessage();
    }

    public MessageVO(Boolean result, String code, String message) {
        this.result = result;
        this.code = code;
        this.message = message;
    }

    public MessageVO(Boolean result, StatusEmnus enums, T data) {
        this.result = result;
        this.code = enums.getCode();
        this.message = enums.getMessage();
        this.data = data;
    }

    public static <T> MessageVO<T> getInstanceForOk(T data) {
        return new MessageVO(true, data);
    }

    public static MessageVO getInstanceForOk() {
        return new MessageVO(true, StatusEmnus.SUCCESS);
    }

    public static <T> MessageVO<T> getInstanceForError(String code, String message) {
        return new MessageVO(false, code, message);
    }
}
