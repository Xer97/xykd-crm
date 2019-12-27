package bootcrm.common;

/**
 * @author xer97
 * @date 2019/3/31 12:32
 */
public enum ResponseCode {
    /**
     * success
     */
    SUCCESS(0, "SUCCESS"),
    /**
     * error
     */
    ERROR(1, "ERROR"),
    /**
     * need login
     */
    NEED_LOGIN(10, "NEED_LOGIN"),
    /**
     * illegal argument
     */
    ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
