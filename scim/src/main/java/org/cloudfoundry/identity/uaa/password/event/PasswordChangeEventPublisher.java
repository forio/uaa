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

package org.cloudfoundry.identity.uaa.password.event;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.cloudfoundry.identity.uaa.audit.event.AbstractUaaEvent;
import org.cloudfoundry.identity.uaa.scim.dao.common.ScimUserProvisioning;
import org.cloudfoundry.identity.uaa.scim.domain.common.ScimEmail;
import org.cloudfoundry.identity.uaa.scim.domain.common.ScimUserInterface;
import org.cloudfoundry.identity.uaa.scim.exception.ScimResourceNotFoundException;
import org.cloudfoundry.identity.uaa.user.UaaUser;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Event publisher for password changes with the resulting event type varying
 * according to the input and outcome. Can be
 * used as an aspect intercepting calls to a component that changes user
 * password.
 *
 * @author Dave Syer
 *
 */
public class PasswordChangeEventPublisher implements ApplicationEventPublisherAware {

    private ScimUserProvisioning dao;

    private ApplicationEventPublisher publisher;

    public PasswordChangeEventPublisher(ScimUserProvisioning provisioning) {
        this.dao = provisioning;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void passwordFailure(String userId, Exception e) {
        UaaUser client = getUser(userId);
        if (client == null) {
            publish(new PasswordFailureEvent(e.getMessage(), client, getPrincipal()));
        }
        else {
            publish(new PasswordFailureEvent(e.getMessage(), getPrincipal()));
        }
    }

    public void passwordChange(String userId) {
        publish(new PasswordChangeEvent("Password changed", getUser(userId), getPrincipal()));
    }

    private UaaUser getUser(String userId) {
        try {
            // If the request came in for a user by id we should be able to
            // retrieve the username
            ScimUserInterface scimUser = dao.retrieve(userId);
            Date today = new Date();
            if (scimUser != null) {
                return new UaaUser(scimUser.getId(), scimUser.getUserName(), "N/A", getEmail(scimUser), null,
                                scimUser.getGivenName(),
                                scimUser.getFamilyName(), today, today);
            }
        } catch (ScimResourceNotFoundException e) {
            // ignore
        }
        return null;
    }

    private String getEmail(ScimUserInterface scimUser) {
        List<ScimEmail> emails = scimUser.getEmails();
        if (emails == null || emails.isEmpty()) {
            return scimUser.getUserName().contains("@") ? scimUser.getUserName() : scimUser.getUserName()
                            + "@unknown.org";
        }
        for (ScimEmail email : emails) {
            if (email.isPrimary()) {
                return email.getValue();
            }
        }
        return scimUser.getEmails().get(0).getValue();
    }

    private Principal getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private void publish(AbstractUaaEvent event) {
        if (publisher != null) {
            publisher.publishEvent(event);
        }
    }

}
