package com.ryan.dao;

import com.ryan.po.Father;
import com.ryan.po.Sons;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;


import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2019:04:02
 *
 * @Author : Lilanzhou
 * 功能 :
 */
public class FatherDAO {
    Configuration config = null;
    SessionFactory sessionFactory = null;
    Session session = null;
    Transaction ts = null;

    public FatherDAO() {
        config = new Configuration().configure("hibernate.cfg.xml");
        sessionFactory = config.buildSessionFactory();
    }

    public void add() {

        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        try {
            Father father = new Father();
            father.setFid(6);
            father.setFname("测试father");
            Sons son = new Sons();
            son.setSid(5);
            son.setSname("第一个儿子");
            Sons son1 = new Sons();
            son1.setSid(6);
            son1.setSname("第二个儿子");
            son.setFather(father);
            son1.setFather(father);
            Set<Sons> set = new HashSet<>();
            set.add(son);
            set.add(son1);
            father.setSon(set);
            session.save(father);
            ts.commit();
            session.close();
        } catch (Exception e) {
            ts.rollback();
            e.printStackTrace();
        }

    }

    /**
     * 因为设置了 级联cascade.ALL 所以当删除主键父亲时儿子也会被删除
     */
    public void delFather() {
        session = sessionFactory.openSession();
        ts = session.beginTransaction();
        try {
            Father father = session.load(Father.class, 2);
            session.delete(father);
            ts.commit();
            session.close();
        } catch (Exception e) {
            ts.rollback();
            e.printStackTrace();
        }
    }

    /**
     * 删除从表时 从表上要解除级联 不然删一个从表会导致主表被删 主表的其他从表也会被删除
     */
    public void delSon() {
        session = sessionFactory.openSession();
        ts = session.beginTransaction();
        try {
            Sons sons = session.load(Sons.class, 6);
            session.delete(sons);
            ts.commit();
            session.close();
        } catch (Exception e) {
            ts.rollback();
            e.printStackTrace();
        }
    }

    public void update() {
        session = sessionFactory.openSession();
        ts = session.beginTransaction();
        try {
            Father father = session.load(Father.class, 3);
            father.setFname("update测2试");
            Set<Sons> set = father.getSon();
            for (Sons s : set
                    ) {
                if (s.getSid() == 2) {
                    s.setSname("update2号名字");
                }
            }
            session.update(father);
            ts.commit();
            session.close();
        } catch (Exception e) {
            ts.rollback();
            e.printStackTrace();
        }
    }

    public void query() {
        session = sessionFactory.openSession();
       Query query = session.createQuery("from Father as a");//需要写hql语句
       // Criteria criteria=session.createCriteria(Father.class);//不需要写sql
       // SQLQuery sqlQuery=session.createSQLQuery("select * from father");//需要写sql
       // NativeQuery nativeQuery = sqlQuery.addEntity(Father.class);
      //  List<Father>list1= nativeQuery.list();

      List<Father> list = query.list();
        for (Father f : list
                ) {
            System.out.println(f.getFname());
            Set<Sons> son = f.getSon();
            Iterator<Sons> iterator = son.iterator();
            if (iterator.hasNext()) {
                System.out.print(iterator.next().getSname());
            }

        }
        session.close();
    }
    public void testLoad(){
        Set<Sons> sonss = querySon();
        for (Sons s:sonss
             ) {
            System.out.println(s.getFather().getFname()+"---"+s.getSname());
        }

    }
    public Set<Sons> querySon(){
            session=sessionFactory.openSession();
            Father father = session.load(Father.class, 2);
               Set<Sons>sons=father.getSon();
            if (!Hibernate.isInitialized(sons)) {
                Hibernate.initialize(sons);
            }

            session.close();
        return sons;

    }

    public static void main(String[] args) {
        FatherDAO dao = new FatherDAO();
        dao.add();
        dao.delFather();
        dao.delSon();
        dao.update();
        dao.query();
        dao.testLoad();
    }

}
