package entity;

import java.util.Timer;
import java.util.TimerTask;

public class Session {
    private long sessionId;
    private long sessionTimeInMilliSeconds;
    private long startTimeStamp;
    private Timer timer = new Timer();
    private volatile boolean stopFlag = false;
    public Session(long sessionId, long sessionTimeInMilliSeconds) {
        this.sessionId = sessionId;
        this.sessionTimeInMilliSeconds = sessionTimeInMilliSeconds;
    }

    //TODO
    public void start() {
        this.startTimeStamp = System.currentTimeMillis();
        System.out.println("start:" + this.getSessionId() + ",time:" + this.getSessionTimeInMilliSeconds());
    }

    //TODO
    public void stop() {

    }
    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public long getStartTimeStamp() {
        return startTimeStamp;
    }

    public void setStartTimeStamp(long startTimeStamp) {
        this.startTimeStamp = startTimeStamp;
    }

    public long getSessionTimeInMilliSeconds() {
        return sessionTimeInMilliSeconds;
    }

    public void setSessionTimeInMilliSeconds(long sessionTimeInMilliSeconds) {
        this.sessionTimeInMilliSeconds = sessionTimeInMilliSeconds;
    }

    @Override
    public String toString() {
        return "entity.Session{" +
                "sessionId=" + sessionId +
                ", sessionTimeInMilliSeconds=" + sessionTimeInMilliSeconds +
                '}';
    }
}
