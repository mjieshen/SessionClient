package entity;

/**
 * Created by shenjie on 2020/08/21.
 */
public class DeliverySessionCreationType {
    private long deliverySessionId;
    private ActionType actionType;
    private long startTime;
    private long endTime;

    private String version;

    private String tmgiPool;
    private String tmgi;

    public long getDeliverySessionId() {
        return deliverySessionId;
    }

    public void setDeliverySessionId(long deliverySessionId) {
        this.deliverySessionId = deliverySessionId;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTmgiPool() {
        return tmgiPool;
    }

    public void setTmgiPool(String tmgiPool) {
        this.tmgiPool = tmgiPool;
    }

    public String getTmgi() {
        return tmgi;
    }

    public void setTmgi(String tmgi) {
        this.tmgi = tmgi;
    }

    @Override
    public String toString() {
        return "DeliverySessionCreationType{" +
                "deliverySessionId=" + deliverySessionId +
                ", actionType=" + actionType +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", version='" + version + '\'' +
                ", tmgiPool='" + tmgiPool + '\'' +
                ", tmgi='" + tmgi + '\'' +
                '}';
    }
}
