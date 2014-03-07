package org.cloudfoundry.identity.uaa.scim;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class ScimGroupJsonSerializer extends JsonSerializer<ScimGroup> {
    @Override
    public void serialize(ScimGroup group, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        Map<String, List<ScimGroupMember>> roles = new HashMap<String, List<ScimGroupMember>>();
        for (ScimGroupMember.Role authority : ScimGroupMember.Role.values()) {
            String role = authority.toString().toLowerCase()+"s";
            roles.put(role, new ArrayList<ScimGroupMember>());
            if (group.getMembers() != null) {
                for (ScimGroupMember member : group.getMembers()) {
                    if (member.getRoles().contains(authority)) {
                        roles.get(role).add(member);
                    }
                }
            }
        }

        Map<Object, Object> groupJson = new HashMap<Object, Object>();

        // old fields
        addNonNull(groupJson, "meta", group.getMeta());
        addNonNull(groupJson, "schemas", group.getSchemas());
        addNonNull(groupJson, "id", group.getId());
        addNonNull(groupJson, "displayName", group.getDisplayName());

        // new fields
        addNonNull(groupJson, "organization", group.getOrganization());
        addNonNull(groupJson, "event", group.getEvent());
        addNonNull(groupJson, "eventDate", convertDate(group.getEventDate()));

        for (Map.Entry<String, List<ScimGroupMember>> entry : roles.entrySet()) {
            addNonNull(groupJson, entry.getKey(), entry.getValue());
        }

        jgen.writeObject(groupJson);

    }

    private void addNonNull (Map<Object, Object> map, Object key, Object value) {
        if (value == null || (value instanceof Collection && ((Collection<?>)value).isEmpty())) {
            return;
        }
        map.put(key, value);
    }

    private String convertDate(Date date)
    {
        if (date == null)
        {
            return null;
        }
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        df.setTimeZone(tz);
        return df.format(date);
    }
}
