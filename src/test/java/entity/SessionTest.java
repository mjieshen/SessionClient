package entity;

import org.junit.Test;
import utils.HttpClient;
import utils.MockHttpClient;

/**
 * Created by shenjie on 2020/08/23.
 */
public class SessionTest {

    HttpClient mockHttpClient = new MockHttpClient();

    @Test
    public void testStart() {
        Session session = new Session(123456, 1000);
        session.setHttpClient(mockHttpClient);
        String responseBody = session.start();
        System.out.println("responseBody:" + responseBody);
    }

    @Test
    public void testStop() {
        Session session = new Session(123456, 1000);
        session.setHttpClient(mockHttpClient);
        String responseBody = session.stop();
        System.out.println("responseBody:" + responseBody);
    }
}