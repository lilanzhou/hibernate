package com.ryan.service;

import com.ryan.pojo.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Administrator on 2019:04:17
 *
 * @Author : Lilanzhou
 * ¹¦ÄÜ :
 */
@Service
public class UserServiceImpl {
    private List<User> users=new ArrayList<User>(){
        {
            add(new User("ryan","1sas23"));
            add(new User("rian","123asd"));
            add(new User("ruao","12ew3"));
            add(new User("rava","1223"));

        }
    };

    public List query(String con){
        List list= new ArrayList();
        Predicate<User> predicate=(p)->(p.getUsername().startsWith(con));

        list= Arrays.asList(users.stream().filter(predicate).toArray());

      //  System.out.println(list);
        return  list;
    }
    public void addUser(User user){
        users.add(user);
    }
    public void delUser(String id){
        users.remove(Integer.parseInt(id));

    }
    public void update(String id,String username){
        User user = users.get(Integer.parseInt(id));
        user.setUsername(username);
    }
}
