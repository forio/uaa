package org.cloudfoundry.identity.uaa.user;

import java.util.List;
import java.util.Map;
import org.cloudfoundry.identity.uaa.oauth.token.AccountMembership;
import org.cloudfoundry.identity.uaa.oauth.token.GlobalMembership;
import org.cloudfoundry.identity.uaa.oauth.token.LocalMembership;
import org.cloudfoundry.identity.uaa.oauth.token.MembershipWrapper;
import org.cloudfoundry.identity.uaa.oauth.token.PersonalMembership;
import org.cloudfoundry.identity.uaa.oauth.token.ProjectMembership;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcUaaGroupMembershipDatabase {

  private static final MembershipWrapper[] NO_MEMEBRS = new MembershipWrapper[0];

  private JdbcTemplate jdbcTemplate;

  public JdbcUaaGroupMembershipDatabase (JdbcTemplate jdbcTemplate) {

    this.jdbcTemplate = jdbcTemplate;
  }

  public MembershipWrapper[] listPersonalMemberships (UaaUser user) {

    List<Map<String, Object>> personalList = jdbcTemplate.queryForList("select g.displayName as displayname, m.application_role as application_role from uaa.groups g, uaa.group_membership m where g.displayname like ? and m.group_id = g.id and m.member_id = ?", new Object[]{user.getId() + "|%", user.getId()});

    if ((personalList == null) || personalList.isEmpty()) {

      return NO_MEMEBRS;
    }

    MembershipWrapper[] personalMemberships = new MembershipWrapper[personalList.size()];
    int index = 0;

    for (Map<String, Object> map : personalList) {

      String[] parts = ((String)map.get("displayname")).split("\\|", -1);

      personalMemberships[index++] = new MembershipWrapper(new PersonalMembership(parts[1], (String)map.get("application_role")));
    }

    return personalMemberships;
  }

  public MembershipWrapper[] listLocalMemberships (UaaUser user) {

    List<Map<String, Object>> localList = jdbcTemplate.queryForList("select g.id as group_id, g.displayName as displayname, m.application_role as application_role from uaa.groups g, uaa.group_membership m where g.displayname like 'local|%' and m.group_id = g.id and m.member_id = ?", new Object[]{user.getId()});

    if ((localList == null) || localList.isEmpty()) {

      return NO_MEMEBRS;
    }

    MembershipWrapper[] localMemberships = new MembershipWrapper[localList.size()];
    int index = 0;

    for (Map<String, Object> map : localList) {

      String[] parts = ((String)map.get("displayname")).split("\\|", -1);

      localMemberships[index++] = new MembershipWrapper(new LocalMembership(parts[1], parts[2], (String)map.get("group_id"), parts[3], (String)map.get("application_role")));
    }

    return localMemberships;
  }

  public MembershipWrapper[] listProjectMemberships (UaaUser user) {

    List<Map<String, Object>> projectList = jdbcTemplate.queryForList("select g.displayName as displayname, m.application_role as application_role from uaa.groups g, uaa.group_membership m where g.displayname like 'project|%' and m.group_id = g.id and m.member_id = ?", new Object[]{user.getId()});

    if ((projectList == null) || projectList.isEmpty()) {

      return NO_MEMEBRS;
    }

    MembershipWrapper[] projectMemberships = new MembershipWrapper[projectList.size()];
    int index = 0;

    for (Map<String, Object> map : projectList) {

      String[] parts = ((String)map.get("displayname")).split("\\|", -1);

      projectMemberships[index++] = new MembershipWrapper(new ProjectMembership(parts[1], parts[2], (String)map.get("application_role")));
    }

    return projectMemberships;
  }

  public MembershipWrapper[] listAccountMemberships (UaaUser user) {

    List<Map<String, Object>> accountList = jdbcTemplate.queryForList("select g.displayName as displayname, m.application_role as application_role from uaa.groups g, uaa.group_membership m where g.displayname like 'account|%' and m.group_id = g.id and m.member_id = ?", new Object[]{user.getId()});

    if ((accountList == null) || accountList.isEmpty()) {

      return NO_MEMEBRS;
    }

    MembershipWrapper[] accountMemberships = new MembershipWrapper[accountList.size()];
    int index = 0;

    for (Map<String, Object> map : accountList) {

      String[] parts = ((String)map.get("displayname")).split("\\|", -1);

      accountMemberships[index++] = new MembershipWrapper(new AccountMembership(parts[1], (String)map.get("application_role")));
    }

    return accountMemberships;
  }

  public MembershipWrapper[] listGlobalMemberships (UaaUser user) {

    List<String> globalList = jdbcTemplate.queryForList("select m.application_role as application_role from uaa.groups g, uaa.group_membership m where g.displayname = 'global' and m.group_id = g.id and m.member_id = ?", new Object[]{user.getId()}, String.class);

    if ((globalList == null) || globalList.isEmpty()) {

      return NO_MEMEBRS;
    }

    MembershipWrapper[] globalMemberships = new MembershipWrapper[globalList.size()];
    int index = 0;

    for (String globalRole : globalList) {
      globalMemberships[index++] = new MembershipWrapper(new GlobalMembership(globalRole));
    }

    return globalMemberships;
  }
}
