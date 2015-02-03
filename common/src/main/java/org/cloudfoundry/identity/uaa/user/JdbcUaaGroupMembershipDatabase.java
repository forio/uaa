package org.cloudfoundry.identity.uaa.user;

import java.util.List;
import java.util.Map;
import org.cloudfoundry.identity.uaa.oauth.token.LocalGroup;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcUaaGroupMembershipDatabase {

  private JdbcTemplate jdbcTemplate;

  public JdbcUaaGroupMembershipDatabase (JdbcTemplate jdbcTemplate) {

    this.jdbcTemplate = jdbcTemplate;
  }

  public String[] listGlobalRoles (UaaUser user) {

    List<String> roleList = jdbcTemplate.queryForList("select m.application_role from uaa.groups g, uaa.group_membership m where g.displayname = 'global' and m.group_id = g.id and m.member_id = ?", new Object[]{user.getId()}, String.class);

    if ((roleList == null) || roleList.isEmpty()) {

      return new String[0];
    }

    String[] roles = new String[roleList.size()];

    roleList.toArray(roles);

    return roles;
  }

  public String[] listAccountIds (UaaUser user) {

    List<String> accountList = jdbcTemplate.queryForList("select g.displayName from uaa.groups g, uaa.group_membership m where g.displayname like 'account|%' and m.group_id = g.id and m.member_id = ?", new Object[]{user.getId()}, String.class);

    if ((accountList == null) || accountList.isEmpty()) {

      return new String[0];
    }

    String[] accountIds = new String[accountList.size()];
    int index = 0;

    for (String displayName : accountList) {
      accountIds[index++] = displayName.substring("account|".length());
    }

    return accountIds;
  }

  public LocalGroup[] listLocalGroups (UaaUser user) {

    List<Map<String, Object>> localList = jdbcTemplate.queryForList("select g.displayName as displayname, m.application_role as application_role from uaa.groups g, uaa.group_membership m where g.displayname like 'local|%' and m.group_id = g.id and m.member_id = ?", new Object[]{user.getId()});

    if ((localList == null) || localList.isEmpty()) {

      return new LocalGroup[0];
    }

    LocalGroup[] localGroups = new LocalGroup[localList.size()];
    int index = 0;

    for (Map<String, Object> map : localList) {

      String[] parts = ((String)map.get("displayname")).split("\\|", -1);

      localGroups[index++] = new LocalGroup(parts[1], parts[2], parts[3], (String)map.get("application_role"));
    }

    return localGroups;
  }
}
