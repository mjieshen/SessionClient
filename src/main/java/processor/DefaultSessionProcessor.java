package processor;

import entity.Session;

import java.util.Timer;
import java.util.TimerTask;

public class DefaultSessionProcessor implements SessionProcessor {
    private Session session;
    private volatile boolean stopFlag = false;
    private Timer timer = new Timer();
    private long start;

    public DefaultSessionProcessor(Session session) {
        this.session = session;
    }

    @Override
    public void createSession() {
        start();
        stop();
    }

    @Override
    public void resetSessionTime(long sessionTimeInMilliSeconds) {
        session.setSessionTimeInMilliSeconds(sessionTimeInMilliSeconds);
        if (!stopFlag) {
            timer.cancel();
            stop();
        }
    }

    private void start() {
        start = System.currentTimeMillis();
        System.out.println("start:" + session.getSessionId() + ",time:" + session.getSessionTimeInMilliSeconds());
    }

    private void stop() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.println("stop:" + session.getSessionId() + ",time:" + session.getSessionTimeInMilliSeconds() + ",delay:" + (System.currentTimeMillis() - start));
                    stopFlag = true;
                } catch (Exception e) {
                    System.out.println("stop session failed!");
                }
            }
        }, session.getSessionTimeInMilliSeconds());
    }
}
