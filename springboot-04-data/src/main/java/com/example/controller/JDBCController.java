package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JDBCController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //查询数据库所有信息
    @GetMapping("/users")
    public List<Map<String,Object>> userList(){
        String sql = "select * from user";
        List<Map<String,Object>> mapList = jdbcTemplate.queryForList(sql);
        return mapList;
    }

    //添加用户
    @GetMapping("/addUser")
    public String addUser(){
        String sql = "insert into mybatis.user(id,name,pwd) values(6,'hello','123')";
        jdbcTemplate.update(sql);
        return "addUser-ok";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id")int id){
        String sql = "update mybatis.user set name=?,pwd=? where id=" + id;
        //封装
        Object[] objects = new Object[2];
        objects[0]="world";
        objects[1]="321";
        jdbcTemplate.update(sql,objects);
        return "updateUser-ok";
    }

    //删除
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id")int id){
        String sql = "delete from mybatis.user  where id=?";
        jdbcTemplate.update(sql,id);
        return "deleteUser-ok";
    }



}
