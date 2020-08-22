package utils;

import com.alibaba.fastjson.JSON;
import entity.DeliverySessionCreationType;
import org.junit.Test;

public class HttpUtilsTest implements Constants {

    @Test
    public void sendHttpPost() {
        long sessionId = 123456;
        DeliverySessionCreationType deliverySession = new DeliverySessionCreationType();
        deliverySession.setDeliverySessionId(sessionId);
        String response = HttpUtils.sendHttpPost(urlFormat + sessionId, JSON.toJSONString(deliverySession));
        System.out.println("response:" + response);
    }

}