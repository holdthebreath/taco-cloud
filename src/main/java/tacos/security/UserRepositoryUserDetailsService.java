package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tacos.User;
import tacos.data.UserRepository;

/**
 * @ClassName UserRepositoryUserDetailsService
 * @Description 用户详细信息服务类
 * @Author hwd
 * @Date 2020/7/15 2:30 PM
 * @Version 1.0
 */
//构造型注解,将该类标记为包含在Spring组件扫描中
@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    private UserRepositoryUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user != null){
            return user;
        }
        throw new UsernameNotFoundException("User '" + username + "' not found!");
    }
}
