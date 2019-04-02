package com.ryan.po;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2019:04:02
 *
 * @Author : Lilanzhou
 * 功能 :
 */
@Entity(name = "Father")
public class Father {
    private int fid;
    private String fname;
    private Set<Sons> son=new HashSet<>();

    @Id
    @Column(name = "fid", nullable = false)
    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    @Basic
    @Column(name = "fname", nullable = true, length = 20)
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Father father = (Father) o;

        if (fid != father.fid) return false;
        if (fname != null ? !fname.equals(father.fname) : father.fname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fid;
        result = 31 * result + (fname != null ? fname.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "father",cascade = {CascadeType.ALL})
    public Set<Sons> getSon() {
        return son;
    }

    public void setSon(Set<Sons> son) {
        this.son = son;
    }
}
