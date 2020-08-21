package entity;

public class Session {
    private long sessionId;
    private long sessionTimeInMilliSeconds;

    public Session(long sessionId, long sessionTimeInMilliSeconds) {
        this.sessionId = sessionId;
        this.sessionTimeInMilliSeconds = sessionTimeInMilliSeconds;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
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
