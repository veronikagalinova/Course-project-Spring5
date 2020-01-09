package bg.sofia.uni.fmi.tbb.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;


@Document(collection = "users")
@Data
@JsonIgnoreProperties(value = {"authorities", "accountNonExpired",
        "accountNonLocked",
        "credentialsNonExpired", "enabled"})
public class User implements UserDetails {

    @Id
    private String id;

    @NotNull
    @Length(min = 3, max = 30)
    @NonNull
    private String username;

    @NotNull
    @Length(min = 5, max = 30)
    @NonNull
    private String password;

    @NotNull
    @NonNull
    @Length(min = 1, max = 30)
    private String firstName;

    @Length(min = 1, max = 30)
    private String lastName;

    @NonNull
    private List<Role> roles = new ArrayList<>();

    private boolean active = true;


    public User(@NotNull @Length(min = 3, max = 30) @NonNull String username,
                @NotNull @Length(min = 5, max = 30) @NonNull String password,
                @NotNull @NonNull @Length(min = 1, max = 30) String firstName,
                @Length(min = 1, max = 30) String lastName,
                @NonNull List<Role> roles, boolean active) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
        this.active = active;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getAuthoritiesForRoles(getRoles());
    }

    private Collection<GrantedAuthority> getAuthoritiesForRoles(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
    }

    @JsonProperty(access = WRITE_ONLY)
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

}
