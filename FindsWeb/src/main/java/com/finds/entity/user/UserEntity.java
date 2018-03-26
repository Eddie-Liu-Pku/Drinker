package com.finds.entity.user;

import com.finds.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 67552 on 2015/8/30.
 */
@Entity
@Table(name="user")
public class UserEntity extends BaseEntity {
    private String id;
    private String name;
    private String psw;
    private String schuid;
    private String phone;
    private Integer lfnum;
    private Date uptime;
    private String userphoto;

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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column
    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
    @Column
    public String getSchuid() {
        return schuid;
    }

    public void setSchuid(String schuid) {
        this.schuid = schuid;
    }
    @Column
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column
    public Integer getLfnum() {
        return lfnum;
    }

    public void setLfnum(Integer lfnum) {
        this.lfnum = lfnum;
    }
    @Column
    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }


    @Column
    public String getUserphoto() {
        return userphoto;
    }

    public void setUserphoto(String userphoto) {
        this.userphoto = userphoto;
    }
}
