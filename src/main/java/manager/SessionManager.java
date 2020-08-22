package manager;

import entity.Session;
import processor.DefaultSessionProcessor;
import processor.SessionProcessor;
import task.DeliverySessionTask;
import java.util.Map;
import java.util.concurrent.*;


public class SessionManager {
    private Map<Long, SessionProcessor> sessionProcessors = new ConcurrentHashMap<>();
    private ExecutorService executor = Executors.newCachedThreadPool();

    public SessionManager(){
//        init();
    }

    //TODO:
    private void init() {
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        int maxPoolSize = 1000;
        executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(10000),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return null;
                    }
                }, new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public boolean createSession(long sessionId, long sessionTimeInMilliSeconds) {
        SessionProcessor sessionProcessor = new DefaultSessionProcessor(new Session(sessionId, sessionTimeInMilliSeconds));
        sessionProcessors.put(sessionId, sessionProcessor);

        executor.submit(new DeliverySessionTask(sessionProcessor));

        return true;
    }

    public void resetSessionTime(long sessionId, long sessionTimeInMilliSeconds) {
        SessionProcessor sessionProcessor = sessionProcessors.get(sessionId);
        if (sessionProcessor != null) {
            sessionProcessor.resetSessionTime(sessionTimeInMilliSeconds);
        }
    }

    public void resetAllSessionTime(long sessionTimeInMilliSeconds) {
        for (SessionProcessor sessionProcessor : sessionProcessors.values()) {
            sessionProcessor.resetSessionTime(sessionTimeInMilliSeconds);
        }
    }

    public Map<Long, SessionProcessor> getSessionProcessors() {
        return sessionProcessors;
    }
}
