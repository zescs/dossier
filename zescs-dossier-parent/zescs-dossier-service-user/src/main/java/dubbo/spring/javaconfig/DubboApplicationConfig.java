package dubbo.spring.javaconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.zescs.config.app.ApplicationConfig;

@Configuration
@Import({ ApplicationConfig.class })
public class DubboApplicationConfig {

}
