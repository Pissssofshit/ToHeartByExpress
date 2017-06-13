package cn.zucc.qifeng.toheartbyexpress.Bean;

/**
 * Created by 80421 on 2017/5/27.
 */

public class FeedBack {
    private int code;
    private String message;
    private String user_account;

    public FeedBack() {
    }

    public FeedBack(int code, String message, String user_account) {
        this.code = code;
        this.message = message;
        this.user_account = user_account;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
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
