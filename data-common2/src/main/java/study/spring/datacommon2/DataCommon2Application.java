package study.spring.datacommon2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = SimpleMyRepository.class)
public class DataCommon2Application {


	public static void main(String[] args) {
		SpringApplication.run(DataCommon2Application.class, args);
	}

}
