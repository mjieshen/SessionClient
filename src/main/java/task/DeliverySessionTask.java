package task;

import com.google.common.util.concurrent.SettableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import processor.SessionProcessor;

/**
 * Created by shenjie on 2020/08/21.
 */
public class DeliverySessionTask implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(DeliverySessionTask.class);
    private SessionProcessor sessionProcessor;
    private SettableFuture<String> settableFuture;

    public DeliverySessionTask(SessionProcessor sessionProcessor, SettableFuture<String> settableFuture) {
        this.sessionProcessor = sessionProcessor;
        this.settableFuture = settableFuture;
    }

    /**
     * run session task
     */
    @Override
    public void run() {
        try {
            String response = sessionProcessor.createSession();
            settableFuture.set(response);
        } catch (Exception e) {
            logger.error("run DeliverySessionTask failed.");
        }
    }

}
