package bmcsdl_02.bmcsdl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("bmcsdl_02.bmcsdl.Repository")
public class BmcsdlApplication {

	public static void main(String[] args) {
		SpringApplication.run(BmcsdlApplication.class, args);
	}

}
