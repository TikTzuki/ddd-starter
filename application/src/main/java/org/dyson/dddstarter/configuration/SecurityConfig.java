package org.dyson.dddstarter.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Configuration
@EnableWebSecurity
//@RequiredArgsConstructor
public class SecurityConfig {
    public static final String ADMIN = "admin";
    public static final String USER = "user";
    private final JwtAuthenticationConverter jwtAuthConverter = new JwtAuthenticationConverter();
//    private final JwtGrantedAuthoritiesConverter jwtAuthConverter;

    //    @Bean
//    SecurityFilterChain oauth2SecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
//        http.oauth2Login(Customizer.withDefaults());
//        http.oauth2Client();
//        return http.build();
//    }
//    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
//        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
//        jwtConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());
//        return jwtConverter;
//    }

    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtConverter() {
        log.info("----------> create {}");
        return new Converter<Jwt, AbstractAuthenticationToken>() {
            private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();


            @Override
            public AbstractAuthenticationToken convert(Jwt jwt) {
                log.info("----------> conver {}", jwt.toString());
                System.out.println("-----------------> ");
                Collection<GrantedAuthority> authorities = Stream.concat(
                    jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                    extractResourceRoles(jwt).stream()).collect(Collectors.toSet());
                return new JwtAuthenticationToken(jwt, authorities);
            }

//            private String getPrincipalClaimName(Jwt jwt) {
//                String claimName = JwtClaimNames.SUB;
//                if (properties.getPrincipalAttribute() != null) {
//                    claimName = properties.getPrincipalAttribute();
//                }
//                return jwt.getClaim(claimName);
//            }

            private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
                Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
                Map<String, Object> resource;
                Collection<String> resourceRoles;
                if (resourceAccess == null
                    || (resource = (Map<String, Object>) resourceAccess.get("account")) == null
                    || (resourceRoles = (Collection<String>) resource.get("roles")) == null) {
                    return Set.of();
                }
                return resourceRoles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                    .collect(Collectors.toSet());
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
            .requestMatchers(HttpMethod.GET, "/test/anonymous", "/test/anonymous/**").permitAll()
            .requestMatchers("/", "/api-docs/**", "/swagger-ui/**").permitAll()
            .requestMatchers("/orders/**").permitAll()
            .anyRequest().authenticated();
        http.oauth2ResourceServer()
            .jwt();
//                .jwtAuthenticationConverter(jwtConverter());
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.oauth2Login(Customizer.withDefaults());
        http.oauth2Client();
        return http.build();
    }

}
