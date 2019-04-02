package com.ryan.dao;

import com.ryan.po.Student;
import com.ryan.po.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2019:04:02
 *
 * @Author : Lilanzhou
 * 功能 :
 */
public class TeacherDAO {
    Configuration config=null;
    SessionFactory sessionFactory=null;
    Session session=null;
    Transaction ts=null;
    public TeacherDAO(){
        config=new Configuration().configure("hibernate.cfg.xml");
        sessionFactory=config.buildSessionFactory();
    }
    public void add(){
        session=sessionFactory.openSession();
        ts=session.beginTransaction();
        try {
            Teacher teacher = new Teacher();
            teacher.setTid(3);
            teacher.setTname("刘老师");

            Teacher teacher1 = new Teacher();
            teacher1.setTid(4);
            teacher1.setTname("程老师");
            Set<Teacher> setTeacher = new HashSet<>();
            setTeacher.add(teacher);
            setTeacher.add(teacher1);
            Student student = new Student();
            student.setSid(3);
            student.setSname("张同学");

            Student student1 = new Student();
            student1.setSid(4);
            student1.setSname("吴同学");
            Set<Student> set = new HashSet<>();
            set.add(student);
            set.add(student1);
            teacher.setStudents(set);
            teacher1.setStudents(set);
            student.setTeachers(setTeacher);
            student1.setTeachers(setTeacher);
            session.save(teacher);
            session.save(teacher1);
            ts.commit();
            session.close();
        }catch (Exception e){
            ts.rollback();
            e.printStackTrace();
        }
    }
 public  void del(){
        session=sessionFactory.openSession();
        ts=session.beginTransaction();
        try {
            Teacher teacher = session.load(Teacher.class, 1);
            session.delete(teacher);
            ts.commit();
//            Student student=session.load(Student.class,1);
//            session.delete(student);
//            ts.commit();
            session.close();
        }catch (Exception e){
            ts.rollback();
            e.printStackTrace();
        }
 }
    public static void main(String[] args) {
        TeacherDAO dao=new TeacherDAO();
        //dao.add();
        dao.del();
    }

}
