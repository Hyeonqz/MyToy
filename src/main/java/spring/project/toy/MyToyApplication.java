package spring.project.toy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import jakarta.persistence.Entity;

@SpringBootApplication
@ComponentScan("spring.project.*")
@EntityScan("spring,project.dto")
@EnableJpaRepositories("spring.project.*")
public class MyToyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyToyApplication.class, args);
	}

}
