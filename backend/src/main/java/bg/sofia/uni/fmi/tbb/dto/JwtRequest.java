package bg.sofia.uni.fmi.tbb.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtRequest implements Serializable {
    private static final long serialVersionUID = -7114738552608475653L;

    private String username;
    private String password;
}
