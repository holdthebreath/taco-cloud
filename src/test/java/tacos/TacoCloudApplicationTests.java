package tacos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//JUnit注解,提供一个测试运行器以指导JUnit如何运行测试
//SpringRunner:Spring提供的测试运行器,它会创建运行所需Spring应用上下文
@RunWith(SpringRunner.class)
//告诉JUnit在启动测试时添上Spring Boot功能(视同为在main()方法中调用SpringApplication.run())
@SpringBootTest
class TacoCloudApplicationTests {

	@Test
	void contextLoads() {
	}

}
