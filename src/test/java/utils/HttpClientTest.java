package utils;

import com.alibaba.fastjson.JSON;
import entity.DeliverySessionCreationType;
import org.junit.Test;

public class HttpClientTest implements Constants {
    private HttpClient httpClient = new HttpClient();

    @Test
    public void sendHttpPost() {
        long sessionId = 123456789;
        DeliverySessionCreationType deliverySession = new DeliverySessionCreationType();
        deliverySession.setDeliverySessionId(sessionId);
        String response = httpClient.sendHttpPost(String.format(urlFormat, sessionId), JSON.toJSONString(deliverySession));
        System.out.println("response:" + response);
    }

}