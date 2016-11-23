package daggerok;

import daggerok.domain.User;
import daggerok.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@Slf4j
@SpringBootApplication
public class CfApplication {

    /*
    // local:
    docker-compose up -d
    gradle bootRun
    open http://localhost:8080/usres

    // cf:
    cf dev start
    cf login -a https://api.local.pcfdev.io --skip-ssl-validation -u user -p pass
    cf apps
    cf markeplace
    cf create-service p-mysql 512mb cf-app-mysql-db -c '{"ram_gb":1}'
    gradle build
    cf push -p build/libs/cf-0.0.1.jar cf-app --no-start
    cf bind-service cf-app cf-app-mysql-db
    # scale out to 3 instances
    cf scale -i 3 cf-app
    open http://cf-app.local.pcfdev.io/users
    */

    public static void main(String[] args) {
        SpringApplication.run(CfApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(final UserRepository users) {

        if (users.count() > 0) {
            return args -> log.info("skipping initialization...");
        }

        return args -> Stream.of("max", "bax")
                .map(username -> new User().setUsername(username))
                .forEach(users::save);
    }
}
