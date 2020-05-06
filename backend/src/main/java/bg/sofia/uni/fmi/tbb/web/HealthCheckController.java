package bg.sofia.uni.fmi.tbb.web;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Used from AWS target group to determine EC2 instance state
 */
@RestController
@RequestMapping("/api/healthcheck")
public class HealthCheckController {

  @GetMapping
  public ResponseEntity<Void> healthCheck() {
    return ok().build();
  }
}
