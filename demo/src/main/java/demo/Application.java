package demo;

import com.endofmaster.rest.EnableCommonsRest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZM.Wang
 */
@SpringBootApplication
@EnableCommonsRest
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

}
