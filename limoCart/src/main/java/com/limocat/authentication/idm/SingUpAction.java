package com.limocat.authentication.idm;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.apache.deltaspike.core.api.config.view.metadata.DefaultCallback;
import org.picketlink.credential.DefaultLoginCredentials;
import org.picketlink.idm.IdentityManager;

@RequestScoped
public class SingUpAction {
	@Inject
	private IdentityManager		identityManager;
	
	@Inject
	private DefaultLoginCredentials	SignUpCredential;
	
	private String  	realmName;
	private String 		role;
}
