package com.cg.baseproject.security.authority;
 
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
 
public class TestShiro {
    public static void main(String[] args) {
    	
    	DefaultSecurityManager securityManager = new DefaultSecurityManager();
        IniRealm iniRealm = new IniRealm("classpath:TestShiro.ini");
        securityManager.setRealm(iniRealm);

        SecurityUtils.setSecurityManager(securityManager);

        // Now that a simple Shiro environment is set up, let's see what you can do:

        // get the currently executing user:
        Subject currentUser = SecurityUtils.getSubject();

        //从配置文件中读取用户的权限信息  构建SecurityManager对象
//    	DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
//    	IniRealm iniRealm=new IniRealm();
//    	defaultSecurityManager.setRealm(iniRealm);
    	

		/*
		 * @SuppressWarnings("deprecation") Factory<SecurityManager> factory = new
		 * IniSecurityManagerFactory("classpath:ShiRo.ini"); SecurityManager
		 * securityManager = factory.getInstance();
		 * SecurityUtils.setSecurityManager(securityManager);
		 */
    	//把defaultSecurityManager绑定到setSecurityManager
//    	SecurityUtils.setSecurityManager(defaultSecurityManager);
    	
        //获取当前的用户
//        Subject currentUser =SecurityUtils.getSubject();
 
 
 
        // 登陆 (用户输入的用户名跟密码)
        UsernamePasswordToken token = new UsernamePasswordToken("Admin", "123456");
        //相当于记住密码
        token.setRememberMe(true);
 
        try {
            currentUser.login( token );
            System.out.println("用户:" + currentUser.getPrincipal() + "token："+token.toString() +" 登陆成功！");
        } catch (UnknownAccountException uae) {
            System.out.println("用户名不存在:" + token.getPrincipal());
        } catch (IncorrectCredentialsException ice) {
            System.out.println("密码不正确!!");
        } catch (LockedAccountException lae) {
            System.out.println("用户被锁定!!");
        }
 
        //isAuthenticated() 当登录成功就返回true 失败就返回false
        if (currentUser.isAuthenticated()) {
            //测试用户是否拥有某个角色
            System.out.println("是否拥有 admin 角色: " + currentUser.hasRole("admin"));
 
            //测试是否拥有具体的权限
            System.out.println("是否拥有 user:query:1 权限: " + currentUser.isPermitted("user:query:1"));
 
            //退出
            currentUser.logout();
 
        }
    }
}