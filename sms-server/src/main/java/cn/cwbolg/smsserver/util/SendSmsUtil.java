package cn.cwbolg.smsserver.util;

import cn.cwbolg.smsserver.model.SmsResponse;
import cn.cwbolg.smsserver.model.SmsMessage;
import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author  chenw
 * @date  2020/3/25 11:29
 *
 * 短信发送
 */
@Slf4j
@Component
public class SendSmsUtil {

    private static final String domain = "dysmsapi.aliyuncs.com";
    //地区ID
    @Value("${aliyun-sms.regionId}")
    private String regionId;
    //accessKeyId
    @Value("${aliyun-sms.accessKeyId}")
    private String accessKeyId;
    // secret
    @Value("${aliyun-sms.secret}")
    private String secret;
    // 模板CODE
    @Value("${aliyun-sms.templateCode}")
    private String templateCode;
    //签名名称
    @Value("${aliyun-sms.signName}")
    private String signName;

    //参数版本
    private  static final  String version = "2017-05-25";

    private static final String action = "SendSms";

    public  Boolean send(SmsMessage smsMessage){
        //regionId - 地域ID
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, secret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(domain);
        request.setVersion(version);
        request.setAction(action);
        request.putQueryParameter("RegionId", secret);
        request.putQueryParameter("PhoneNumbers", smsMessage.getPhoneNumber());
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", "{\"code\":\""+ smsMessage.getCode()+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            SmsResponse smsResponse = JSON.parseObject(response.getData(), SmsResponse.class);
            if(!"ok".equalsIgnoreCase(smsResponse.getMessage())){
                log.error("短信发送失败原因："+JSON.toJSONString(response.getData()));
                return false;
            }
        } catch (Exception e) {
            log.error("短信服务异常:{}",e);
            return false;
        }
        return true;
    }
}
