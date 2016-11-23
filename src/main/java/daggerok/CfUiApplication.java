package daggerok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CfUiApplication {

    /*

    mvn clean package
    cf dev start
    cf login -a https://api.local.pcfdev.io --skip-ssl-validation -u user -p pass
    cf push
    cf apps
    open http://cf-ui.local.pcfdev.io
    cf scale -i 3 cf-ui
    cf apps
    cf delete cf-ui -r -f

     */

    public static void main(String[] args) {
        SpringApplication.run(CfUiApplication.class, args);
    }
}
