package platform;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CodeSharingPlatform {

    public static void main(String[] args) {
        SpringApplication.run(CodeSharingPlatform.class, args);
    }

    @Component
    public class Runner implements CommandLineRunner {
        private final CodeRepository repository;

        public Runner(CodeRepository repository) {
            this.repository = repository;
        }

        @Override
        public void run (String... args) {


        }
    }

}
