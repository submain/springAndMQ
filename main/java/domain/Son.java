package domain;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
//Ϊ�����ע��IOC����
//ʹ��valueΪ���Ը�ֵ
//ʹ�ó�ʼ����������bean����������
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
	 * ��ʼ��ǰ����
	 */
	public void initmMethod() {
		System.out.println("bean"+this.getClass()+"��ʼ��...");
	}
	/**
	 * ���ٷ���
	 */
	public void destroyMethod() {
		System.out.println("bran"+this.getClass()+"����....");
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		//�����ע��IOC����
		this.applicationContext = applicationContext;
	}

}
