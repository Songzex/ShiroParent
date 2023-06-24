package org.example.testjavashiro.singleReaml;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.testng.annotations.Test;

import java.util.Scanner;


public class TestJavaShiro {

    @Test
    public void  Test1(){
        //创建SecurityManager()对象
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //读取资源文件模仿与数据库交互然会获取对应数据
        IniRealm realm = new IniRealm("classpath:LikeReaml.ini");
        //设置raml对象给SectrityManger类
        securityManager.setRealm(realm);
        //绑定对应的sectritymanger
        SecurityUtils.setSecurityManager(securityManager);
        //获取当前用户对象
        Subject subject = SecurityUtils.getSubject();
        //模拟一下token等录的形式
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("scy", "scy123455");
        //模拟用处直接输出的 其实shiro内部的就是一token的信号是校验的
        Scanner scanner = new Scanner(System.in);
        String name=scanner.next();
        String pwd=scanner.next();
        Session session1 = subject.getSession();//获取一个和Shiro通信的会话session
//获取对象 的信息 是一个session对象 3-当前用户信息 操作一些事情--不需要web环境和其他容器支持
        /*Session session = subject.getSession();*/
/*        session.setAttribute("张三","zs123456");
        Object name1 = session.getAttribute("张三");
        System.out.println(" "+name1);*/
        //模拟用户登录了session保存了用户信息
        session1.setAttribute("name","pwd");
        //验证一下
        Object name1 = session1.getAttribute("name");
     /*   if(name1.equals()){


        }*/


    }












}
