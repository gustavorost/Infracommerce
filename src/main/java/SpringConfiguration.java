

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import br.com.infracommerce.configuration.JpaConfiguration;

@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"br.com.infracommerce"})
public class SpringConfiguration {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringConfiguration.class, args);
	}

}
