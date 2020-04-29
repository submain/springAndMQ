package mainDo;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.yhb.dao.FilterDao;
import com.yhb.service.FilterService;

import config.PropertiesConfig;
import domain.Persion;
import domain.Son;
public class MainTest {
	public static void  main(String []args) {
//	 ApplicationContext applicationContext =  new ClassPathXmlApplicationContext("beans.xml");
//	 Persion persion = (Persion) applicationContext.getBean("persion");
//	 System.out.println(persion);
		
//		AnnotationConfigApplicationContext  annotationConfigApplicationContext =new AnnotationConfigApplicationContext(MainConfig.class);
//		Persion persion = (Persion) annotationConfigApplicationContext.getBean("persion01");
//		System.out.println(persion);
//		String [] beanNames = annotationConfigApplicationContext.getBeanDefinitionNames();
//		for(String beanName :beanNames) {
//			System.out.println(beanName);
//		}
	}
	@Test
	public void test01() {
		
	}
	
	@Test
	public void test02 () {
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext  annotationConfigApplicationContext =new AnnotationConfigApplicationContext(PropertiesConfig.class);
		//printBeans(annotationConfigApplicationContext);
		System.out.println("========dddd=====");
		Persion persion = (Persion) annotationConfigApplicationContext.getBean("persion");
		System.out.println(persion);
		
		FilterDao filterDao = (FilterDao) annotationConfigApplicationContext.getBean("filterDao");
		System.out.println("filterDao"+filterDao);
		FilterService filterService = (FilterService) annotationConfigApplicationContext.getBean("filterService1");
		System.out.println("filterService"+filterService);
	}
	
	private void printBeans(AnnotationConfigApplicationContext applicationContext){
		String[] definitionNames = applicationContext.getBeanDefinitionNames();
		for (String name : definitionNames) {
			System.out.println(name);
		}
	}
}

