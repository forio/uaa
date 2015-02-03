package org.cloudfoundry.identity.uaa.oauth.token;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "localGroup")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class LocalGroup {

  private String accountId;
  private String projectId;
  private String group;
  private String role;

  public LocalGroup () {

  }

  public LocalGroup (String accountId, String projectId, String group, String role) {

    this.accountId = accountId;
    this.projectId = projectId;
    this.group = group;
    this.role = role;
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

  @XmlElement(name = "group", required = true, nillable = false)
  public String getGroup () {

    return group;
  }

  public void setGroup (String group) {

    this.group = group;
  }

  @XmlElement(name = "role", required = true, nillable = false)
  public String getRole () {

    return role;
  }

  public void setRole (String role) {

    this.role = role;
  }
}
