package vn.unicloud.fnb.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
            .requestMatchers(HttpMethod.GET, "/test/anonymous", "/test/anonymous/**").permitAll()
            .requestMatchers("/",
                "/api-docs/**",
                "/webjars/**",
                "/swagger-ui/**",
                "/login**",
                "/error**",
                "/actuator/prometheus",
                "/api/*/diners/qrcode/**"
            ).permitAll()
            .anyRequest().authenticated();
        http.oauth2ResourceServer().jwt();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.oauth2Login(Customizer.withDefaults());
        http.oauth2Client();
        return http.build();
    }

}
