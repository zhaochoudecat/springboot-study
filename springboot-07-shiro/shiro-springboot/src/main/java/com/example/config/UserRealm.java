package com.example.config;

import com.example.pojo.User;
import com.example.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //拿到当前登录的这个对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();//拿到user对象

        //设置当前用户的权限
        info.addStringPermission(currentUser.getPerms());
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证的=>doGetAuthenticationInfo");
        // 用户名、密码， 数据中取
//        String name = "root";
//        String password = "123456";

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;

        User user =  userService.queryUserByName(userToken.getUsername());

//        if (!userToken.getUsername().equals(name)) {
//            return null;//抛出异常 UnknownAccountException
//        }

        if(user == null) {
            return null;
        }

        // 密码认证，shiro做
        //传入 user 认证的方法才可以获取到 （shiro构造器）
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
