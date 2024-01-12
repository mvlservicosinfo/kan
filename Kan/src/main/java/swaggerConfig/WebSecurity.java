package swaggerConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurity {


protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    auth.inMemoryAuthentication()
            .withUser("user")
            .password("1234")
            .roles("USER")
            .and()
            .withUser("admin")
            .password("admin")
            .roles("USER", "ADMIN");
}
    protected void configure(HttpSecurity http) throws Exception{
    http
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic()
            .and()
            .csrf().disable();
}
}
