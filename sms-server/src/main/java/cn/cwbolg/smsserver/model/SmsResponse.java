package cn.cwbolg.smsserver.model;

import lombok.Data;

/**
 * @author  chenw
 * @date  2020/3/25 14:23
 *
 * 发送消息响应实体类
 */
@Data
public class SmsResponse {

    private String message;

    private String requestId;

    private String bizId;

    private String code;
}
