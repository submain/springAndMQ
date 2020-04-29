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
		//获取类加载器
		ClassLoader classLoader = context.getClassLoader();
		//获取到容器中的bean的注册信息
		BeanDefinitionRegistry beanDefinitionRegistry = context.getRegistry();
		//可以通过判断容器中是否注入了某个bean来进行判断    
		Boolean isExist  = beanDefinitionRegistry.containsBeanDefinition("son");
		if(isExist) {
			System.out.println("son组件在容器中存在");
		}else {
			System.out.println("son组件在容器中不存在");
		}
	
		if(osName.contains("linux")) {
			System.out.println("is linux");
			return true;
		}
		System.out.println("is windows");
		return false;
	}

}
