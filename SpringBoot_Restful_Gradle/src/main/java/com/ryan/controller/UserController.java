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
 * 功能 :
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserServiceImpl service;
    @ApiOperation(value = "获取用户列表含条件查询")
    @ApiImplicitParam(name = "con",value = "查询条件",dataType = "String",required = true)
    @ApiResponses(value = {@ApiResponse(code = 200,message = "查询成功"),
            @ApiResponse(code = 204,message = "查询失败")})
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
    @ApiOperation(value = "添加用户")
    @ApiImplicitParam(name = "user",value = "添加的对象",dataType = "User",required = true)
    @PostMapping(value = "/add/")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "成功")})
    public ResponseEntity<Void> add(@RequestBody User user) {
        service.addUser(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ApiOperation(value = "删除用户")
    //@ApiImplicitParam(name = "index",value = "添加的对象",dataType = "String",required = true)
    @DeleteMapping(value = "/del/{index}/")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "删除成功")})
    public ResponseEntity<Void> del(@PathVariable String index) {
        service.delUser(index);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @ApiOperation(value = "修改用户")
    //@ApiImplicitParam(name = "index",value = "添加的对象",dataType = "String",required = true)
    @PutMapping(value = "/put/{index}/{username}")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "修改成功")})
    public ResponseEntity<Void> update(@PathVariable String index,@PathVariable String username) {
        service.update(index,username);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
