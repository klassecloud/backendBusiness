package cloud.klasse.backendbusiness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SecurityConfiguration.class)
public class BackendBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendBusinessApplication.class, args);
    }

}
