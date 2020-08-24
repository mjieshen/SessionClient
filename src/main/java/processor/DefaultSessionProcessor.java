package processor;

import entity.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by shenjie on 2020/08/21.
 */
public class DefaultSessionProcessor implements SessionProcessor {
    private static Logger logger = LoggerFactory.getLogger(DefaultSessionProcessor.class);
    private Session session;
    private Timer timer;

    public DefaultSessionProcessor(Session session) {
        this.session = session;
    }

    @Override
    public String createSession() {
        String response = session.start();
        scheduleStopSession();

        return response;
    }

    /**
     * schedule session stop action
     */
    private void scheduleStopSession() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    session.stop();
                } catch (Exception e) {
                    logger.error("stop session failed!");
                }
            }
        }, session.getSessionExpireTimeInMilliSeconds());
    }

    @Override
    public void resetSessionTime(long sessionExpireTimeInMilliSeconds) {
        if (timer != null) {
            timer.cancel();
        }

        long runningTime = System.currentTimeMillis() - session.getStartTimeStamp();
        if (runningTime >= sessionExpireTimeInMilliSeconds) {
            session.stop();
        } else {
            session.setSessionExpireTimeInMilliSeconds(sessionExpireTimeInMilliSeconds - runningTime);
            scheduleStopSession();
        }
    }

}
