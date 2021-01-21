package com.lojaunit.configs;

import com.zaxxer.hikari.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

 // @Value("${spring.datasource.url}")
	@Value("jdbc:postgresql://localhost/lojaunit?user=postgres&password=123456")
  private String dbUrl;

  @Bean
  public DataSource dataSource() {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(dbUrl);
      return new HikariDataSource(config);
  }
}