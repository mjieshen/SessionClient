package manager;

import com.google.common.util.concurrent.ListenableFuture;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by shenjie on 2020/08/23.
 */
public class SessionManagerTest {
    public static final int threadCount = 3;
    private ExecutorService executor = Executors.newFixedThreadPool(threadCount);
    private SessionManager sessionManager = new SessionManager();
    int[] sessionIds = {111, 222, 333, 444, 555, 666, 777, 888, 999, 1212};

    @Test
    public void testCreateSession() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            int temp = i;
            long sessionExpireTime = getRandom(1000, 5000);
            ListenableFuture<String> future = sessionManager.createSession(sessionIds[temp], sessionExpireTime);
            future.addListener(new Runnable() {
                @Override
                public void run() {
                    try {
                        String response = future.get();
                        System.out.println("testCreateSession response:" + response);

                        // wait stop action
                        TimeUnit.MILLISECONDS.sleep(sessionExpireTime + 2000);
                        countDownLatch.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, executor);
        }
        countDownLatch.await();
    }

    @Test
    public void testResetSessionTime() throws Exception {
        sessionManager.createSession(123456, 1000);
        TimeUnit.SECONDS.sleep(2);
        sessionManager.resetSessionTime(123456, 3000);
        TimeUnit.SECONDS.sleep(10);
    }

    @Test
    public void testResetAllSessionTime() throws Exception {
        sessionManager.createSession(123456, 1000);
        TimeUnit.SECONDS.sleep(2);
        sessionManager.resetAllSessionTime(3000);
        TimeUnit.SECONDS.sleep(10);
    }

    private static int getRandom(int start, int end) {
        int num = (int) (Math.random() * (end - start + 1) + start);
        return num;
    }
}