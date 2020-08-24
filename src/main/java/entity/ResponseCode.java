package entity;

/**
 * Created by shenjie on 2020/08/21.
 */
public enum ResponseCode {
    OK(200),
    ERROR(500);

    private int code;

    ResponseCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
