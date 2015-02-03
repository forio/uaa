package org.cloudfoundry.identity.uaa.user;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcUaaGroupsDatabase {

  private JdbcTemplate jdbcTemplate;

  public JdbcUaaGroupsDatabase (JdbcTemplate jdbcTemplate) {

    this.jdbcTemplate = jdbcTemplate;
  }
}
