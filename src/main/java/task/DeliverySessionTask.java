package task;

import processor.SessionProcessor;

public class DeliverySessionTask implements Runnable {
    private SessionProcessor sessionProcessor;

    public DeliverySessionTask(SessionProcessor sessionProcessor) {
        this.sessionProcessor = sessionProcessor;
    }

    @Override
    public void run() {
        sessionProcessor.createSession();
    }

}
