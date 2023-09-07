package de.ait.gr5.bs.controllers.utils;

import de.ait.gr5.bs.models.City;
import de.ait.gr5.bs.models.User;
import de.ait.gr5.bs.security.details.AuthenticatedUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class userAuthorization {

  public static void userAuthorizationForTest(User userForAuth) {

    AuthenticatedUser user = new AuthenticatedUser(userForAuth);
    Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    SecurityContext securityContext = SecurityContextHolder.getContext();
    securityContext.setAuthentication(authentication);

  }

  public static User createdUser(Long userId, String email, String password, User.State state,
                                 User.Role role, boolean agreement) {
    return User.builder()
        .userId(userId)
        .email(email)
        .password(password)
        .state(state)
        .role(role)
        .agreement(agreement)
        .city(City.builder().titleCity("Berlin").build())
        .build();

  }
}