package cn.cwbolg.smsserver.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author  chenw
 * @date  2020/3/25 14:57
 *
 * 短信发送模型
 */
@Data
@Accessors(chain = true)
public class SmsMessage {
    //验证码
    private String code;
    //手机号
    private String phoneNumber;
}
