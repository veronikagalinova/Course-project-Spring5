package bg.sofia.uni.fmi.tbb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = 4972390712601872115L;

    private String jwttoken;
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private List<String> roles;
}
