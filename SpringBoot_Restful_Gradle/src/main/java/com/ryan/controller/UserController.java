package com.ryan.controller;

import com.ryan.pojo.User;
import com.ryan.service.UserServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2019:04:17
 *
 * @Author : Lilanzhou
 * ���� :
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserServiceImpl service;
    @ApiOperation(value = "��ȡ�û��б�������ѯ")
    @ApiImplicitParam(name = "con",value = "��ѯ����",dataType = "String",required = true)
    @ApiResponses(value = {@ApiResponse(code = 200,message = "��ѯ�ɹ�"),
            @ApiResponse(code = 204,message = "��ѯʧ��")})
    @GetMapping(value = "/query/{con}")
    public ResponseEntity<List<User>> query(@PathVariable String con){
        List<User> list= service.query(con);
        // System.out.println(list);
        if(list.isEmpty()){
            return new ResponseEntity<List<User>>(list,HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<List<User>>(list, HttpStatus.OK);
        }
    }
    @ApiOperation(value = "����û�")
    @ApiImplicitParam(name = "user",value = "��ӵĶ���",dataType = "User",required = true)
    @PostMapping(value = "/add/")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "�ɹ�")})
    public ResponseEntity<Void> add(@RequestBody User user) {
        service.addUser(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ApiOperation(value = "ɾ���û�")
    //@ApiImplicitParam(name = "index",value = "��ӵĶ���",dataType = "String",required = true)
    @DeleteMapping(value = "/del/{index}/")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "ɾ���ɹ�")})
    public ResponseEntity<Void> del(@PathVariable String index) {
        service.delUser(index);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @ApiOperation(value = "�޸��û�")
    //@ApiImplicitParam(name = "index",value = "��ӵĶ���",dataType = "String",required = true)
    @PutMapping(value = "/put/{index}/{username}")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "�޸ĳɹ�")})
    public ResponseEntity<Void> update(@PathVariable String index,@PathVariable String username) {
        service.update(index,username);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
