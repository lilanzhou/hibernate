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
@Entity
public class Teacher {
    private int tid;
    private String tname;
    private Set<Student> students=new HashSet<>();

    @Id
    @Column(name = "tid", nullable = false)
    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    @Basic
    @Column(name = "tname", nullable = true, length = 20)
    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (tid != teacher.tid) return false;
        if (tname != null ? !tname.equals(teacher.tname) : teacher.tname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tid;
        result = 31 * result + (tname != null ? tname.hashCode() : 0);
        return result;
    }

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "teastu", catalog = "demo", schema = "demo", joinColumns = @JoinColumn(name = "tid", referencedColumnName = "tid", nullable = false), inverseJoinColumns = @JoinColumn(name = "sid", referencedColumnName = "sid", nullable = false))
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
