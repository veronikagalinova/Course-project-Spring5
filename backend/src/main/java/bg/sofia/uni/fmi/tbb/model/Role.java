package bg.sofia.uni.fmi.tbb.model;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection="roles")
@Data
public class Role {
    public static String ROLE_ADMIN = "ROLE_ADMIN";
    public static String ROLE_TRAVELER = "ROLE_TRAVELER";
    public static String ROLE_BUS_COMPANY = "ROLE_BUS_COMPANY";

    @Id
    private String id;

    @NotNull
    @NonNull
    @Length(min = 2, max = 30)
    private String name;
}
