package vn.unicloud.fnb;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.annotation.PostConstruct;

@Slf4j
@EnableAsync
@EnableScheduling
@EnableWebSecurity
@SpringBootApplication
@EnableRedisRepositories
@RequiredArgsConstructor
public class Application2 implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application2.class, args);
    }

    @PostConstruct
    private void init() throws Exception {

    }

    @Override
    public void run(String... args) throws Exception {
    }

}
