package manager;

import entity.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import processor.DefaultSessionProcessor;
import processor.SessionProcessor;
import task.DeliverySessionTask;
import java.util.Map;
import java.util.concurrent.*;


public class SessionManager {
    private static Logger logger = LoggerFactory.getLogger(SessionManager.class);
    private Map<Long, SessionProcessor> sessionProcessors = new ConcurrentHashMap<>();
    private ExecutorService executor;

    public SessionManager() {
        init();
    }

    private void init() {
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        int maxPoolSize = 1000;
        executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(10000),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public boolean createSession(long sessionId, long sessionTimeInMilliSeconds) {
        SessionProcessor sessionProcessor = new DefaultSessionProcessor(new Session(sessionId, sessionTimeInMilliSeconds));
        sessionProcessors.put(sessionId, sessionProcessor);

        executor.submit(new DeliverySessionTask(sessionProcessor));

        return true;
    }

    public void resetSessionTime(long sessionId, long sessionTimeInMilliSeconds) {
        logger.info("reset sessionId[{}] sessionTime[{}]", sessionId, sessionTimeInMilliSeconds);
        SessionProcessor sessionProcessor = sessionProcessors.get(sessionId);
        if (sessionProcessor != null) {
            sessionProcessor.resetSessionTime(sessionTimeInMilliSeconds);
        }
    }

    public void resetAllSessionTime(long sessionTimeInMilliSeconds) {
        logger.info("reset allSession sessionTime[{}]", sessionTimeInMilliSeconds);
        for (SessionProcessor sessionProcessor : sessionProcessors.values()) {
            sessionProcessor.resetSessionTime(sessionTimeInMilliSeconds);
        }
    }

    public Map<Long, SessionProcessor> getSessionProcessors() {
        return sessionProcessors;
    }
}
