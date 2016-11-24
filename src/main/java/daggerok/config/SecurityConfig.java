package daggerok.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    static final String[] ALLOWED_RESOURCES = {
            "/node_modules/**",
            "/index.html",
            "/css/**",
            "/js/**",
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .httpBasic()
                .and()
            .authorizeRequests()
                .antMatchers(ALLOWED_RESOURCES)
                .permitAll()
            .anyRequest()
                .authenticated();
    }
}
