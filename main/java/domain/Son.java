package domain;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
//为组件中注入IOC容器
//使用value为属性赋值
//使用初始化方法管理bean的生命周期
@Component
public class Son implements ApplicationContextAware  {
	
	private  ApplicationContext applicationContext;
	@Value("${person.nickName}")
	private String name;
	public static String age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static String getAge() {
		return age;
	}
	public static void setAge(String age) {
		Son.age = age;
	}
	@Override
	public String toString() {
		return "Son [name=" + name + "]";
	}
	/**
	 * 初始化前方法
	 */
	public void initmMethod() {
		System.out.println("bean"+this.getClass()+"初始化...");
	}
	/**
	 * 销毁方法
	 */
	public void destroyMethod() {
		System.out.println("bran"+this.getClass()+"销毁....");
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		//组件中注入IOC容器
		this.applicationContext = applicationContext;
	}

}
