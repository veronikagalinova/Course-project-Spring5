package bg.sofia.uni.fmi.tbb.web;

import bg.sofia.uni.fmi.tbb.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;

@Slf4j
public final class AuthorizationUtil {

    private static final String INVALID_ENTITY_ERROR_MSG = "User ID=%s from " +
            "path is different from Entity ID=%s";

    private static final String NO_RIGHTS_MSG = "This action requires " +
            "administrator permissions.";

    private AuthorizationUtil() {

    }

    public static void verifyCurrentUserOrAdmin(String id,
                                                Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        if (!isUserAdministrator(principal) && !id.equals(principal.getId())) {
            throw new AccessDeniedException(String.format(INVALID_ENTITY_ERROR_MSG, principal.getId(), id));
        }
    }

    public static void verifyAdmin(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        log.warn("Tries to access admin endpoint: " + principal.getUsername() + principal.getRoles());
        if (!isUserAdministrator(principal)) {
            log.warn("************************");
            throw new AccessDeniedException(NO_RIGHTS_MSG);
        }
    }

    private static boolean isUserAdministrator(User user) {
        return user.getRoles().indexOf(User.ADMIN) != -1;
    }
}
