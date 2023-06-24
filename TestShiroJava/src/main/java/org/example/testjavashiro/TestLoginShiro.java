package org.example.testjavashiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.testng.annotations.Test;
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
        UsernamePasswordToken token = new UsernamePasswordToken("system","system1");
        //根据提供的token 登录、 不同的登录情况报错
        //密码不对的时候的异常： org.apache.shiro.authc.IncorrectCredentialsException
        //账户不对的情况下：org.apache.shiro.authc.UnknownAccountException
        // 或者执行业务
        try {
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


        }



    }







}
