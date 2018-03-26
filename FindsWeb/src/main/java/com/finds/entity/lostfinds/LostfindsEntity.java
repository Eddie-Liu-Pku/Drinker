package com.finds.entity.lostfinds;

import com.finds.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 67552 on 2015/8/31.
 */
@Entity
@Table(name="lostfinds")
public class LostfindsEntity extends BaseEntity {
    private String id;
    private String thing;
    private String address;
    private String content;
    private String user_id;
    private Integer islost;
    private Integer isget;
    private  Integer commentnum;
    private Date pubtime;


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
    public String getThing() {
        return thing;
    }

    public void setThing(String thing) {
        this.thing = thing;
    }
    @Column
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Column
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    @Column
    public Integer getIslost() {
        return islost;
    }

    public void setIslost(Integer islost) {
        this.islost = islost;
    }
    @Column
    public Integer getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(Integer commentnum) {
        this.commentnum = commentnum;
    }
    @Column
    public Date getPubtime() {
        return pubtime;
    }

    public void setPubtime(Date pubtime) {
        this.pubtime = pubtime;
    }

    @Column
    public Integer getIsget() {
        return isget;
    }

    public void setIsget(Integer isget) {
        this.isget = isget;
    }
}
