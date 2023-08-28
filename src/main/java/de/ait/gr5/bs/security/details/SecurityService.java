package de.ait.gr5.bs.security.details;

import de.ait.gr5.bs.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
  public boolean isUserAuthorized(Long userId) {
    AuthenticatedUser authenticatedUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return authenticatedUser.id().equals(userId);
  }
}
