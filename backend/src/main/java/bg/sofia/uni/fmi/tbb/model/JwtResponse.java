package bg.sofia.uni.fmi.tbb.model;

import lombok.Data;

import javax.management.ConstructorParameters;
import java.io.Serializable;

@Data
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = 4972390712601872115L;

    private String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }
}
