package cn.zucc.qifeng.toheartbyexpress.Bean;

/**
 * Created by 80421 on 2017/5/27.
 */

public class User {


    private String user_account;
    private String user_password;
    private String user_name;

    public User(){}
    public User(String user_account,String user_password){
        this.user_account=user_account;
        this.user_password=user_password;

    }
    public User(String user_account,String user_password,String user_name){
        this.user_account=user_account;
        this.user_password=user_password;
        this.user_name=user_name;
    }
    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }





}
