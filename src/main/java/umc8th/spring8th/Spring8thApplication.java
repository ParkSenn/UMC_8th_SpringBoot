package umc8th.spring8th;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Spring8thApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring8thApplication.class, args);
	}

}
