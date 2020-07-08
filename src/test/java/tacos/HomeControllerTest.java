package tacos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @ClassName HomeControllerTest
 * @Description HomeController测试类
 * @Author hwd
 * @Date 2020/7/7 9:47 PM
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
//Spring Boot特殊测试注解,它会让这个测试在SpringMVC应用的上下文中执行(将输入的测试类型注册到SpringMVC中)
@WebMvcTest()
public class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHomePage() throws Exception{
        //对"/"路径发起HTTP GET请求
        mockMvc.perform(get("/")).
            //响应状态为HTTP200(OK)
            andExpect(status().isOk()).
            //视图对逻辑名称应该是"home"
            andExpect(view().name("home")).
            //渲染后对视图应该包含文本"I’ll be a Laker for the rest of my life"
            andExpect(content().string(containsString("I’ll be a Laker for the rest of my life")));
//        期望全部满足后表明测试通过
    }
}
