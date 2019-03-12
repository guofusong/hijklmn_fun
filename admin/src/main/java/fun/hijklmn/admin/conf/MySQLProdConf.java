package fun.hijklmn.admin.conf;

import java.net.MalformedURLException;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@PropertySource("classpath:/jdbc-prod.properties")
@Profile("prod")
public class MySQLProdConf implements EnvironmentAware {

	private Environment env;

	@Override
	public void setEnvironment(Environment env) {
		this.env = env;
	}

	@Bean(initMethod = "init", destroyMethod = "close")
	public DruidDataSource getDruidDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setInitialSize(3);
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl(env.getRequiredProperty("mysql.url"));
		dataSource.setUsername(env.getRequiredProperty("mysql.username"));
		dataSource.setPassword(env.getRequiredProperty("mysql.password"));
		dataSource.setMaxActive(env.getRequiredProperty("mysql.maxactive", Integer.class));
		dataSource.setMinIdle(env.getRequiredProperty("mysql.minidle", Integer.class));
		dataSource.setMaxWait(env.getRequiredProperty("mysql.maxwait", Integer.class));
		return dataSource;
	}

	@Bean
	public SqlSessionFactoryBean getSqlSessionFactoryBean() throws MalformedURLException {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(getDruidDataSource());
		bean.setTypeAliasesPackage("fun.hijklmn.model");
		return bean;
	}

	@Bean
	public MapperScannerConfigurer getMapperScannerConfigurer() {
		MapperScannerConfigurer configurer = new MapperScannerConfigurer();
		configurer.setBasePackage("fun.hijklmn.model");
		return configurer;
	}

}
