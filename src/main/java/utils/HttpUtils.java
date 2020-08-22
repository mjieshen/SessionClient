package utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static String sendHttpPost(String url, String requestBody) {
        String responseBody = null;
        try {
            // (发送时间,发送url,和body,结果,连接信息)
            logger.info("requestTimeStamp:" + System.currentTimeMillis());
            logger.info("url:" + url);
            logger.info("request:" + requestBody);
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(requestBody));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            responseBody = EntityUtils.toString(entity, "UTF-8");
            logger.info("response:" + responseBody);
            response.close();
            httpClient.close();
        } catch (Exception e) {
            logger.error("send post request failed.");
        }

        return responseBody;
    }

}