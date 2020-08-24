package processor;

import entity.Session;
import org.junit.Test;
import utils.MockHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * Created by shenjie on 2020/08/23.
 */
public class DefaultSessionProcessorTest {

    @Test
    public void testCreateSession() throws Exception {
        Session session = new Session(123456, 1000);
        session.setHttpClient(new MockHttpClient());
        SessionProcessor sessionProcessor = new DefaultSessionProcessor(session);
        sessionProcessor.createSession();
        TimeUnit.SECONDS.sleep(5);
    }

    @Test
    public void testResetSessionExpireTime() throws Exception {
        Session session = new Session(123456, 1000);
        session.setHttpClient(new MockHttpClient());
        SessionProcessor sessionProcessor = new DefaultSessionProcessor(session);
        sessionProcessor.createSession();

        sessionProcessor.resetSessionExpireTime(10000);
        TimeUnit.SECONDS.sleep(15);
    }
}