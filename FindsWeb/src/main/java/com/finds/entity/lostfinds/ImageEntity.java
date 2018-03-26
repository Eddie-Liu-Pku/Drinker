package com.finds.entity.lostfinds;

import com.finds.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
/**
 * Created by 67552 on 2015/11/5.
 */
@Entity
@Table(name="image")
public class ImageEntity extends BaseEntity{
    private String id;
    private String lostfind_id;
    private String url;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Column
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    @Column
    public String getLostfind_id() {
        return lostfind_id;
    }

    public void setLostfind_id(String lostfind_id) {
        this.lostfind_id = lostfind_id;
    }
}
