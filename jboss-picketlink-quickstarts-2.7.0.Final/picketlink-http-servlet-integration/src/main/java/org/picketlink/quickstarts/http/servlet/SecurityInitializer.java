/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.picketlink.quickstarts.http.servlet;

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.Role;
import org.picketlink.idm.model.basic.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import static org.picketlink.idm.model.basic.BasicModel.grantRole;

/**
 * This startup bean creates a number of default users, groups and roles when the application is started.
 * 
 * @author Pedro Igor
 */
@Singleton
@Startup
public class SecurityInitializer {

    @Inject
    private PartitionManager partitionManager;

    @PostConstruct
    public void create() {
        // Create user john
        User john = new User("john");

        IdentityManager identityManager = this.partitionManager.createIdentityManager();

        identityManager.add(john);
        identityManager.updateCredential(john, new Password("john"));

        Role userRole = new Role("User");

        identityManager.add(userRole);

        // Create application role "adminRole"
        Role adminRole = new Role("Administrator");

        identityManager.add(adminRole);

        RelationshipManager relationshipManager = this.partitionManager.createRelationshipManager();

        // Grant the "userRole" application role to john
        grantRole(relationshipManager, john, userRole);
    }
}
