package cn.zucc.qifeng.toheartbyexpress.Bean;

/**
 * Created by 80421 on 2017/6/6.
 */

public class PurchaseItem {
    private String name;
    private String details;
    private int number;
    private double price;

    public PurchaseItem() {

    }

    public PurchaseItem(String name, String details,int number, double price) {
        this.name = name;
        this.details = details;
        this.number = number;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


}
