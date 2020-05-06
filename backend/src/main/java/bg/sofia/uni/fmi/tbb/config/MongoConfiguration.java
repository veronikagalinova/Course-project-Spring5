package bg.sofia.uni.fmi.tbb.config;

import com.mongodb.MongoClientOptions;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfiguration {

  private final MongoProperties mongoProperties;

  public MongoConfiguration(MongoProperties mongoProperties) {

    super();
    this.mongoProperties = mongoProperties;
  }


  @Bean
  public MongoClientOptions mongoClientOptions() {

    return MongoClientOptions.builder().sslEnabled(true).build();
  }

}
