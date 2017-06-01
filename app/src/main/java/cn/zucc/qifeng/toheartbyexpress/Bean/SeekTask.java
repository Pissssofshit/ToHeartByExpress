package cn.zucc.qifeng.toheartbyexpress.Bean;

/**
 * Created by 80421 on 2017/5/31.
 */

public class SeekTask {
    private String  longitude;
    private String latitude;
    private String city;
    private String address;
    private String user_account;

    public SeekTask(String longitude, String latitude, String address, String city) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.city = city;

    }




    public SeekTask() {
    }
    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }




}
