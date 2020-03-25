package cn.cwbolg.smsserver.util;

import cn.cwbolg.smsserver.model.SmsMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author  chenw
 * @date  2020/3/25 16:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SendSmsUtilTest {
    @Autowired
    SendSmsUtil sendSmsUtil;

    @Test
    public void send() {
        SmsMessage smsMessage = new SmsMessage().setCode("123321").setPhoneNumber("15918885819");
        Boolean isResult = sendSmsUtil.send(smsMessage);
        System.out.println("打印:"+isResult);
    }
}