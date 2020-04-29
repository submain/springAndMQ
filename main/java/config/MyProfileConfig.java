package config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class MyProfileConfig {
	@Bean
	@Profile("test")
	public DataSource dataSourceTest() throws PropertyVetoException {
		ComboPooledDataSource cDataSource = new ComboPooledDataSource();
		cDataSource.setPassword("");
		cDataSource.setUser("root");
		cDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
		cDataSource.setDriverClass("com.mysql.jdbc.Driver");
		return cDataSource;
	}
	
	@Bean
	@Profile("dev")
	public DataSource dataSourceDev() throws PropertyVetoException {
		ComboPooledDataSource cDataSource = new ComboPooledDataSource();
		cDataSource.setPassword("");
		cDataSource.setUser("root");
		cDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/inn");
		cDataSource.setDriverClass("com.mysql.jdbc.Driver");
		return cDataSource;
	}
	
	@Bean
	@Profile("pro")
	public DataSource dataSourcePro() throws PropertyVetoException {
		ComboPooledDataSource cDataSource = new ComboPooledDataSource();
		cDataSource.setPassword("");
		cDataSource.setUser("root");
		cDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/my");
		cDataSource.setDriverClass("com.mysql.jdbc.Driver");
		return cDataSource;
	}
	
}
