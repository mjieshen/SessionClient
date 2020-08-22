package entity;

import com.alibaba.fastjson.JSON;
import utils.Constants;
import utils.HttpUtils;

public class Session implements Constants {
    private long sessionId;
    private long sessionTimeInMilliSeconds;
    private long startTimeStamp;

    public Session(long sessionId, long sessionTimeInMilliSeconds) {
        this.sessionId = sessionId;
        this.sessionTimeInMilliSeconds = sessionTimeInMilliSeconds;
    }

    public String start() {
        startTimeStamp = System.currentTimeMillis();
        return sendRequest(ActionType.Start);
    }

    public String stop() {
        return sendRequest(ActionType.Stop);
    }

    private String sendRequest(ActionType actionType) {
        DeliverySessionCreationType deliverySession = build(actionType);
        String request = JSON.toJSONString(deliverySession);
        String response = HttpUtils.sendHttpPost(String.format(urlFormat, sessionId), request);

        return response;
    }

    private DeliverySessionCreationType build(ActionType actionType) {
        DeliverySessionCreationType deliverySession = new DeliverySessionCreationType();
        deliverySession.setDeliverySessionId(sessionId);
        deliverySession.setStartTime(startTimeStamp);
        deliverySession.setEndTime(startTimeStamp + sessionTimeInMilliSeconds);
        deliverySession.setVersion(clientVersion);
        deliverySession.setActionType(actionType);

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
}
