package config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

import com.yhb.service.FilterService;

import Conditionals.MyLinuxConditional;

import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Conditional;

import domain.ImportTest;
import domain.Persion;
import domain.Son;
@PropertySource(value= {"classpath:my.properties"})
@Configuration
@ComponentScan(value="com.yhb",excludeFilters= {
		@Filter(type=FilterType.ANNOTATION,classes=Controller.class)
})
@Import({ImportTest.class})
public class PropertiesConfig {
	
	
	
	@Bean(initMethod="initmMethod",destroyMethod="destroyMethod")
	public Son son() {
		return new Son();
	}
	//使用@Conditional选择合适的bean放入容器
	@Conditional(value= {MyLinuxConditional.class})
	@Bean
	public Persion persion() {
		return new Persion();
	}
	
	@Bean("filterService1")
	@Primary//优先注入
	public FilterService filterService() {
		return new FilterService();
	}
}
