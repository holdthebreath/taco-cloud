package tacos.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //用户信息储存在内存中
        auth.inMemoryAuthentication().withUser("buzz").password("infinity").authorities("ROLE_USER");
        auth.inMemoryAuthentication().withUser("woody").password("bullseye").authorities("ROLE_USER");
    }
}