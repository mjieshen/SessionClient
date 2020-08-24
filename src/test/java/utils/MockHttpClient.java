package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by shenjie on 2020/08/23.
 */
public class MockHttpClient extends HttpClient {

    private static Logger logger = LoggerFactory.getLogger(MockHttpClient.class);

    @Override
    public String sendHttpPost(String url, String requestBody) {
        logger.info("requestTimeStamp:" + System.currentTimeMillis());
        logger.info("url:" + url);
        logger.info("request:" + requestBody);
        String responseBody = "mock responseBody";
        logger.info("response:" + responseBody);

        return responseBody;
    }
}
