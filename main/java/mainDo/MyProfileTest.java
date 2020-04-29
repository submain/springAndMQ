package mainDo;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.MyProfileConfig;

public class MyProfileTest {
	
	@Test
	public void test_01() {
		//ͨ�������е���ʽ�����
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MyProfileConfig.class);
		beanNames(annotationConfigApplicationContext);
	}
	@Test
	public void TestDev() {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		//���ü���Ļ���
		annotationConfigApplicationContext.getEnvironment().setActiveProfiles("pro");
		//ע����������
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

