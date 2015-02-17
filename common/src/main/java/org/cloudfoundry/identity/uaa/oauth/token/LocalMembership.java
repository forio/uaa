package org.cloudfoundry.identity.uaa.oauth.token;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "local")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class LocalMembership extends Membership {

  private String accountId;
  private String projectId;
  private String groupId;
  private String groupName;
  private String role;

  public LocalMembership () {

  }

  public LocalMembership (String accountId, String projectId, String groupId, String groupName, String role) {

    this.accountId = accountId;
    this.projectId = projectId;
    this.groupId = groupId;
    this.groupName = groupName;
    this.role = role;
  }

  @Override
  @XmlTransient
  public MembershipType getMembershipType () {

    return MembershipType.LOCAL;
  }

  @XmlElement(name = "accountId", required = true, nillable = false)
  public String getAccountId () {

    return accountId;
  }

  public void setAccountId (String accountId) {

    this.accountId = accountId;
  }

  @XmlElement(name = "projectId", required = true, nillable = false)
  public String getProjectId () {

    return projectId;
  }

  public void setProjectId (String projectId) {

    this.projectId = projectId;
  }

  @XmlElement(name = "groupId", required = true, nillable = false)
  public String getGroupId () {

    return groupId;
  }

  public void setGroupId (String groupId) {

    this.groupId = groupId;
  }

  @XmlElement(name = "groupName", required = true, nillable = false)
  public String getGroupName () {

    return groupName;
  }

  public void setGroupName (String groupName) {

    this.groupName = groupName;
  }

  @XmlElement(name = "role", required = true, nillable = false)
  public String getRole () {

    return role;
  }

  public void setRole (String role) {

    this.role = role;
  }
}
