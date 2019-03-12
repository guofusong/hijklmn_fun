package fun.hijklmn.stage;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import fun.hijklmn.stage.conf.MySQLConf;
import fun.hijklmn.stage.conf.MySQLProdConf;
import fun.hijklmn.stage.conf.SSLConf;

@Configuration
@Import({ MySQLConf.class, MySQLProdConf.class, SSLConf.class })
@ServletComponentScan
public class ApplicationConf {

}
