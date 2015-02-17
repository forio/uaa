package org.cloudfoundry.identity.uaa.oauth.token;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "membership")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MembershipWrapper {

  private Membership membership;

  public MembershipWrapper () {

  }

  public MembershipWrapper (Membership membership) {

    this.membership = membership;
  }

  @XmlElementRefs({@XmlElementRef(type = PersonalMembership.class), @XmlElementRef(type = LocalMembership.class), @XmlElementRef(type = ProjectMembership.class), @XmlElementRef(type = AccountMembership.class), @XmlElementRef(type = GlobalMembership.class)})
  public Membership getMembership () {

    return membership;
  }

  public void setMembership (Membership membership) {

    this.membership = membership;
  }
}
