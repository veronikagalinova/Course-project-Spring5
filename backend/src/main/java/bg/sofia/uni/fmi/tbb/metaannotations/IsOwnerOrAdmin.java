package bg.sofia.uni.fmi.tbb.metaannotations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Uses SecurityService to check if current bus company has line with given
 * id or if current traveler has ticket with given id
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('ADMIN') or @securityService.isOwner(#id, " +
        "authentication.principal)")
public @interface IsOwnerOrAdmin {
}
