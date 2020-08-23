package entity;

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
