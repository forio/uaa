package org.cloudfoundry.identity.uaa.scim;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(using = ScimGroupJsonSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
@JsonDeserialize(using = ScimGroupJsonDeserializer.class)
public class ScimGroup extends ScimCore {

    private String displayName;
    private List<ScimGroupMember> members;

    private String organization;
    private String event;
    private Date eventDate;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<ScimGroupMember> getMembers() {
        return members;
    }

    public void setMembers(List<ScimGroupMember> members) {
        this.members = members;
    }

    public String getOrganization()
    {
        return organization;
    }

    public void setOrganization(String organization)
    {
        this.organization = organization;
    }

    public String getEvent()
    {
        return event;
    }

    public void setEvent(String event)
    {
        this.event = event;
    }

    public Date getEventDate()
    {
        return eventDate;
    }

    public void setEventDate(Date date)
    {
        this.eventDate = date;
    }

    public ScimGroup() {
    }

    public ScimGroup(String name) {
        this.displayName = name;
    }

    public ScimGroup(String id, String name) {
        super(id);
        this.displayName = name;
    }

    @Override
    public String toString() {
        return String.format("(Group id: %s, name: %s, created: %s, modified: %s, version: %s, members: %s)", getId(), displayName, getMeta().getCreated(), getMeta().getLastModified(), getVersion(), members);
    }
}
