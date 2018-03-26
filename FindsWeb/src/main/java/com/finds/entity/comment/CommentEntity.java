package com.finds.entity.comment;

import com.finds.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by 67552 on 2015/9/1.
 */
@Entity
@Table(name="comment")
public class CommentEntity extends BaseEntity {
    private String id;
    private String commcon;
    private String lf_id;
    private String user_id;
    private String com_id;
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
    public String getCommcon() {
        return commcon;
    }

    public void setCommcon(String commcon) {
        this.commcon = commcon;
    }
    @Column
    public String getLf_id() {
        return lf_id;
    }

    public void setLf_id(String lf_id) {
        this.lf_id = lf_id;
    }
    @Column
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    @Column
    public String getCom_id() {
        return com_id;
    }

    public void setCom_id(String com_id) {
        this.com_id = com_id;
    }
}
