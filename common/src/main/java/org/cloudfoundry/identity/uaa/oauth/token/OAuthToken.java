package org.cloudfoundry.identity.uaa.oauth.token;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "token")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class OAuthToken {

  private String userId;
  private String localAccountId;
  private String[] globalRoles;
  private String[] accountIds; //account ids where user is a member of the account group
  private LocalGroup[] localGroups;

  public OAuthToken () {

  }

  @XmlElement(name = "userId", required = true, nillable = false)
  public String getUserId () {

    return userId;
  }

  public void setUserId (String userId) {

    this.userId = userId;
  }

  @XmlElement(name = "localAccountId", required = true, nillable = false)
  public String getLocalAccountId () {

    return localAccountId;
  }

  public void setLocalAccountId (String localAccountId) {

    this.localAccountId = localAccountId;
  }

  @XmlElement(name = "globalRoles", required = true, nillable = false)
  public String[] getGlobalRoles () {

    return globalRoles;
  }

  public void setGlobalRoles (String[] globalRoles) {

    this.globalRoles = globalRoles;
  }

  @XmlElement(name = "accountIds", required = true, nillable = false)
  public String[] getAccountIds () {

    return accountIds;
  }

  public void setAccountIds (String[] accountIds) {

    this.accountIds = accountIds;
  }

  @XmlElement(name = "localGroups", required = true, nillable = false)
  public LocalGroup[] getLocalGroups () {

    return localGroups;
  }

  public void setLocalGroups (LocalGroup[] localGroups) {

    this.localGroups = localGroups;
  }
}
