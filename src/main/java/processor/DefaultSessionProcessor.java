package processor;

import entity.Session;

import java.util.Timer;
import java.util.TimerTask;

public class DefaultSessionProcessor implements SessionProcessor {
    private Session session;
    private volatile boolean stopFlag = false;
    private Timer timer = new Timer();

    public DefaultSessionProcessor(Session session) {
        this.session = session;
    }

    @Override
    public void createSession() {
        this.session.start();
        scheduleSession();
    }

    private void scheduleSession() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.println("stop:" + session.getSessionId() + ",time:" + session.getSessionTimeInMilliSeconds() + ",delay:" + (System.currentTimeMillis() - session.getStartTimeStamp()));
                    session.stop();
                } catch (Exception e) {
                    System.out.println("stop session failed!");
                }
            }
        }, session.getSessionTimeInMilliSeconds());
    }

    @Override
    public void resetSessionTime(long sessionTimeInMilliSeconds) {
        timer.cancel();
        long runningTime = System.currentTimeMillis()-session.getStartTimeStamp();
        if (runningTime >= sessionTimeInMilliSeconds) {
            session.stop();
        } else {
            session.setSessionTimeInMilliSeconds(sessionTimeInMilliSeconds-runningTime);
            scheduleSession();
        }
    }

}
