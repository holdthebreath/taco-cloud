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
        //配置基于LDAP认证
        auth.ldapAuthentication()
            //为基础对LDAP查询提供过滤条件
            //默认情况下,对于用户和组对查询条件是空的(搜索会在LDAP层级结构的跟开始)
            //为查找用户提供基础查询(在名为people组织单元下搜索)
            .userSearchBase("ou=people")
            .userSearchFilter("(uid={0})")
            //为查找组提供基础查询(在名为groups组织单元下搜索)
            .groupSearchBase("ou=groups")
            .groupSearchFilter("member={0}")
            //通过密码进行比对(可选)
            //默认情况下,登陆表单提供的密码会与用户LDAP条目中的userPassword属性进行比对
            .passwordCompare()
            .passwordEncoder(new BCryptPasswordEncoder())
            //声明密码属性的名称
            .passwordAttribute("passcode")
            //通过root()指定嵌入式服务器根前缀
            //当LDAP服务器启动时,它会尝试在类路径下寻找LDIF(LDAP数据交换格式)文件加载数据
            .and().contextSource()
            .root("dc=tacocloud,dc=com")
            //指定加载哪个LDIF文件
            .ldif("classpath:users.ldif")
            //默认情况,Spring Security的LDAP认证假设LDAP服务器监听本机33389端口
            //contextSource()方法返回ContextSourceBuilder,其中提供了url()方法指定LDAP服务器的地址
            .and().contextSource().url("ldap://tacocloud.com:389/dc=tacocloud,dc=com");
    }
}