package processor;

/**
 * Created by shenjie on 2020/08/21.
 */
public interface SessionProcessor {
    /**
     * create session
     */
    String createSession();

    /**
     * reset session expireTime
     *
     * @param sessionExpireTimeInMilliSeconds session expire time
     */
    void resetSessionExpireTime(long sessionExpireTimeInMilliSeconds);
}
