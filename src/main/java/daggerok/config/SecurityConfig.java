package daggerok.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    static final String[] ALLOWED_ANT_MATCHERS = {
            "/favicon.ico",
            "/index.html",
            "/vendors/**",
            "/app/**",
    };

    static final String[] ALLOWED_MVC_MATCHERS = {
            "/logout",
            "/login",
            "/404",
            "/me",
            "/",
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests()
                .antMatchers(ALLOWED_ANT_MATCHERS).permitAll()
                .mvcMatchers(ALLOWED_MVC_MATCHERS).permitAll()
            .anyRequest()
                .authenticated()
                .and()
            .httpBasic()
                .and()
            .formLogin()
                .permitAll()
                .and()
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .logoutSuccessUrl("/")
                .permitAll();
    }
}
