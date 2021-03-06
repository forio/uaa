package org.cloudfoundry.identity.uaa.oauth.token;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "account")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class AccountMembership extends Membership {

  private String accountId;
  private String role;

  public AccountMembership () {

  }

  public AccountMembership (String accountId, String role) {

    this.accountId = accountId;
    this.role = role;
  }

  @Override
  @XmlTransient
  public MembershipType getMembershipType () {

    return MembershipType.ACCOUNT;
  }

  @XmlElement(name = "accountId", required = true, nillable = false)
  public String getAccountId () {

    return accountId;
  }

  public void setAccountId (String accountId) {

    this.accountId = accountId;
  }

  @XmlElement(name = "role", required = true, nillable = false)
  public String getRole () {

    return role;
  }

  public void setRole (String role) {

    this.role = role;
  }
}
