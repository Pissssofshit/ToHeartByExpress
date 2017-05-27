package cn.zucc.qifeng.toheartbyexpress.Bean;

/**
 * Created by 80421 on 2017/5/27.
 */

public class FeedBack {
    private int code;
    private String message;
    public FeedBack(){}

    public FeedBack(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
