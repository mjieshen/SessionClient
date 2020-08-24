package manager;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import entity.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import processor.DefaultSessionProcessor;
import processor.SessionProcessor;
import task.DeliverySessionTask;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by shenjie on 2020/08/21.
 */
public class SessionManager {
    private static Logger logger = LoggerFactory.getLogger(SessionManager.class);
    private Map<Long, SessionProcessor> sessionProcessors = new ConcurrentHashMap<>();
    private ExecutorService executor;

    public SessionManager() {
        init();
    }

    /**
     * init thread pool
     */
    private void init() {
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        int maxPoolSize = 1000;
        executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(10000),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * create session async
     *
     * @param sessionId                 sessionId
     * @param sessionTimeInMilliSeconds session expire time
     * @return
     */
    public ListenableFuture<String> createSession(long sessionId, long sessionTimeInMilliSeconds) {
        logger.info("createSession sessionId[{}] sessionTime[{}]", sessionId, sessionTimeInMilliSeconds);
        SessionProcessor sessionProcessor = new DefaultSessionProcessor(new Session(sessionId, sessionTimeInMilliSeconds));
        sessionProcessors.put(sessionId, sessionProcessor);

        SettableFuture<String> settableFuture = SettableFuture.create();
        executor.submit(new DeliverySessionTask(sessionProcessor, settableFuture));

        return settableFuture;
    }

    /**
     * reset session expireTime by sessionId
     *
     * @param sessionId sessionId
     * @param sessionTimeInMilliSeconds session expire time
     */
    public void resetSessionTime(long sessionId, long sessionTimeInMilliSeconds) {
        logger.info("reset sessionId[{}] sessionTime[{}]", sessionId, sessionTimeInMilliSeconds);
        SessionProcessor sessionProcessor = sessionProcessors.get(sessionId);
        if (sessionProcessor != null) {
            sessionProcessor.resetSessionTime(sessionTimeInMilliSeconds);
        }
    }

    /**
     * reset all sessions expireTime
     *
     * @param sessionTimeInMilliSeconds session expire time
     */
    public void resetAllSessionTime(long sessionTimeInMilliSeconds) {
        logger.info("reset allSession sessionTime[{}]", sessionTimeInMilliSeconds);
        for (SessionProcessor sessionProcessor : sessionProcessors.values()) {
            sessionProcessor.resetSessionTime(sessionTimeInMilliSeconds);
        }
    }

    /**
     * get session processors
     *
     * @return
     */
    public Map<Long, SessionProcessor> getSessionProcessors() {
        return sessionProcessors;
    }
}
