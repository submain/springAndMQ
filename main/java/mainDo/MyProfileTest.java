package mainDo;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.MyProfileConfig;

public class MyProfileTest {
	
	@Test
	public void test_01() {
		//通过命令行的形式激活环境
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MyProfileConfig.class);
		beanNames(annotationConfigApplicationContext);
	}
	@Test
	public void TestDev() {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		//设置激活的环境
		annotationConfigApplicationContext.getEnvironment().setActiveProfiles("pro");
		//注册主配置类
		annotationConfigApplicationContext.register(MyProfileConfig.class);
		annotationConfigApplicationContext.refresh();
		beanNames(annotationConfigApplicationContext);
	}
	
	public void beanNames(AnnotationConfigApplicationContext annotationConfigApplicationContext) {
		String beanNames []  = annotationConfigApplicationContext.getBeanDefinitionNames();
		for(String s:beanNames) {
			System.out.println(s);
		}
	}
	
	

}

