package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName HomeController
 * @Description 主页面控制器类
 * @Author hwd
 * @Date 2020/7/7 9:31 PM
 * @Version 1.0
 */
@Controller
public class HomeController {

//针对路径发起的Get请求
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
