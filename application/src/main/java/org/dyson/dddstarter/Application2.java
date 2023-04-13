package org.dyson.dddstarter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
@EnableAsync
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
