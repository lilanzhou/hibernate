package com.ryan.po;

import javax.persistence.*;

/**
 * Created by Administrator on 2019:04:01
 *
 * @Author : Lilanzhou
 * 功能 :
 */
@Entity
public class Hus {
    private int id;
    private String hname;
    private Wife wife;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "hname", nullable = false, length = 10)
    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hus hus = (Hus) o;

        if (id != hus.id) return false;
        if (hname != null ? !hname.equals(hus.hname) : hus.hname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (hname != null ? hname.hashCode() : 0);
        return result;
    }

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }
}
