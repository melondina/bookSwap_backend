package de.ait.gr5.bs.security.details;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
  public boolean isUserPermission(Long userId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication.isAuthenticated()) {
      Object principal = authentication.getPrincipal();

      if (principal instanceof AuthenticatedUser authenticatedUser) {
        return authenticatedUser.id().equals(userId);
      }
    }
    return false;
  }
}
