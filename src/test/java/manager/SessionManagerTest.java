package manager;

import junit.framework.TestCase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SessionManagerTest extends TestCase {

    private ExecutorService executor = Executors.newFixedThreadPool(10);
    private SessionManager sessionManager = new SessionManager();

    public void testCreateSession() {
        boolean result = sessionManager.createSession(123456, 1000);
        assert result;
    }

    public void testResetSessionTime() {
    }

    public void testResetAllSessionTime() {
    }

    public void testGetSessionProcessors() {
    }

    public void batchTestCreateSession() throws Exception {
        for (int i = 0; i < 2; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    sessionManager.createSession(getRandom(4000, 5000), getRandom(5000, 10000));
                }
            });
        }
    }

    private static int getRandom(int start, int end) {
        int num = (int) (Math.random() * (end - start + 1) + start);
        return num;
    }
}