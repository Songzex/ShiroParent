package org.example.testjavashiro.singleReaml;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.testng.annotations.Test;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**这是一个纯正的java 项目
 * shiro 的基本实现功能和主要的思想 主要就是这些
 * 无非就是一些在添加一些扩展和和填充应对不同策略的实现
 * **/
public class TestLoginShiro {
    @Test
    public void   Testlogin(){
        //创建管理者SecurityManger
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //创建reaml类 真正交互db的类
        IniRealm realm = new IniRealm("classpath:LikeReaml.ini");
        //设置reaml类给SecrurityManger类
        securityManager.setRealm(realm);
        //工具类 SecurityUtils 来协作实现
        SecurityUtils.setSecurityManager(securityManager);
        //获取当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        //模拟前端发过来的 一个验证信息对象 token
        UsernamePasswordToken token = new UsernamePasswordToken("system","system01");
        //根据提供的token 登录、 不同的登录情况报错
        //密码不对的时候的异常： org.apache.shiro.authc.IncorrectCredentialsException
        //账户不对的情况下：org.apache.shiro.authc.UnknownAccountException
        // 或者执行业务
        //我们铺货异常
        try {
            //用shiro提供的login()方法 进行等录
            subject.login(token);
        }catch (IncorrectCredentialsException e){
            System.out.println("密码不对");
        }catch (UnknownAccountException e){
            System.out.println("账户未知");
        }

        if(!subject.isAuthenticated()){
            System.out.println("登录、" +
                    "不成功");

        }else {
            System.out.println(" login successful");
             if (subject.hasRole("user")){
                 System.out.println("你是用户");
             }else {
                 System.out.println("你可能是管理员什么其他的");
             }if (subject.isPermitted("user:insert")){
                System.out.println("你有对应的权限资源");
            }else {
                 log.println("但是你没有一user:update开头的权限");
            }

        }
        //用提供的方法模拟等出功能
      /*  subject.login(token);*/


    }







}
