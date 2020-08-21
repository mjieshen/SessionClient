package processor;

public interface SessionProcessor {
    void createSession();

    void resetSessionTime(long sessionTimeInMilliSeconds);
}
