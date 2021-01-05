package org.zhuch.dreamblog;

import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

@EnableJdbcAuditing
@SpringBootApplication
public class DreamblogApplication {
    public static void main(String[] args) {
        SpringApplication.run(DreamblogApplication.class, args);
    }
}

