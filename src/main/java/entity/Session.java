package entity;

import com.alibaba.fastjson.JSON;
import utils.Constants;
import utils.HttpClient;

/**
 * Created by shenjie on 2020/08/21.
 */
public class Session implements Constants {
    private long sessionId;
    private long sessionTimeInMilliSeconds;
    private long startTimeStamp;
    private HttpClient httpClient = new HttpClient();

    public Session(long sessionId, long sessionTimeInMilliSeconds) {
        this.sessionId = sessionId;
        this.sessionTimeInMilliSeconds = sessionTimeInMilliSeconds;
    }

    /**
     * send start request
     *
     * @return
     */
    public String start() {
        startTimeStamp = System.currentTimeMillis();
        return sendRequest(ActionType.Start);
    }

    /**
     * send stop request
     *
     * @return
     */
    public String stop() {
        return sendRequest(ActionType.Stop);
    }

    /**
     * send request
     *
     * @param actionType
     * @return
     */
    private String sendRequest(ActionType actionType) {
        DeliverySessionCreationType deliverySession = build(actionType);
        String request = JSON.toJSONString(deliverySession);
        String response = httpClient.sendHttpPost(String.format(urlFormat, sessionId), request);

        return response;
    }

    /**
     * build requestBody
     *
     * @param actionType actionType
     * @return
     */
    private DeliverySessionCreationType build(ActionType actionType) {
        DeliverySessionCreationType deliverySession = new DeliverySessionCreationType();
        deliverySession.setDeliverySessionId(sessionId);
        deliverySession.setStartTime(startTimeStamp);
        deliverySession.setVersion(clientVersion);
        deliverySession.setActionType(actionType);

        if (ActionType.Stop.equals(actionType)) {
            deliverySession.setEndTime(System.currentTimeMillis());
        }

        return deliverySession;
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionId=" + sessionId +
                ", sessionTimeInMilliSeconds=" + sessionTimeInMilliSeconds +
                ", startTimeStamp=" + startTimeStamp +
                '}';
    }

    public long getStartTimeStamp() {
        return startTimeStamp;
    }

    public long getSessionTimeInMilliSeconds() {
        return sessionTimeInMilliSeconds;
    }

    public void setSessionTimeInMilliSeconds(long sessionTimeInMilliSeconds) {
        this.sessionTimeInMilliSeconds = sessionTimeInMilliSeconds;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
