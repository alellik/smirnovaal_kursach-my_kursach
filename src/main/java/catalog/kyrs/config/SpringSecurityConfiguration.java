package catalog.kyrs.config;

import catalog.kyrs.model.UserAuthority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(expressionInterceptUrlRegistry ->
                expressionInterceptUrlRegistry

                        .requestMatchers("/registration", "/login").permitAll()


                        .requestMatchers(HttpMethod.GET, "/cinemas/**").hasAnyAuthority(UserAuthority.CUSTOMERS.getAuthority(), UserAuthority.ADMINISTRATOR.getAuthority())

                        .requestMatchers(HttpMethod.GET, "/comments/**").hasAnyAuthority(UserAuthority.CUSTOMERS.getAuthority(), UserAuthority.ADMINISTRATOR.getAuthority())
                        .requestMatchers(HttpMethod.POST, "/comments").hasAnyAuthority(UserAuthority.CUSTOMERS.getAuthority(), UserAuthority.ADMINISTRATOR.getAuthority())

                        .requestMatchers(HttpMethod.POST, "/likes").hasAnyAuthority(UserAuthority.CUSTOMERS.getAuthority(), UserAuthority.ADMINISTRATOR.getAuthority())



                        .requestMatchers(HttpMethod.POST, "/cinemas").hasAuthority(UserAuthority.ADMINISTRATOR.getAuthority())
                        .requestMatchers(HttpMethod.PUT, "/cinemas/{id}").hasAuthority(UserAuthority.ADMINISTRATOR.getAuthority())
                        .requestMatchers(HttpMethod.DELETE, "/cinemas/{id}").hasAuthority(UserAuthority.ADMINISTRATOR.getAuthority())

                        .requestMatchers(HttpMethod.PUT, "/comments/{id}").hasAuthority(UserAuthority.ADMINISTRATOR.getAuthority())
                        .requestMatchers(HttpMethod.DELETE, "/comments/{id}").hasAuthority(UserAuthority.ADMINISTRATOR.getAuthority())

                        .requestMatchers(HttpMethod.GET, "/likes/**").hasAuthority(UserAuthority.ADMINISTRATOR.getAuthority())
                        .requestMatchers(HttpMethod.PUT, "/likes/{id}").hasAuthority(UserAuthority.ADMINISTRATOR.getAuthority())
                        .requestMatchers(HttpMethod.DELETE, "/likes/{id}").hasAuthority(UserAuthority.ADMINISTRATOR.getAuthority())

                        .requestMatchers(HttpMethod.GET, "/directors/**").hasAuthority(UserAuthority.ADMINISTRATOR.getAuthority())
                        .requestMatchers(HttpMethod.POST, "/directors").hasAuthority(UserAuthority.ADMINISTRATOR.getAuthority())
                        .requestMatchers(HttpMethod.PUT, "/directors/{id}").hasAuthority(UserAuthority.ADMINISTRATOR.getAuthority())
                        .requestMatchers(HttpMethod.DELETE, "/directors/{id}").hasAuthority(UserAuthority.ADMINISTRATOR.getAuthority())

                        .requestMatchers(HttpMethod.GET, "/screenwriters/**").hasAuthority(UserAuthority.ADMINISTRATOR.getAuthority())
                        .requestMatchers(HttpMethod.POST, "/screenwriters").hasAuthority(UserAuthority.ADMINISTRATOR.getAuthority())
                        .requestMatchers(HttpMethod.PUT, "/screenwriters/{id}").hasAuthority(UserAuthority.ADMINISTRATOR.getAuthority())
                        .requestMatchers(HttpMethod.DELETE, "/screenwriters/{id}").hasAuthority(UserAuthority.ADMINISTRATOR.getAuthority())

                        .anyRequest().hasAuthority(UserAuthority.FULL.getAuthority()))
                .formLogin(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
