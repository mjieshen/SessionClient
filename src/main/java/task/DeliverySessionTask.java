package task;

import processor.SessionProcessor;

/**
 * Created by shenjie on 2020/08/21.
 */
public class DeliverySessionTask implements Runnable {
    private SessionProcessor sessionProcessor;

    public DeliverySessionTask(SessionProcessor sessionProcessor) {
        this.sessionProcessor = sessionProcessor;
    }

    /**
     * session task
     */
    @Override
    public void run() {
        sessionProcessor.createSession();
    }

}
