package catalog.kyrs.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserAuthority implements GrantedAuthority {
    CUSTOMERS,
    ADMINISTRATOR,
    FULL;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
