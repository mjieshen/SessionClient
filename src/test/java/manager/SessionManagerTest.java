package manager;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SessionManagerTest {
    /*
      TODO:
      1.添加代码注释
      2.写readme
      3.返回异步对象
     */

    public static final int threadCount = 10;
    private ExecutorService executor = Executors.newFixedThreadPool(threadCount);
    private SessionManager sessionManager = new SessionManager();

    @Test
    public void testCreateSession() throws Exception {
        sessionManager.createSession(111, 1000);
        sessionManager.createSession(222, 1000);
        sessionManager.createSession(333, 1000);
        assert sessionManager.getSessionProcessors().size() == 3;
        TimeUnit.SECONDS.sleep(5);
    }

    @Test
    public void testBatchCreateSessions() throws Exception {
        int[] sessionIds = {111, 222, 333, 444, 555, 666, 777, 888, 999, 000};
        for (int i = 0; i < threadCount; i++) {
            int temp = i;
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    sessionManager.createSession(sessionIds[temp], getRandom(1000, 5000));
                }
            });
        }

        TimeUnit.SECONDS.sleep(10);
        assert sessionManager.getSessionProcessors().size() == threadCount;
    }

    @Test
    public void testResetSessionTime() throws Exception {
        sessionManager.createSession(123456, 1000);
        TimeUnit.SECONDS.sleep(2);
        sessionManager.resetSessionTime(123456, 10000);
        TimeUnit.SECONDS.sleep(15);
    }

    @Test
    public void testResetAllSessionTime() throws Exception {
        sessionManager.createSession(123456, 1000);
        TimeUnit.SECONDS.sleep(2);
        sessionManager.resetAllSessionTime(10000);
        TimeUnit.SECONDS.sleep(15);
    }

    private static int getRandom(int start, int end) {
        int num = (int) (Math.random() * (end - start + 1) + start);
        return num;
    }
}