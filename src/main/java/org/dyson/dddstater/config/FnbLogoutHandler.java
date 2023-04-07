package org.dyson.dddstater.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Component
@Slf4j
@RequiredArgsConstructor
public class FnbLogoutHandler implements LogoutHandler {
    private final RestTemplate restTemplate;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        OidcUser user = (OidcUser) authentication.getPrincipal();
        String endSessionUri = user.getIssuer() + "/protocal/openid-connect/logout";
        var builder = UriComponentsBuilder.fromUriString(endSessionUri)
                .queryParam("id_token_hint", user.getIdToken().getTokenValue());
        ResponseEntity<String> logoutResponse = restTemplate.getForEntity(builder.toUriString(), String.class);
        if (logoutResponse.getStatusCode().is2xxSuccessful()) {
            log.info("Successfulley logged out from Keycloak");
        } else {
            log.error("Could not propagate logout to Keycloak");
        }
    }
}
