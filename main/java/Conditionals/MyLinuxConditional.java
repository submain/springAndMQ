package Conditionals;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MyLinuxConditional implements Condition{

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		Environment environment =context.getEnvironment();
		// TODO Auto-generated method stub
		String osName = environment.getProperty("os.name");
		//��ȡ�������
		ClassLoader classLoader = context.getClassLoader();
		//��ȡ�������е�bean��ע����Ϣ
		BeanDefinitionRegistry beanDefinitionRegistry = context.getRegistry();
		//����ͨ���ж��������Ƿ�ע����ĳ��bean�������ж�    
		Boolean isExist  = beanDefinitionRegistry.containsBeanDefinition("son");
		if(isExist) {
			System.out.println("son����������д���");
		}else {
			System.out.println("son����������в�����");
		}
	
		if(osName.contains("linux")) {
			System.out.println("is linux");
			return true;
		}
		System.out.println("is windows");
		return false;
	}

}
