package org.cloudfoundry.identity.uaa.oauth.token;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "token")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class OAuthToken {

  private String userId;
  private String parentAccountId;
  private GlobalMembership[] globalMemberships;
  private AccountMembership[] accountMemberships;
  private ProjectMembership[] projectMemberships;
  private LocalMembership[] localMemberships;
  private PersonalMembership[] personalMemberships;

  public OAuthToken () {

  }

  @XmlElement(name = "userId", required = true, nillable = false)
  public String getUserId () {

    return userId;
  }

  public void setUserId (String userId) {

    this.userId = userId;
  }

  @XmlElement(name = "parentAccountId", required = true, nillable = false)
  public String getParentAccountId () {

    return parentAccountId;
  }

  public void setParentAccountId (String parentAccountId) {

    this.parentAccountId = parentAccountId;
  }

  @XmlElement(name = "globalMemberships", required = false, nillable = false)
  public GlobalMembership[] getGlobalMemberships () {

    return globalMemberships;
  }

  public void setGlobalMemberships (GlobalMembership[] globalMemberships) {

    this.globalMemberships = globalMemberships;
  }

  @XmlElement(name = "accountMemberships", required = false, nillable = false)
  public AccountMembership[] getAccountMemberships () {

    return accountMemberships;
  }

  public void setAccountMemberships (AccountMembership[] accountMemberships) {

    this.accountMemberships = accountMemberships;
  }

  @XmlElement(name = "projectMemberships", required = false, nillable = false)
  public ProjectMembership[] getProjectMemberships () {

    return projectMemberships;
  }

  public void setProjectMemberships (ProjectMembership[] projectMemberships) {

    this.projectMemberships = projectMemberships;
  }

  @XmlElement(name = "localMemberships", required = false, nillable = false)
  public LocalMembership[] getLocalMemberships () {

    return localMemberships;
  }

  public void setLocalMemberships (LocalMembership[] localMemberships) {

    this.localMemberships = localMemberships;
  }

  @XmlElement(name = "personalMemberships", required = false, nillable = false)
  public PersonalMembership[] getPersonalMemberships () {

    return personalMemberships;
  }

  public void setPersonalMemberships (PersonalMembership[] personalMemberships) {

    this.personalMemberships = personalMemberships;
  }
}
