package com.ryan.po;

import javax.persistence.*;

/**
 * Created by Administrator on 2019:04:02
 *
 * @Author : Lilanzhou
 * 功能 :
 */
@Entity
public class Sons {
    private int sid;
    private String sname;
    private Father father;

    @Id
    @Column(name = "sid", nullable = false)
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "sname", nullable = true, length = 20)
    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sons sons = (Sons) o;

        if (sid != sons.sid) return false;
        if (sname != null ? !sname.equals(sons.sname) : sons.sname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sid;
        result = 31 * result + (sname != null ? sname.hashCode() : 0);
        return result;
    }

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "fid", referencedColumnName = "fid" )
    public Father getFather() {
        return father;
    }

    public void setFather(Father father) {
        this.father = father;
    }
}
