package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * @ClassName SecurityConfig
 * @Description Spring Security基础配置类
 * @Author hwd
 * @Date 2020/7/14 10:13 PM
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //配置使用JDBC对存储在关系型数据库中对用户信息认证
        auth.jdbcAuthentication().dataSource(dataSource)
            //自定义认证/用户权限/用户群组查询语句
            //需遵循查询基本协议:
            //1.所有查询将用户名作为唯一参数
            //2.身份验证查询选择用户名、密码和启用状态
            //3.授权查询选择包含用户名和授予的权限的零个或多个行的数据
            //4.群组权限查询选择零个或多个行数据,每个行有一个group id、一个组名和一个权限.
            .usersByUsernameQuery("select username, password, enabled form Users where username = ?")
        .authoritiesByUsernameQuery("select username, authority from UserAuthorities where username = ?")
        .groupAuthoritiesByUsername("xxxxxxxxxxxxxxx")
            //指定密码转码器
            //BCryptPasswordEncoder使用bcrypt强哈希加密
            //数据库密码永远不会解码
            //用户在登录时输入的密码使用相同的算法进行编码,然后将其与数据库中编码的密码进行比较
            //比较在PasswordEncoder的matches()方法中执行
        .passwordEncoder(new BCryptPasswordEncoder());
    }
}