package org.cloudfoundry.identity.uaa.oauth.token;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProjectMembership extends Membership {

  private String accountId;
  private String projectId;
  private String role;

  public ProjectMembership () {

  }

  public ProjectMembership (String accountId, String projectId, String role) {

    this.accountId = accountId;
    this.projectId = projectId;
    this.role = role;
  }

  @Override
  @XmlTransient
  public MembershipType getMembershipType () {

    return MembershipType.PROJECT;
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

  @XmlElement(name = "role", required = true, nillable = false)
  public String getRole () {

    return role;
  }

  public void setRole (String role) {

    this.role = role;
  }
}
