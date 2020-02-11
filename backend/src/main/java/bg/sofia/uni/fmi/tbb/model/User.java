package bg.sofia.uni.fmi.tbb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;


@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties(value = {"authorities", "accountNonExpired",
        "accountNonLocked",
        "credentialsNonExpired", "enabled", "active"})
public class User implements UserDetails {

    public static final String ADMIN = "ROLE_ADMIN";
    public static final String BUS_COMPANY = "ROLE_BUS_COMPANY";
    public static final String TRAVELER = "ROLE_TRAVELER";

    @Id
    private String id;

    @NotNull
    @Length(min = 3, max = 30)
    @NonNull
    private String username;

//    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,16})")
    @NotNull
    @NonNull
    @JsonProperty(access = WRITE_ONLY)
    private String password;

    @NotNull
    @NonNull
    @Length(min = 1, max = 30)
    private String firstName;

    @NotNull
    @NonNull
    @Length(min = 1, max = 30)
    private String lastName;

    private List<String> roles = new ArrayList<>();

    private boolean active = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toSet());
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

    public static List<String> getValidRoles() {
        return Arrays.asList(ADMIN, BUS_COMPANY,TRAVELER);
    }

}
