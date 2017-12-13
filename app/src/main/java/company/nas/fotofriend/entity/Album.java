package company.nas.fotofriend.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by admin on 13.12.2017.
 */

public class Album extends RealmObject {

    @PrimaryKey
    private int aid;
    private int thumb_id;
    private int owner_id;
    private String thumb_src;
    private String title;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getThumb_id() {
        return thumb_id;
    }

    public void setThumb_id(int thumb_id) {
        this.thumb_id = thumb_id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getThumb_src() {
        return thumb_src;
    }

    public void setThumb_src(String thumb_src) {
        this.thumb_src = thumb_src;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
