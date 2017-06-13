package cn.zucc.qifeng.toheartbyexpress.Bean;

import java.util.List;

/**
 * String user_account
 *
 */

public class Task {

    protected String user_account;
    protected String user_address;
    protected String user_phone;

    protected List<PurchaseItem> purcharseList;
    protected double sum;
    protected double longitude;
    protected double latitude;
    protected String deadline;
    protected String storename;


    public Task(String user_account, String user_address, String user_phone, List<PurchaseItem> purcharseList,
                double sum, double longitude, double latitude,String deadline,String storename) {
        this.user_account = user_account;
        this.user_address = user_address;
        this.user_phone = user_phone;
        this.purcharseList = purcharseList;
        this.sum = sum;
        this.longitude = longitude;
        this.latitude = latitude;
        this.deadline=deadline;
        this.storename=storename;
    }

    public Task() {
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }
    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public List<PurchaseItem> getPurcharseList() {
        return purcharseList;
    }

    public void setPurcharseList(List<PurchaseItem> purcharseList) {
        this.purcharseList = purcharseList;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
