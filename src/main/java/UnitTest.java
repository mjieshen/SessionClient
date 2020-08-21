import manager.SessionManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class UnitTest {

    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws Exception {
        SessionManager sessionManager = new SessionManager();
        for (int i = 0; i < 2; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    sessionManager.createSession(getRandom(4000, 5000), getRandom(5000, 10000));
                }
            });
        }

        TimeUnit.SECONDS.sleep(1);
        for (Long sessionId : sessionManager.getSessionProcessors().keySet()) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    sessionManager.resetSessionTime(sessionId, 20000);
                }
            });
        }

    }

    public static int getRandom(int start, int end) {
        int num = (int) (Math.random() * (end - start + 1) + start);
        return num;
    }
}
