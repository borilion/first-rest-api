package com.in28minutes.springboot.firstrestapi.user;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsCommandLineRunner implements CommandLineRunner {

  Logger logger = LoggerFactory.getLogger(getClass());

  UserDetailsRepository repository;

  public UserDetailsCommandLineRunner(UserDetailsRepository repository) {
    this.repository = repository;
  }

  @Override
  public void run(String... args) throws Exception {
    repository.save(new UserDetails("Ranga", "USER, ADMIN"));
    repository.save(new UserDetails("Ravi", "ADMIN"));
    repository.save(new UserDetails("John", "ADMIN"));

    // List<UserDetails> users = repository.findAll();
    List<UserDetails> users = repository.findByRole("Admin");

    logger.debug("========================");
    users.forEach(user -> logger.debug(user.toString()));
    logger.debug("========================");
  }

}
