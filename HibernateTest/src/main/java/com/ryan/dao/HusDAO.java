package com.ryan.dao;

import com.ryan.po.Hus;
import com.ryan.po.Wife;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;



import java.util.List;

/**
 * Created by Administrator on 2019:04:01
 *
 * @Author : Lilanzhou
 * 功能 :
 */
public class HusDAO {
    //1
    Configuration config = null;
    SessionFactory sessionFactory = null;


    public HusDAO() {
        config = new Configuration().configure("hibernate.cfg.xml");
        sessionFactory = config.buildSessionFactory();
    }

    /**
     * 查询没有事务  不改变数据库的值
     */

    public void query() {
        Session session=sessionFactory.openSession();
         Query query=session.createQuery("from Hus");
           List<Hus> list= query.list();
        for (Hus h:list
             ) {
            if(h.getWife().getWname()!=null){
                System.out.println(h.getHname()+"--"+h.getWife().getWname());
            }else {
                System.out.println(h.getHname());
            }
        }
        session.close();

    }


    public void update() {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        try {
            Hus hus = session.load(Hus.class, 3);
            hus.getWife().setWname("小赵");
            hus.setHname("舟舟");
            session.update(hus);
            ts.commit();
            session.close();
        } catch (Exception e) {
            ts.rollback();
            e.printStackTrace();
        }
    }


    /**
     * 删除从表要解除  级联（cascadeType.ALL)
     *
     * 调用session的save和update能触发cascade.All
     */

    public void delWife() {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        try {
            Wife wife = session.get(Wife.class, 5);

            session.delete(wife);
            ts.commit();
            session.close();
        } catch (Exception e) {
            ts.rollback();
            e.printStackTrace();
        }
    }


    public void del() {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        try {
            Hus hus = session.load(Hus.class, 4);
            session.delete(hus);
            ts.commit();
            session.close();

        } catch (Exception e) {
            ts.rollback();
            e.printStackTrace();
        }
    }

    public void add() {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();

        try {
            Hus hus = new Hus();
            hus.setId(6);
            hus.setHname("h5665k");
            Wife wife = new Wife();
            wife.setId(6);
            wife.setWname("56565");
            hus.setWife(wife);
            wife.setHus(hus);
            session.save(hus);
            ts.commit();
            session.close();
        } catch (Exception e) {
            ts.rollback();
            e.printStackTrace();
        }

    }


}
