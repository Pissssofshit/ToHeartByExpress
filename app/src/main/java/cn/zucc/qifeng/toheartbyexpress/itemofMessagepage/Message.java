package cn.zucc.qifeng.toheartbyexpress.itemofMessagepage;

/**
 * Created by 80421 on 2017/5/30.
 */

public class Message {
    private String title;
    private String details;
    private int photoId;

    public Message(String title, String details, int photoId){
        this.title=title;
        this.details=details;
        this.photoId=photoId;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }
}
