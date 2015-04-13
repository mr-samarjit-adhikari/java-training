/**
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
package com.limocat.authentication.idm;

import java.util.List;

import org.picketlink.event.PartitionManagerCreateEvent;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.Realm;
import org.picketlink.idm.model.basic.User;
import org.picketlink.idm.query.IdentityQueryBuilder;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

@Stateless
public class PartitionManagerInitializer {

    /**
     * <p>Creates some default users for each realm/company.</p>
     */
    public void onInit(@Observes PartitionManagerCreateEvent event) {
        PartitionManager partitionManager = event.getPartitionManager();
        String			 realmName  	  = Resources.REALM.system.name();

        if(!isSystemUserAndRealmExists(partitionManager,realmName)){
        	createSystemUserAndRealm(partitionManager, realmName, realmName);
        }
        
    }
    
    private boolean isSystemUserAndRealmExists(PartitionManager pm,String realmName){
    	Realm partition = pm.getPartition(Realm.class, realmName);
    	
    	if(partition == null){
    		return false;
    	}
    	
    	IdentityManager identityManager = pm.createIdentityManager(partition);
    	IdentityQueryBuilder iqueryBuilder = identityManager.getQueryBuilder();
    	
    	List<User> users= iqueryBuilder.createIdentityQuery(User.class)
    						.where(iqueryBuilder.equal(User.LOGIN_NAME, realmName))
    						.getResultList();
    	
    	if(users.size() == 0){
    		return false;
    	}
    	
    	return true;
    }
    
	private void createSystemUserAndRealm(PartitionManager partitionManager, String realmName, String loginName) {
        Realm partition = partitionManager.getPartition(Realm.class, realmName);
        partition = new Realm(realmName);
        partitionManager.add(partition);

        IdentityManager identityManager = partitionManager.createIdentityManager(partition);        

        User user = new User(loginName);
        Password password = new Password(loginName); //Default passwords is same as loginName

        identityManager.add(user);
        identityManager.updateCredential(user, password);
    }
}