package cn.techoc.jxcadmin.model;

import java.time.LocalDateTime;

/**
 * @author techoc
 * @since 2021/5/26
 */
public class CaptchaImageModel {

    private final String code;

    private final LocalDateTime expireTime;


    public CaptchaImageModel(String code, int expireAfterSeconds) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireAfterSeconds);
    }

    public String getCode() {
        return code;
    }

    /**
     * 验证码是否失效
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
