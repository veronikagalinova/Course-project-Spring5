package bg.sofia.uni.fmi.tbb.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * Represents cities in the transport network
 */
@Document(collection = "stops")
@Data
public class Stop {
    @Id
    private String id;

    @NotNull
    @NonNull
    private String location;
}
