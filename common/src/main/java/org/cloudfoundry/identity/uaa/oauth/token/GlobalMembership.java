package org.cloudfoundry.identity.uaa.oauth.token;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "global")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class GlobalMembership extends Membership {

  private String role;

  public GlobalMembership () {

  }

  public GlobalMembership (String role) {

    this.role = role;
  }

  @Override
  @XmlTransient
  public MembershipType getMembershipType () {

    return MembershipType.GLOBAL;
  }

  @XmlElement(name = "role", required = true, nillable = false)
  public String getRole () {

    return role;
  }

  public void setRole (String role) {

    this.role = role;
  }
}
