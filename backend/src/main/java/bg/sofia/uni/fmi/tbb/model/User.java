package bg.sofia.uni.fmi.tbb.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;


@Document(collection = "users")
@Data
public class User {

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
    private String fname;

    @Length(min = 1, max = 30)
    private String lname;

    @Length(min = 10, max = 100)
    private String description;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

}
