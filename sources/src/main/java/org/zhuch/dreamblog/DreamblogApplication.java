package org.zhuch.dreamblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

@EnableJdbcAuditing
@SpringBootApplication
public class DreamblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(DreamblogApplication.class, args);
	}

}
