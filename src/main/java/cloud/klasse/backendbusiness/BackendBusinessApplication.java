package cloud.klasse.backendbusiness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy.RepositoryDetectionStrategies;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@SpringBootApplication
@Import(SecurityConfiguration.class)
public class BackendBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendBusinessApplication.class, args);
    }

    @Bean
    RepositoryRestConfigurer repositoryRestConfigurer() {
        return new RepositoryRestConfigurer() {
            @Override
            public void configureRepositoryRestConfiguration(final RepositoryRestConfiguration config) {
                config.setRepositoryDetectionStrategy(RepositoryDetectionStrategies.ALL);
                config.setReturnBodyForPutAndPost(true);
                config.setBasePath("/rest");
            }
        };
    }

}
