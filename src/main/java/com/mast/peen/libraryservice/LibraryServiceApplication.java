package com.mast.peen.libraryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableJpaAuditing
@EnableJpaRepositories
@EnableConfigurationProperties
public class LibraryServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(LibraryServiceApplication.class, args);
  }

}
