package fun.hijklmn.admin;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import fun.hijklmn.admin.conf.MySQLConf;
import fun.hijklmn.admin.conf.MySQLProdConf;

@Configuration
@Import({ MySQLConf.class, MySQLProdConf.class })
@ServletComponentScan
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ApplicationConf {

	private RestTemplate restTemplate;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// 单个数据大小
		factory.setMaxFileSize("40960KB");
		// 总上传数据大小
		factory.setMaxRequestSize("40960KB");
		return factory.createMultipartConfig();
	}

}
