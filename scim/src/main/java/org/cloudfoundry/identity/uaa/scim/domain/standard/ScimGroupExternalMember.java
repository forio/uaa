/*******************************************************************************
 *     Cloud Foundry
 *     Copyright (c) [2009-2014] Pivotal Software, Inc. All Rights Reserved.
 *
 *     This product is licensed to you under the Apache License, Version 2.0 (the "License").
 *     You may not use this product except in compliance with the License.
 *
 *     This product includes a number of subcomponents with
 *     separate copyright notices and license terms. Your use of these
 *     subcomponents is subject to the terms and conditions of the
 *     subcomponent's license, as noted in the LICENSE file.
 *******************************************************************************/
package org.cloudfoundry.identity.uaa.scim.domain.standard;

import org.cloudfoundry.identity.uaa.scim.domain.common.ScimGroupExternalMemberInterface;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ScimGroupExternalMember extends ScimCore implements ScimGroupExternalMemberInterface {

    private String groupId;

    private String externalGroup;

    public ScimGroupExternalMember(String groupId, String externalGroup) {
        this.groupId = groupId;
        this.externalGroup = externalGroup;
    }

    @Override
    public String getGroupId() {
        return groupId;
    }

    @Override
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public String getExternalGroup() {
        return externalGroup;
    }

    @Override
    public void setExternalGroup(String externalGroup) {
        this.externalGroup = externalGroup;
    }

    @Override
    public String toString() {
        return String
                        .format("(Group id: %s, created: %s, modified: %s, version: %s, externalGroups: %s)",
                                        getId(), getMeta().getCreated(), getMeta().getLastModified(), getVersion(),
                                        externalGroup);
    }
}
